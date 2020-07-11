package com.ucpeo.activity.service;

import com.ucpeo.activity.bean.Act;
import com.ucpeo.activity.bean.User;
import com.ucpeo.activity.dao.BaseDao;
import com.ucpeo.activity.dao.UserDao;

import java.util.List;

public interface UserService  {

    User getByUsername(String username);

    void create(User user);

    void delete(Integer id);

    void update(User user);

    User get(Integer id);

    List<User> getAll();

    User login(String username, String password);

    User get(String username);

}
