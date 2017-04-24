package com.fsl.tag;

import java.util.List;


import org.springframework.context.ApplicationContext;

import com.fsl.entity.DictCode;
import com.fsl.service.IDictCodeService;
import com.fsl.utils.ApplicationUtil;

public class TestTagFunction {

    static ApplicationContext applicationContext = ApplicationUtil.getApplicationContext();
    
    private static IDictCodeService dictCodeService = (IDictCodeService) ApplicationUtil.getBean("dictCodeService");
    
    public static String hello(String str){
        return str.toUpperCase();
    }
    
    public static List<DictCode> getDict(String dictName,String itemCode){
        System.out.println("----------22-----------");
        DictCode dictCode = new DictCode(null,dictName,itemCode,null);
        List<DictCode> list = dictCodeService.getDict(dictCode);
        return list;
    }
}
