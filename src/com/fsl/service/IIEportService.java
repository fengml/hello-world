package com.fsl.service;

import java.io.File;
import java.util.List;

import com.fsl.entity.User;

public interface IIEportService {
    
    public List<User> importFile(File file);
}
