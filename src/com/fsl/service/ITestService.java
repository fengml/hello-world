package com.fsl.service;

import com.fsl.entity.Test;

public interface ITestService {
    public int getModelById(Integer id);
    
    public int insert(Test test);
}
