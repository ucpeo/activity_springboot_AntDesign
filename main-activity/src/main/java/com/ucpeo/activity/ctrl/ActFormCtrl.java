package com.ucpeo.activity.ctrl;


import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.ActForm;
import com.ucpeo.activity.bean.Resp;
import com.ucpeo.activity.bean.inter.ActState;
import com.ucpeo.activity.service.ActFormService;
import com.ucpeo.activity.service.ActService;
import com.ucpeo.activity.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("actForm")
public class ActFormCtrl {
    @Autowired
    ActService actService;

    @Autowired
    ActFormService actFormService;

    @Autowired
    OptionService optionService;

    @PostMapping("{actId}")
    public Resp<ActForm> create(@PathVariable("actId") Integer act, @RequestBody ActForm actForm) {
        Act activity = actService.get(act);
        if (activity.getState() != ActState.ACT_STATE_CREATE) {
            return new Resp<>("仅活动创建状态可以添加表单", 400, null);
        }
        if (!activity.getEnForm()) {
            activity.setEnForm(true); // 活动开启表单 并保存
            actService.update(activity);
        }

        actForm.setAct(activity);
        actFormService.create(actForm);
        if (actForm.getOptionList() != null && actForm.getOptionList().size() > 0)
            optionService.batchSaveOption(actForm.getId(), actForm.getOptionList());
        return new Resp<>("success", 0, actForm);
    }
}
