package com.ucpeo.activity.service.impl;

import com.ucpeo.activity.bean.ActForm;
import com.ucpeo.activity.dao.ActFormDao;
import com.ucpeo.activity.service.ActFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActFormServiceImpl implements ActFormService {
    @Resource
    ActFormDao formDao;

    @Override
    public void create(ActForm actFrom) {
        formDao.create(actFrom);
    }

    @Override
    public void delete(Integer id) {
        formDao.delete(id);
    }

    @Override
    public void update(ActForm actFrom) {
        formDao.update(actFrom);
    }

    @Override
    public ActForm get(Integer id) {
        return formDao.get(id);
    }

    @Override
    public List<ActForm> getAll() {
        return formDao.getAll();
    }

    @Override
    public List<ActForm> actActForm(Integer actId) {
        return formDao.actActForm(actId);
    }
}
