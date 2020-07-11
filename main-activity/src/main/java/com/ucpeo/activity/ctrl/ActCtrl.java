package com.ucpeo.activity.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ucpeo.activity.bean.*;
import com.ucpeo.activity.bean.inter.ActState;


import com.ucpeo.activity.service.ActService;
import com.ucpeo.activity.service.PartakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.AffineTransform;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("act")
public class ActCtrl {


    @Autowired
    ActService actService;

    @Autowired
    PartakeService partakeService;


    @PostMapping
    public Resp<Act> create(@SessionAttribute("loginUser") User loginUser, @RequestBody Act act) {
        if (StringUtils.isEmpty(act.getName()) || act.getRecruited() == null || act.getEndTime() == null) {
            return new Resp<>("请正确填写信息", 409, null);
        }

        act.setCreateTime(new Date());
        act.setCreateUser(loginUser);
        act.setState(ActState.ACT_STATE_CREATE);
        System.out.println(loginUser == null);
        act.setEnForm(false);
        act.setEnRes(false);
        actService.create(act);
        return new Resp<>("success", 0, act);
    }

    @GetMapping("list")
    public List<Act> list() {
        return actService.getAll();
    }

    @GetMapping("{id}")
    public Resp<Act> get(@PathVariable("id") Integer id) {
        return new Resp<>("success", 0, actService.get(id));
    }

    @GetMapping("actFormlist/{id}")
    public Resp<List<ActForm>> getActformList(@PathVariable("id") Integer id) {
        Act act = actService.get(id);
        return new Resp<>("success", 0, act != null ? act.getActFormList() : null);
    }


    @GetMapping("myList")
    public Resp<List<Act>> myList(@SessionAttribute("loginUser") User loginUser) {
        System.out.println(JSON.toJSON(loginUser));
        Page page = new Page();
        page.setSize(3);
        Act act = new Act();
        act.setCreateUser(loginUser);
        Example example = Example.parse(act);
        actService.listByExample(example, page);
        return page;
    }

    @GetMapping("release/{id}")
    public Resp release(@PathVariable("id") Integer id) {
        Act act = actService.get(id);
        if (act.getState() == ActState.ACT_STATE_CREATE) {
            act.setState(ActState.ACT_STATE_TASK);
            actService.update(act);
            return new Resp();
        }
        return new Resp("发布活动失败", 400, null);
    }

    @GetMapping("stop/{id}")
    public Resp stop(@PathVariable("id") Integer id) {
        Act act = actService.get(id);
        if (ActState.ACT_STATE_TASK == act.getState()) {
            act.setState(ActState.ACT_STATE_FINISH);
            actService.update(act);
            return new Resp();
        }
        return new Resp("失败", 400, null);
    }

    @GetMapping("baseInfo/{id}")
    public Resp<JSONObject> getBaseInfo(@PathVariable("id") Integer id) {
        Act act = actService.get(id);
        if (act == null) {
            return new Resp<>("活动被删除！", 405, null);
        }
        JSONObject json = new JSONObject();
        json.put("participants", act.getParticipants());
        json.put("recruited", act.getRecruited());
        json.put("success", partakeService.getSuccessCount(act.getId()));
        json.put("ing", partakeService.getIngCount(act.getId()));
        return new Resp<>("success", 0, json);
    }

    @DeleteMapping("{id}")
    public Resp delete(@PathVariable("id") Integer id) {
        actService.delete(id);
        return new Resp();
    }


    @GetMapping("state/{state}")
    public Page<Act> getActByState(@SessionAttribute("loginUser") User user, @PathVariable("state") Integer state, Page page) {
        Act act = new Act();
        act.setCreateUser(user);
        if (state != -1)
            act.setState(state);
        Example example = Example.parse(act);
        System.out.println(JSON.toJSONString(example));
        actService.listByExample(example, page);
        return page;
    }


}
