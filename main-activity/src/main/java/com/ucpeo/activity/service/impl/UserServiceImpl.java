package com.ucpeo.activity.service.impl;

import com.ucpeo.activity.bean.User;
import com.ucpeo.activity.dao.UserDao;
import com.ucpeo.activity.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public User get(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}
