package com.fsl.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 
 * 描述：插入大量数据,性能测试
 * @author fengshengliang 2017年3月3日 下午4:16:23 
 * @version 1.0
 */
public class TestForInsertLargeNumber {
    // rewriteBatchedStatements=true启用批处理
    private String url = "jdbc:mysql://localhost:3306/test2?rewriteBatchedStatements=true";
    private String user = "root";
    private String password = "102020";
    
    /**
     * 
     * 描述：jdbc 手动提交事务  10万条数据
     *     总用时大概:19秒
     * @author fengshengliang 2017年3月3日 下午4:15:58 
     * @version 1.0
     */
    //@org.junit.Test
    public void Test(){
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);  
            
            //执行前 先 truncate 表
            String sql1 = "truncate large_userInfo";
            PreparedStatement pstm1 = conn.prepareStatement(sql1);
            pstm1.execute();
            
            
            String sql = "INSERT INTO large_userInfo(uname,uphone,uaddress) VALUES(CONCAT('姓名',?),?,?)";
            pstm = conn.prepareStatement(sql);
            // 设置自动提交为false
            conn.setAutoCommit(false);
            
            int n = 100000;
            System.out.println("数据量:"+n+"条");
            Long startTime = System.currentTimeMillis();
            System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            Random rand = new Random();
            int a,b,c,d;
            
            for (int i = 1; i <= n; i++) {
                    //pstm.setInt(1, i);
                    pstm.setInt(1, i);
                    a = rand.nextInt(10);
                    b = rand.nextInt(10);
                    c = rand.nextInt(10);
                    d = rand.nextInt(10);
                    pstm.setString(2, "188"+a+"88"+b+c+"66"+d);
                    pstm.setString(3, "xxxxxxxxxx_"+"188"+a+"88"+b+c+"66"+d);
                    pstm.executeUpdate();
            }
            
            // 手动提交事务
            conn.commit();
            
            Long endTime = System.currentTimeMillis();
            System.out.println("结束时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            System.out.println("完成,总用时大概：" + (endTime - startTime)/1000+"秒"); 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    
    /**
     * 
     * 描述：使用批处理导入数据 100万条数据
     * @author fengshengliang 2017年3月3日 下午5:16:29 
     * @version 1.0
     */
    //@org.junit.Test
    public void Test2(){
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);  
            //执行前 先 truncate 表
            String sql1 = "truncate large_userInfo";
            PreparedStatement pstm1 = conn.prepareStatement(sql1);
        //    pstm1.execute();
            
            String sql = "INSERT INTO large_userInfo(uid,uname,uphone,uaddress) VALUES(?,CONCAT('姓名',?),?,?)";
            pstm = conn.prepareStatement(sql);

            int n = 1000000;
            System.out.println("数据量:"+n+"条");
            Long startTime = System.currentTimeMillis();
            System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            
            Random rand = new Random();
            int a,b,c,d;
            for (int i = 1; i <= n; i++) {
                    pstm.setInt(1, i);
                    pstm.setInt(2, i);
                    a = rand.nextInt(10);
                    b = rand.nextInt(10);
                    c = rand.nextInt(10);
                    d = rand.nextInt(10);
                    pstm.setString(3, "188"+a+"88"+b+c+"66"+d);
                    pstm.setString(4, "xxxxxxxxxx_"+"188"+a+"88"+b+c+"66"+d);
                    pstm.addBatch();
            }
            pstm.executeBatch();
            Long endTime = System.currentTimeMillis();
            System.out.println("结束时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            System.out.println("完成,总用时大概：" + (endTime - startTime)/1000+"秒"); 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    /**
     * 
     * 描述： 批量处理 + 事务提交 100万条数据
     *     用时大概: 35秒
     * @author fengshengliang 2017年3月6日 上午10:07:19 
     * @version 1.0
     */
    @org.junit.Test
    public void Test3(){
        Connection conn = null;
        PreparedStatement pstm =null;
        ResultSet rt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);  
            //执行前 先 truncate 表
            String sql1 = "truncate large_userInfo";
            PreparedStatement pstm1 = conn.prepareStatement(sql1);
            pstm1.execute();
            
            String sql = "INSERT INTO large_userInfo(uid,uname,uphone,uaddress) VALUES(?,CONCAT('姓名',?),?,?)";
            pstm = conn.prepareStatement(sql);
            
            conn.setAutoCommit(false);
            
            int n = 1000000;
            System.out.println("数据量:"+n+"条");
            Long startTime = System.currentTimeMillis();
            System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            
            Random rand = new Random();
            int a,b,c,d;
            for (int i = 1; i <= n; i++) {
                    pstm.setObject(1, null);
                    pstm.setInt(2, i);
                    a = rand.nextInt(10);
                    b = rand.nextInt(10);
                    c = rand.nextInt(10);
                    d = rand.nextInt(10);
                    pstm.setString(3, "188"+a+"88"+b+c+"66"+d);
                    pstm.setString(4, "xxxxxxxxxx_"+"188"+a+"88"+b+c+"66"+d);
                    pstm.addBatch();
            }
            pstm.executeBatch();
            
            conn.commit();
            
            Long endTime = System.currentTimeMillis();
            System.out.println("结束时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            System.out.println("完成,总用时大概：" + (endTime - startTime)/1000+"秒"); 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            if(pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
}
