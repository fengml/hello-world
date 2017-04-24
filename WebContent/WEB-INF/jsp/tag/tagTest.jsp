<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="fsl" prefix="fsl" %>
<%@ taglib prefix="fsl1" tagdir="/WEB-INF/tags/" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
    <h2>自定义标签      &lt fsl:list msg="message" count="2"/&gt</h2>
    <fsl:list msg="message" count="2"/>
    
    <h2>自定义方法标签</h2>
    ${fsl:hello('hello')}
    
    <h2>通过jsp自定义标签</h2>
    <label for="sex">性别</label><fsl1:select1 id="sex" dictName="sex"></fsl1:select1><br>
    <label for="phone">电话</label><fsl1:select1 id="phone" dictName="phone" itemCode="1"></fsl1:select1><br>
  	<label for="class">班级</label><fsl1:select2 id="class" dictName="class" itemCode="1"></fsl1:select2><br>
  </body>
</html>
