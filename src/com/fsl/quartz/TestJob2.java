package com.fsl.quartz;

import com.fsl.utils.DateUtils;


public class TestJob2 {
    public void doSomething(){
        System.out.println("定时任务,在指定时间执行:"+DateUtils.formatDateToString(DateUtils.getCurrentDate()));
    }
}
