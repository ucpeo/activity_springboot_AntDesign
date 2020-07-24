package com.ucpeo.activity.service.impl;

import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.Example;
import com.ucpeo.activity.bean.Partake;
import com.ucpeo.activity.bean.User;
import com.ucpeo.activity.bean.inter.ActState;
import com.ucpeo.activity.bean.inter.PartakeState;
import com.ucpeo.activity.service.ActService;
import com.ucpeo.activity.service.PartakeService;
import com.ucpeo.activity.service.UserActDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserActDataServiceImpl implements UserActDataService, ActState, PartakeState {
    @Autowired
    ActService actService;

    @Autowired
    PartakeService partakeService;

    @Override
    public Map<String, Integer> getUserActDataInfo(User user) {
       Map<String,Integer> result = new HashMap<>(6);
        Act act = new Act();
        act.setCreateUser(user);

        result.put("actAll",actService.count(Example.parse(act)));
        act.setState(ACT_STATE_TASK);
        result.put("actIng",actService.count(Example.parse(act)));
        act.setState(ACT_STATE_FINISH);
        result.put("actFinish",actService.count(Example.parse(act)));

        Partake partake = new Partake();
        partake.setUser(user);


        result.put("partAll",partakeService.count( Example.parse(partake)));
        partake.setState(PART_STATE_ING);
        result.put("partIng",partakeService.count( Example.parse(partake)));
        partake.setState(PART_STATE_SUCCESS);
        result.put("partFinish",partakeService.count( Example.parse(partake)));
        return result;
    }
}
