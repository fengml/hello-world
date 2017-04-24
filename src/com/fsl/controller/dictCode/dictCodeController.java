package com.fsl.controller.dictCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsl.entity.DictCode;
import com.fsl.entity.ResponseData;
import com.fsl.service.IDictCodeService;

@Controller
@RequestMapping(value = "/dictCode")
public class dictCodeController {
    private static final Logger  LOG = Logger.getLogger(dictCodeController.class);
    @Autowired
    private IDictCodeService dictCodeService;
    
    @RequestMapping("/getAll")
    public void getAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
        List<DictCode> list = new  ArrayList<DictCode>();
        list = dictCodeService.getBaseList();
        if(list!=null && list.size()>0){
            response.getWriter().print(list.toString());
        }
    }
    
    @RequestMapping("/getDict")
    @ResponseBody
    public ResponseData getDict(HttpServletRequest request,HttpServletResponse response,DictCode dictCode) throws IOException{
        List<DictCode> list = dictCodeService.getDict(dictCode);
        if(list!=null && list.size()>0){
            return ResponseData.getSuccessResponse(list); 
        }else{
            return ResponseData.getErrorResponse(null);
        }
        
    }
    
    
}
