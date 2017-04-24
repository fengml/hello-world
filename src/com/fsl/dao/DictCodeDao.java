package com.fsl.dao;

import java.util.List;

import com.fsl.entity.DictCode;

public interface DictCodeDao {
    
    public List<DictCode> getBaseList();
    
    public DictCode getModelById(Integer id); 
    
    public int insert(DictCode obj);
    
    public void update(DictCode obj);
    
    public int addOrUpdate(DictCode obj);
    
    public List<DictCode> getModel(DictCode obj);
}
