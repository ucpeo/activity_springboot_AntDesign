package com.ucpeo.activity.ctrl.api;


import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.Example;
import com.ucpeo.activity.bean.Page;
import com.ucpeo.activity.bean.Resp;
import com.ucpeo.activity.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/act")
public class OpenActCtrl {

    @Autowired
    ActService actService;

    @RequestMapping("list")
    public Resp<List<Act>> list(Page<Act> page, @RequestParam("search") String search) {
        System.out.println(page);
        System.out.println(search);
        Act act = new Act();
        if (!StringUtils.isEmpty(search))
            act.setName("%"+search+"%");
        Example example = Example.parse(act);
        actService.listByExample(example, page);
        return page;
    }


}
