package com.fsl.service;

import com.fsl.entity.User;

public interface IUserService {
    public User getModelById(Integer id);
    
    public int insert(User user);
    
    public int addOrUpdate(User user);
}
