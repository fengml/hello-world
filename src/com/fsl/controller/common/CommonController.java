package com.fsl.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 描述：拦截非法访问
 * @author fengshengliang 2017年1月11日 下午2:48:51 
 * @version 1.0
 */
@RequestMapping("/common")
@Controller
public class CommonController {
    
    @RequestMapping("/interceptor")
    public String interceptor(){
        return "/common/interceptor";
    }
}
