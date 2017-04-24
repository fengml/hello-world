package com.fsl.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class TestController extends JUnitActionBase{
    @Test
    public void testAdd() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();   
        MockHttpServletResponse response = new MockHttpServletResponse();   
        request.setServletPath("/test/add");   
        request.addParameter("id", "1002");   
        request.addParameter("name", "junit");   
        request.setMethod("POST");
        // 执行URI对应的action   
        final ModelAndView mav = this.excuteAction(request, response);   
        // Assert logic   
        Assert.assertEquals("test/add", mav.getViewName());   
        String msg=(String)request.getAttribute("msg");   
        System.out.println(msg);
    }
}
