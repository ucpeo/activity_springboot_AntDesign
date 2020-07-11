package com.ucpeo.activity.ctrl;

import com.ucpeo.activity.bean.*;
import com.ucpeo.activity.bean.inter.ActState;
import com.ucpeo.activity.bean.inter.PartakeState;
import com.ucpeo.activity.service.ActService;
import com.ucpeo.activity.service.PartakeService;
import com.ucpeo.activity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("partake")
public class PartakeCtrl {
    @Autowired
    ActService actService;
    @Autowired
    PartakeService partakeService;

    @Autowired
    UserService userService;

    @GetMapping("join/{id}")
    public Resp<Partake> take(@SessionAttribute("loginUser") User loginUser, @PathVariable("id") Integer id) {
        Act act = actService.get(id);
        if (loginUser == null) {
            return new Resp<>("缺少登录用户", 400, null);
        }
        if (act == null) {
            return new Resp<>("参与活动不存在", 400, null);
        }

        if (act.getState() != ActState.ACT_STATE_TASK)
            return new Resp<>("报名失败,请检查活动是否在集结状态", 400, null);


        if (actService.checkUserTake(act.getId(), loginUser.getId())) {
            return new Resp<>("您已报名该活动，请勿重复报名！", 400, null);
        }

        Partake partake = new Partake();
        partake.setState(PartakeState.PART_STATE_ING);
        partake.setAct(act);
        partake.setUser(loginUser);
        partakeService.create(partake);

        act.setParticipants(act.getParticipants() == null ? 1 : act.getParticipants() + 1);//参与人数+1
        actService.update(act);
        return new Resp<>("success", 0, partake);
    }

    @GetMapping("my")
    public Resp<List<Partake>> myPartakes(@SessionAttribute("loginUser") User user) {
        Partake partake = new Partake();
        partake.setUser(user);
        Example example = Example.parse(partake);
        Page page = new Page();
        page.setSize(3);
        partakeService.listByExampleAndPage(example, page);
        return page;

    }

    @GetMapping("{id}")
    public Resp<Partake> get(@PathVariable("id") Integer id) {
        return new Resp<>("success", 0, partakeService.get(id));
    }

    @GetMapping("check/{id}")
    public Resp<List<Partake>> getCheckParts(Page<Partake> page, @PathVariable("id") Integer id) {
        Partake partake = new Partake();
        partake.setState(PartakeState.PART_STATE_CHECK);
        Act act = new Act();
        act.setId(id);
        partake.setAct(act);
        Example<Partake> partakeExample = Example.parse(partake);
        partakeService.listByExampleAndPage(partakeExample, page);
        return page;

    }

    @GetMapping("successList/{id}")
    public Resp<List<Partake>> getSuccessList(Page<Partake> page, @PathVariable("id") Integer id) {
        Partake partake = new Partake();
        partake.setState(PartakeState.PART_STATE_SUCCESS);
        Act act = new Act();
        act.setId(id);
        partake.setAct(act);
        Example<Partake> partakeExample = Example.parse(partake);

        partakeService.listByExampleAndPage(partakeExample, page);

        return page;
    }

    @GetMapping("pass/{id}")
    public Resp passPart(@PathVariable("id") Integer id) {
        Partake partake = partakeService.get(id);
        if (PartakeState.PART_STATE_CHECK == partake.getState()) {
            partake.setState(PartakeState.PART_STATE_SUCCESS);
            partakeService.update(partake);
            return new Resp();
        }
        return new Resp("失败,请检查", 400, null);
    }

    @GetMapping("reset/{id}")
    public Resp resetPart(@PathVariable("id") Integer id) {
        Partake partake = partakeService.get(id);
        if (PartakeState.PART_STATE_CHECK == partake.getState()) {
            partake.setState(PartakeState.PART_STATE_ING);
            partakeService.update(partake);
            return new Resp();
        }
        return new Resp("失败,请检查", 400, null);
    }


    @GetMapping("state/{state}")
    public Page<Act> getActByState(@SessionAttribute("loginUser") User user, @PathVariable("state") Integer state, Page page) {
        Partake partake = new Partake();
        partake.setUser(user);
        if (state != -1)
            partake.setState(state);

        partakeService.listByExampleAndPage(Example.parse(partake), page);
        return page;
    }

}
