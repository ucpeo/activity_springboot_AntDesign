package com.ucpeo.activity.service.impl;

import com.ucpeo.activity.bean.PartForm;
import com.ucpeo.activity.dao.PartFormDao;
import com.ucpeo.activity.service.PartFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PartFormServiceImpl implements PartFormService {

    @Resource
    PartFormDao partFormDao;

    @Override
    public List<PartForm> partFormByPart(Integer partId) {
        return partFormDao.partFormByPart(partId);
    }

    @Override
    public void create(PartForm partForm) {
        partFormDao.create(partForm);
    }

    @Override
    public void delete(Integer id) {
        partFormDao.delete(id);
    }

    @Override
    public void update(PartForm partForm) {
        partFormDao.update(partForm);
    }

    @Override
    public PartForm get(Integer id) {
        return partFormDao.get(id);
    }

    @Override
    public List<PartForm> getAll() {
        return partFormDao.getAll();
    }
}

