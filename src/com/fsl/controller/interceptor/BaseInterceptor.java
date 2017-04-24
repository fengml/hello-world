package com.fsl.controller.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 拦截器
 * 描述：
 * @author fengshengliang 2017年2月21日 上午11:20:54 
 * @version 1.0
 */
public class BaseInterceptor extends HandlerInterceptorAdapter{
    // set 注入不需要拦截的url集合
    private List<String> notInterceptorUrl;
    
    public List<String> getNotInterceptorUrl() {
        return notInterceptorUrl;
    }

    public void setNotInterceptorUrl(List<String> notInterceptorUrl) {
        this.notInterceptorUrl = notInterceptorUrl;
    }

    /**
     * 用于资源的销毁
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器:全部完成后处理..");
        super.afterCompletion(request, response, handler, ex);
    }

    
    /**
     * modelAndView: 可以用来改变请求显示的地图
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        System.out.println("拦截器:拦截后处理..");
        super.postHandle(request, response, handler, modelAndView);
    }

    /***
     * 返回值:表示我们是否要将请求拦截下来
     * 返回false: 请求被终止
     * 
     * Object参数:  表示被拦截的请求的目标对象
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器:拦截前处理..");
        String url = request.getRequestURI();
        System.out.println("未拦截地址:"+notInterceptorUrl);
        System.out.println("当前访问路径:"+url);
        System.out.println(url.substring(url.indexOf("/", 2)));
        boolean flag = false;
        
        for(String ni : notInterceptorUrl){
            if((url.substring(url.indexOf("/", 2)).equals(ni))){ //不拦截
                flag = true;
                System.out.println("是否允许访问: 是");
                break;
            }
        }
        
        /*if(!flag){
            System.out.println("是否允许访问: 否");
            // 进入权限提示页面
            request.getRequestDispatcher("/common/interceptor").forward(request, response);
            return false;
        }*/
        
        return super.preHandle(request, response, handler);
    }
    
}
