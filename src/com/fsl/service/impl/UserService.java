package com.fsl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsl.dao.UserDao;
import com.fsl.entity.User;
import com.fsl.service.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getModelById(Integer id) {
        return userDao.getModelById(id);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int addOrUpdate(User user) {
        return userDao.addOrUpdate(user);
    }
    
}
