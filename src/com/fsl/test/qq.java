/*package com.fsl.test;

package com.tarena.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.tarena.entity.Admin;

*//**
 * 记录异常日志组件
 * @author soft1
 *
 *//*
@Component
@Aspect
public class ExceptionLogger {
    
    //Spring容器自动注入request,在web.xml中通过listener将request放入容器,测试的时候注释掉，否则对测试有影响(tomcat不会加载web.xml)
    //也可暂时去掉这个类，待项目完成后再添加上
    @Resource
    private HttpServletRequest request;
    
    @Around("within(com.tarena.controller..*)")
    public Object log(ProceedingJoinPoint p) throws Throwable{
        
        
        Object obj = null;
        try {
            //调用目标组件
            obj = p.proceed();
        } catch (Throwable e) {
            //发生异常时记录日志
            Admin admin = (Admin)request.getSession().getAttribute("admin");
            if(admin != null){
                //用户名
                String name = admin.getName();
                //ip
                String ip = request.getRemoteHost();
                //当前系统时间
                String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                //类名
                String className = p.getTarget().getClass().getName();
                //方法名
                String method = p.getSignature().getName();
                String msg = "用户: " + name + " ,IP: " + ip + " ,于 " + now + 
                                " \n访问的类及方法: " + className + "." + method + "() ,发生如下异常：\n";
                StackTraceElement[] elems = e.getStackTrace();
                for (StackTraceElement elem : elems) {
                    msg += "\t\t" + elem + "\n"; 
                }
                //开始记录日志文件
                Logger logger = Logger.getLogger(ExceptionLogger.class);
                logger.error(msg);
            }
            //记录完毕后抛出异常，由Spring处理异常
            throw e;
        }
        return obj;
    }
}

*/