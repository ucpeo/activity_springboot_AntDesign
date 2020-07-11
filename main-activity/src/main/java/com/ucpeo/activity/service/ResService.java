package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.Option;
import com.ucpeo.activity.bean.Res;
import com.ucpeo.activity.dao.BaseDao;
import com.ucpeo.activity.dao.ResDao;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ResService  {


    void batchSave(List<Res> res);

    void create(Res res);


    void delete(Integer id);


    void update(Res res);


    Res get(Integer id);


    List<Res> getAll();

}

