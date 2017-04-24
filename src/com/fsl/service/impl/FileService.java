package com.fsl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsl.dao.FileDao;
import com.fsl.entity.File;
import com.fsl.service.IFileService;

@Service
public class FileService implements IFileService {
    @Autowired
    private FileDao FileDao;

    @Override
    public File getModelById(Integer id) {
        return FileDao.getModelById(id);
    }

    @Override
    public int insert(File file) {
        return FileDao.insert(file);
    }
}
