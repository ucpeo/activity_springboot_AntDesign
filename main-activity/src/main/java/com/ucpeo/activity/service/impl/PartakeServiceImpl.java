package com.ucpeo.activity.service.impl;

import com.alibaba.fastjson.JSON;
import com.ucpeo.activity.bean.Example;
import com.ucpeo.activity.bean.Page;
import com.ucpeo.activity.bean.Partake;
import com.ucpeo.activity.bean.inter.PartakeState;
import com.ucpeo.activity.dao.PartakeDao;
import com.ucpeo.activity.service.PartakeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PartakeServiceImpl implements PartakeService {
    @Resource
    PartakeDao partakeDao;

    @Override
    public void create(Partake partake) {
        partakeDao.create(partake);
    }

    @Override
    public void delete(Integer id) {
        partakeDao.delete(id);
    }

    @Override
    public void update(Partake partake) {
        partakeDao.update(partake);
    }

    @Override
    public Partake get(Integer id) {
        return partakeDao.get(id);
    }

    @Override
    public List<Partake> getAll() {
        return partakeDao.getAll();
    }

    @Override
    public List<Partake> getPartsByUser(Integer userId) {
        return partakeDao.getPartsByUser(userId);
    }

    @Override
    public Integer getSuccessCount(Integer actId) {
        return partakeDao.getByStateCount(actId, PartakeState.PART_STATE_SUCCESS);
    }

    @Override
    public Integer getIngCount(Integer actId) {
        return partakeDao.getByStateCount(actId, PartakeState.PART_STATE_ING);
    }

    @Override
    public List<Partake> listByExample(Example<Partake> example) {

        return partakeDao.listByExample(example);
    }

    @Override
    public void listByExampleAndPage(Example<Partake> example, Page<Partake> page) {
        page.setCount(partakeDao.countByExample(example));
        page.render();
        example.put("_start", page.getStart());
        example.put("_end", page.getEnd());
        System.out.println(JSON.toJSON(example));
        page.setData(partakeDao.listByExample(example));
    }

    @Override
    public List<Partake> getActPartByState(Integer actId, Integer state) {
        return partakeDao.getActPartByState(actId, state);
    }

    @Override
    public List<Partake> getActPartByCheckState(Integer actId) {
        return partakeDao.getActPartByState(actId, PartakeState.PART_STATE_CHECK);
    }

}
