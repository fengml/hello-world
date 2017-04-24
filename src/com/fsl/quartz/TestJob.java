package com.fsl.quartz;

import com.fsl.utils.DateUtils;


public class TestJob {
    public void doSomething(){
        System.out.println("定时任务,每隔一段时间执行:"+DateUtils.formatDateToString(DateUtils.getCurrentDate()));
    }
}
