package com.fsl.controller.excel;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.fsl.entity.ResponseData;
import com.fsl.entity.User;
import com.fsl.service.IIEportService;
import com.fsl.service.IUserService;
import com.fsl.utils.JsonUtils;

@Controller
@RequestMapping(value="/excel")
public class ExcelController {
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IIEportService IEportService;
    
    @RequestMapping("/file")
    public String goJsp(){
        return "file/file";
    }
    
    @RequestMapping("/importExcel")
    @ResponseBody
    public ResponseData importExcel(HttpServletRequest request){
        ResponseData result = new ResponseData();
        // 初始化路径
        // 保存文件的目录
        //PATH_FOLDER = servletCtx.getRealPath("/upload")+"/";
        String PATH_FOLDER = "E:/apache-tomcat/apache-tomcat-test/webapps/ROOT/upload/";
        File file = null;
      //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
             
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile mfile=multiRequest.getFile(iter.next().toString());
                if(mfile!=null)
                {
                    String path=PATH_FOLDER + System.currentTimeMillis() + mfile.getOriginalFilename();
                    File f = new File(PATH_FOLDER);
                    // 创建目录
                    if(!f.exists() && f.isDirectory()){
                        f.mkdir();
                    }
                    file = new File(path);
                    //上传
                    try {
                        mfile.transferTo(file);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        List<User> users = IEportService.importFile(file);
        
        System.out.println(users);
        System.out.println("users:"+users.toString());
        
        result.setStatus(0);
        result.setData(users);
        return result;
    }
    
    
    @RequestMapping("/exportExcel")
    public ResponseData exportExcel(HttpServletRequest request) {
        ResponseData result = new ResponseData();

        return result;
    }
    
    
}
