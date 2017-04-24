package com.fsl.utils.tool;

import com.fsl.utils.tool.scaffold.*;

public class ScaffoldGenerator {

    public static void main(String[] args) throws Exception {
        // arg1 子系统名
        // arg2 业务对象名,即Model,双词以上要求单词首字大写
        // arg3 表名
        // ScaffoldGen generator = new ScaffoldGen("arg1", "arg2", "arg3");
        ScaffoldGen generator = new ScaffoldGen("testTool", "TestTest", "test");
        generator.disableCheckRequiredCol();
        generator.execute(false);
    }

}
