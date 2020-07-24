package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.Example;
import com.ucpeo.activity.bean.Page;
import com.ucpeo.activity.bean.Partake;
import com.ucpeo.activity.dao.BaseDao;
import com.ucpeo.activity.dao.PartakeDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartakeService {


    void create(Partake partake);


    void delete(Integer id);


    void update(Partake partake);


    Partake get(Integer id);


    List<Partake> getAll();


    List<Partake> getPartsByUser(Integer userId);

    List<Partake> getActPartByState(Integer actId, Integer state);

    List<Partake> getActPartByCheckState(Integer actId);

    Integer getSuccessCount(Integer actId);

    Integer getIngCount(Integer actId);

    List<Partake> listByExample(Example<Partake> example);

    Integer count(Example<Partake> example);

    void  listByExampleAndPage(Example<Partake> example, Page<Partake> page);


}

