package com.fsl.dao;

import com.fsl.entity.Test;

public interface TestDao {
    
    public int getModelById(Integer id); 
    
    public int insert(Test test);
}
