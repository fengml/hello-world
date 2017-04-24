package com.fsl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsl.dao.TestDao;
import com.fsl.entity.Test;
import com.fsl.service.ITestService;

@Service
public class TestService implements ITestService {
    @Autowired
    private TestDao testDao;

    @Override
    public int getModelById(Integer id) {
        return testDao.getModelById(id);
    }

    @Override
    public int insert(Test test) {
        return testDao.insert(test);
    }
}
