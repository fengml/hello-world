package com.fsl.controller.jcrop;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/jcrop")
@Controller
public class JcropController {
    Logger logger = Logger.getLogger(JcropController.class);



    @RequestMapping("/jc")
    public String jcrop() {
        return "jcrop/jcrop";
    }

    @RequestMapping("/jc2")
    public void uploadHeadImage(HttpServletRequest request, String x, String y,String h, String w,
            MultipartFile imageFile) throws Exception {
        System.out.println("==========Start=============");
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String resourcePath = "resources/uploadImages/";
        if (imageFile != null) {
            if (FileUploadUtil.allowUpload(imageFile.getContentType())) {
                String fileName = FileUploadUtil.rename(imageFile.getOriginalFilename());
                int end = fileName.lastIndexOf(".");
                String saveName = fileName.substring(0, end);
                File dir = new File(realPath + resourcePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, saveName + "_src.jpg");
                imageFile.transferTo(file);
                String srcImagePath = realPath + resourcePath + saveName;
                int imageX = Integer.parseInt(x);
                int imageY = Integer.parseInt(y);
                int imageH = Integer.parseInt(h);
                int imageW = Integer.parseInt(w);
                // 这里开始截取操作
                System.out.println("==========imageCutStart=============");
                ImgCut.imgCut(srcImagePath, imageX, imageY, imageW, imageH);
                // 删除源文件
                if (file.exists()){
                    file.delete();
                }
                System.out.println("==========imageCutEnd=============");
            }
        }
       // return null;
    }
}
