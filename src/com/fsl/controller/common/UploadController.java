package com.fsl.controller.common;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fsl.entity.ResponseData;
import com.fsl.service.IFileService;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private IFileService fileService;
    
    // 保存文件的目录
    private static String PATH_FOLDER = "/";
    // 存放临时文件的目录
    private static String TEMP_FOLDER = "/";
    
    @RequestMapping("/uploadFile")
    @ResponseBody
    public ResponseData upload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException{
        ResponseData result = new ResponseData();
        ServletContext servletCtx=request.getSession().getServletContext();
        // 初始化路径
        // 保存文件的目录
        //PATH_FOLDER = servletCtx.getRealPath("/upload")+"/";
        PATH_FOLDER = "E:/apache-tomcat/apache-tomcat-test/webapps/ROOT/upload/";
        // 存放临时文件的目录,存放xxx.tmp文件的目录
        TEMP_FOLDER = servletCtx.getRealPath("/uploadTemp/")+"/";
        
        //----------
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
             
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path=PATH_FOLDER+file.getOriginalFilename();
                    File f = new File(PATH_FOLDER);
                    f.mkdir();
                    //上传
                    file.transferTo(new File(path));
                    JSONObject json = new JSONObject();
                    String filePath = "/upload/"+file.getOriginalFilename();
                    json.accumulate("picUrl", "/upload/"+file.getOriginalFilename());
                   
                    com.fsl.entity.File fi = new com.fsl.entity.File();
                    fi.setPath(filePath);
                    int n = fileService.insert(fi);
                    if(n==1){
                        json.element("fileId", fi.getId());
                        result.setData(json);
                        result.setStatus(0);
                    }else{
                        result.setStatus(1);
                    }
                    
                }
                 
            }
           
        }
        System.out.println("ok");
        return result;
    }
    
    private FileItem getUploadFileItem(List<FileItem> list) {
        for (FileItem fileItem : list) {
            if(!fileItem.isFormField()) {
                return fileItem;
            }
        }
        return null;
    }
    
    private String getUploadFileName(FileItem item) {
        // 获取路径名
        String value = item.getName();
        // 索引到最后一个反斜杠
        int start = value.lastIndexOf("/");
        // 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
        String filename = value.substring(start + 1);
        
        return filename;
    }
    
    
    
}
