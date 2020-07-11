package com.ucpeo.activity.service.impl;

import com.ucpeo.activity.bean.Res;
import com.ucpeo.activity.dao.ResDao;
import com.ucpeo.activity.service.ResService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResServiceImpl implements ResService {
    @Resource
    ResDao resDao;

    @Override
    public void batchSave(List<Res> res) {
        resDao.batchSave(res);
    }

    @Override
    public void create(Res res) {
        resDao.create(res);
    }

    @Override
    public void delete(Integer id) {
        resDao.delete(id);
    }

    @Override
    public void update(Res res) {
        resDao.update(res);
    }

    @Override
    public Res get(Integer id) {
        return resDao.get(id);
    }

    @Override
    public List<Res> getAll() {
        return resDao.getAll();
    }
}
