package com.fsl.dao;

import com.fsl.entity.User;

public interface UserDao {
    
    public User getModelById(Integer id); 
    
    public int insert(User user);
    
    public void update(User user);
    
    public int addOrUpdate(User user);
}
