package com.ucpeo.activity.service;


import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.Example;
import com.ucpeo.activity.bean.Page;
import com.ucpeo.activity.dao.ActDao;


import java.util.List;

public interface ActService {

    void create(Act act);

    void delete(Integer id);

    void update(Act act);

    Act get(Integer id);

    List<Act> getAll();

    List<Act> getActsByUser(Integer userId);

    Boolean checkUserTake( Integer actId,  Integer userId);


    List<Act> listByExample(Example<Act> example);

    List<Act> listByExample(Example<Act> example, Page<Act> page);


    Integer count(Example<Act> example);
}
