package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.ActForm;
import com.ucpeo.activity.dao.ActFormDao;
import com.ucpeo.activity.dao.BaseDao;

import java.util.List;

public  interface ActFormService  {

    void create(ActForm actFrom);

    void delete(Integer id);

    void update(ActForm actFrom);

    ActForm get(Integer id);

    List<ActForm> getAll();

    List<ActForm> actActForm(Integer actId);
}

