package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.PartForm;
import com.ucpeo.activity.dao.BaseDao;
import com.ucpeo.activity.dao.PartFormDao;
import org.springframework.stereotype.Service;

import java.util.List;

public  interface PartFormService {

    List<PartForm> partFormByPart(Integer id);

    void create(PartForm partForm);


    void delete(Integer id);


    void update(PartForm partForm);


    PartForm get(Integer id);


    List<PartForm> getAll();
}

