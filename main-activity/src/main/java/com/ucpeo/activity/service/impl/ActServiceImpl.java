package com.ucpeo.activity.service.impl;

import com.alibaba.fastjson.JSON;
import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.Example;
import com.ucpeo.activity.bean.Page;
import com.ucpeo.activity.dao.ActDao;
import com.ucpeo.activity.service.ActService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActServiceImpl implements ActService {
    @Resource
    ActDao actDao;

    @Override
    public void create(Act act) {
        actDao.create(act);
    }

    @Override
    public void delete(Integer id) {
        actDao.delete(id);
    }

    @Override
    public void update(Act act) {
        System.out.println("json:" +
                JSON.toJSON(act));
        actDao.update(act);
    }

    @Override
    public Act get(Integer id) {
        return actDao.get(id);
    }

    @Override
    public List<Act> getAll() {
        return actDao.getAll();
    }

    @Override
    public Boolean checkUserTake(Integer actId, Integer userId) {
        return actDao.checkUserTake(actId, userId);
    }

    @Override
    public List<Act> getActsByUser(Integer userId) {
        return actDao.getActsByUser(userId);
    }


    @Override
    public List<Act> listByExample(Example<Act> example) {
        return actDao.listByExample(example);
    }

    @Override
    public List<Act> listByExample(Example<Act> example, Page<Act> page) {
        page.setCount(actDao.count(example));
        page.render();
        example.put("_start", page.getStart());
        example.put("_end", page.getEnd());
        page.setData(actDao.listByExample(example));
        System.out.println(JSON.toJSONString(example));
        return page.getData();
    }

    @Override
    public Integer count(Example<Act> example) {
        return actDao.count(example);
    }
}
