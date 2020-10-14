package com.ucpeo.activity.ctrl;

import com.ucpeo.activity.bean.Res;
import com.ucpeo.activity.dao.ResDao;
import org.springframework.cloud.commons.httpclient.DefaultOkHttpClientFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("res")
public class ResCtrl {

    @Resource
    ResDao resDao;

    @GetMapping("create")
    public Res create() {
        Res res = new Res();
        res.setType(1);
        res.setValue("45684135131");
        //resDao.create(res);
        return res;
    }

    @GetMapping("{id}")
    public Res get(@PathVariable("id") Integer id) {
        return resDao.get(id);
    }

     @GetMapping("list")
    public List<Res> list() {
        return resDao.getAll();
    }

    

}
