package com.ucpeo.activity.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.ucpeo.activity.bean.*;
import com.ucpeo.activity.bean.inter.ActCheckLevel;
import com.ucpeo.activity.bean.inter.ActState;
import com.ucpeo.activity.bean.inter.PartakeState;
import com.ucpeo.activity.service.ActFormService;
import com.ucpeo.activity.service.OptionService;
import com.ucpeo.activity.service.PartFormService;
import com.ucpeo.activity.service.PartakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("partForm")
public class partFormCtrl {
    public static final int TYPE_SIGN = 0;
    public static final int TYPE_MUTE = 1;
    public static final int TYPE_WRITE = 2;

    @Autowired
    PartakeService partakeService;

    @Autowired
    PartFormService partFormService;

    @Autowired
    ActFormService actFormService;

    @Autowired
    OptionService optionService;

    // 提交活动表单
    @PostMapping("partake/{id}")
    public Resp submitPartForm(@SessionAttribute("loginUser") User user, @PathVariable("id") Integer id, @RequestBody JSONObject jsonData) {
        Partake partake = partakeService.get(id);
        if (!partake.getUser().getId().equals(user.getId()))
            return new Resp("您无法提交,所属参与者才允许提交", 429, null);
        if (!partake.getAct().getState().equals(ActState.ACT_STATE_TASK) || !partake.getState().equals(PartakeState.PART_STATE_ING)) {
            return new Resp("无法提交,请检查状态", 400, null);
        }

        partake.getPartFormInputList().forEach(e->partFormService.delete(e.getId()));

        jsonData.keySet().forEach((key) -> {
            ActForm actForm = actFormService.get(Integer.valueOf(key)); //表单
            Integer type = actForm.getType();
            PartForm partForm = new PartForm();
            partForm.setPartake(partake);
            partForm.setType(type);
            partForm.setActForm(actForm);
            List<Option> options = new ArrayList<>();
            if (type == TYPE_WRITE) {
                partForm.setValue(jsonData.getString(key));
            } else if (type == TYPE_SIGN) {
                Integer optId = jsonData.getInteger(key);
                Option option = optionService.get(optId);
                options.add(option);
            } else if (type == TYPE_MUTE) {
                List<Integer> ids = jsonData.getJSONArray(key).toJavaList(Integer.class);
                ids.forEach((optId) -> options.add(optionService.get(optId)));
            }

            partForm.setOptionList(options);
            partFormService.create(partForm); // 保存用户表单
            if (partForm.getOptionList().size() > 0)
                optionService.saveOptionInput(partForm.getId(), partForm.getOptionList()); //保存用户表单选项

        });

        partake.setSubmitTime(new Date());
        // 更新参与状态
        if (partake.getAct().getCheckLevel() == ActCheckLevel.CHECK_LEVEL_AUTO_SUCCES) {
            partake.setState(PartakeState.PART_STATE_SUCCESS);
        } else {
            partake.setState(PartakeState.PART_STATE_CHECK);
        }
        partakeService.update(partake);

        return new Resp<>();
    }

    @GetMapping("listByPart/{id}")
    public Resp<List<PartForm>> getPartFormList(@PathVariable("id") Integer partId) {
        return new Resp<>("success", 0, partFormService.partFormByPart(partId));
    }

}
