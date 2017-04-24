package com.fsl.service;

import java.util.List;

import com.fsl.entity.DictCode;

public interface IDictCodeService {
    
    public List<DictCode> getBaseList();    
    
    public DictCode getModelById(Integer id);
    
    public int insert(DictCode obj);
    
    public int addOrUpdate(DictCode obj);
    
    public List<DictCode> getDict(DictCode obj);
}
