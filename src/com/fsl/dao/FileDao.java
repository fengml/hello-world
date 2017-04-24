package com.fsl.dao;

import com.fsl.entity.File;

public interface FileDao {
    
    public File getModelById(Integer id); 
    
    public int insert(File file);
    
    public void update(File file);
}
