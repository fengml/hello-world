package com.fsl.service;

import com.fsl.entity.File;

public interface IFileService {
    public File getModelById(Integer id);
    
    public int insert(File file);
}
