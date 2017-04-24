package com.fsl.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TestTagSupport extends SimpleTagSupport {
    private String msg;  //自定义标签所带的 参数
    private int count;   //自定义标签所带的 参数
    
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void doTag() throws JspException, IOException {
        
        System.out.println("执行 doTag() 方法..");
        
        /*
         * pageContext  唯一性,持久性
         * SimpleTagSupport类提供了一个方法
         * 来获得pageContext,pageContext提供了
         * 一些方法来获得所有的隐含对象.
         */
        PageContext ctx = (PageContext) getJspContext();
        JspWriter out = ctx.getOut();
        out.println(msg.toUpperCase()+"*"+count);
    }
}
