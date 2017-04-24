package com.fsl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsl.dao.DictCodeDao;
import com.fsl.entity.DictCode;
import com.fsl.service.IDictCodeService;

@Service
public class DictCodeService implements IDictCodeService {
    @Autowired
    private DictCodeDao dictCodeDao;

    @Override
    public DictCode getModelById(Integer id) {
        return dictCodeDao.getModelById(id);
    }

    @Override
    public int insert(DictCode obj) {
        return dictCodeDao.insert(obj);
    }

    @Override
    public int addOrUpdate(DictCode obj) {
        return dictCodeDao.addOrUpdate(obj);
    }

    @Override
    public List<DictCode> getBaseList() {
        return dictCodeDao.getBaseList();
    }

    @Override
    public List<DictCode> getDict(DictCode obj) {
        return dictCodeDao.getModel(obj);
    }
}
