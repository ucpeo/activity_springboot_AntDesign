package com.ucpeo.activity.ctrl;

import com.ucpeo.activity.bean.Resp;
import com.ucpeo.activity.bean.User;
import com.ucpeo.activity.service.UserActDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Map;

@RestController
@RequestMapping("my")
public class UserCenter {
    @Autowired
     UserActDataService userActDataService;

    @GetMapping("actData")
    public Resp<Map<String,Integer>> myActData(@SessionAttribute("loginUser")User loginUser){
        return  new Resp<>("",0,userActDataService.getUserActDataInfo(loginUser));
    }
}
