package com.fsl.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.fsl.entity.User;
import com.fsl.service.IIEportService;

@Service
public class IEportService implements IIEportService{

    @Override
    public List<User> importFile(File file) {
        //获取后缀名字
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        /*String fileName = mFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String ym = new SimpleDateFormat("yyyy-MM").format(new Date());
        String filePath = "/uploadFiles/" + ym + fileName;
        
        File file = new File(rootPath + filePath);
        if(file.exists()){
            file.delete();
            file.mkdirs();
        }else {
            file.mkdirs();
        }
        // 把文件写到对应目录下面
        try {
            mFile.transferTo(file);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        int result = 0;
        List<User> users = new ArrayList<User>();
        if ("xls".equals(suffix) || "XLS".equals(suffix)) {
            users = importXls(file);
            //ieportDao.importFile(secUserList);
        }else if ("xlsx".equals(suffix) || "XLSX".equals(suffix)) {
            users = importXlsx(file);
        }
        return users;
    }

    //处理 .xlsx文件
    private List<User> importXlsx(File file) {
        List<User> users = new ArrayList<User>();
        
        InputStream is = null;
        // 创建工作台
        XSSFWorkbook xWorkbook = null;
        
        try {
            is = new FileInputStream(file);
            xWorkbook = new XSSFWorkbook(is);
            // 获取第一页excel
            XSSFSheet xSheet = xWorkbook.getSheetAt(0);
            if (null != xSheet) {
                for (int i = 1; i < xSheet.getPhysicalNumberOfRows(); i++) {
                    User user = new User();
                    XSSFRow xRow = xSheet.getRow(i);

                    user.setUserName(xRow.getCell(1).toString());
                    user.setPassWord(xRow.getCell(2).toString());
                    
                    users.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    //处理 .xls文件
    private List<User> importXls(File file) {
        List<User> userList = new ArrayList<User>();
        
        InputStream is = null;
        HSSFWorkbook hWorkbook = null;
        try {
            is = new FileInputStream(file);
            hWorkbook = new HSSFWorkbook(is);
            HSSFSheet hSheet = hWorkbook.getSheetAt(0);
            
            if (null != hSheet){  
                for (int i = 1; i < hSheet.getPhysicalNumberOfRows(); i++){  
                    User su = new User();
                    HSSFRow hRow = hSheet.getRow(i);
                    
                    su.setUserName(hRow.getCell(0).toString());
                    su.setPassWord(hRow.getCell(1).toString());
                    
                    userList.add(su);
                }  
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (null != is) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    
        
        return userList;
    }
    
    
    public void exportFile(HttpServletResponse response) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        OutputStream os = null;
        XSSFWorkbook xWorkbook = null;
        try {
            String fileName = "User" + df.format(new Date()) + ".xlsx";
            
            os = response.getOutputStream();
            response.reset();
            
            response.setHeader("Content-disposition", "attachment; filename = " + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-streem");
            
            xWorkbook = new XSSFWorkbook();
            XSSFSheet xSheet = xWorkbook.createSheet("UserList");
            
            //set Sheet页头部
            setSheetHeader(xWorkbook, xSheet);
            
            //set Sheet页内容
            setSheetContent(xWorkbook, xSheet);
            
            xWorkbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        
    }

    /**
     * set Sheet页头部
     * @param xWorkbook
     * @param xSheet
     */
    private void setSheetHeader(XSSFWorkbook xWorkbook, XSSFSheet xSheet) {
        xSheet.setColumnWidth(0, 40 * 256);
        xSheet.setColumnWidth(1, 20 * 256);
        xSheet.setColumnWidth(2, 20 * 256);
        
        CellStyle cs = xWorkbook.createCellStyle();
        //设置水平垂直居中
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        //设置字体
        Font headerFont = xWorkbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setFontName("宋体");
        cs.setFont(headerFont);
        cs.setWrapText(true);//是否自动换行
        
        XSSFRow xRow0 = xSheet.createRow(0);
        
        XSSFCell xCell0 = xRow0.createCell(0);
        xCell0.setCellStyle(cs);
        xCell0.setCellValue("用户ID");
        
        XSSFCell xCell1 = xRow0.createCell(1);
        xCell1.setCellStyle(cs);
        xCell1.setCellValue("用户名");
        
        XSSFCell xCell2 = xRow0.createCell(2);
        xCell2.setCellStyle(cs);
        xCell2.setCellValue("密码");    
    }

    /**
     * set Sheet页内容
     * @param xWorkbook
     * @param xSheet
     */
    private void setSheetContent(XSSFWorkbook xWorkbook, XSSFSheet xSheet) {
        List<User> secUserList = null;
        
        CellStyle cs = xWorkbook.createCellStyle();
        cs.setWrapText(true);
        
        if (null != secUserList && secUserList.size() > 0) {
            for (int i = 0; i < secUserList.size(); i++) {
                XSSFRow xRow = xSheet.createRow(i + 1);
                User secUser = secUserList.get(i);
                for (int j = 0; j < 4; j++) {
                    XSSFCell xCell = xRow.createCell(j);
                    xCell.setCellStyle(cs);
                    switch (j) {
                        case 0:
                            xCell.setCellValue(secUser.getId());
                            break;
                        case 1:
                            xCell.setCellValue(secUser.getUserName());
                            break;
                        case 2:
                            xCell.setCellValue(secUser.getPassWord());
                            break;
                        default:
                            break;
                    }
                }    
            }            
        }
    }

}
