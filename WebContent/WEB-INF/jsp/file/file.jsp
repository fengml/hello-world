<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="appPath" value="${pageContext.servletContext.contextPath}${initParam.prePath}"/><%-- 写页面绝对路径时使用，如果需要在模块和应用上下文增加一级路径时候，这个定义的价值就体现出来了 --%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/><%-- 写静态资源时使用 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
	<title>file 测试</title>
	<script type="text/javascript" src="${contextPath}/js/common/jquery.min.js"></script>
</head>
<body>
	<!-- action="${contextPath}/excel/importExcel" method="post"   enctype="multipart/form-data" -->
	<form id="form_file">
		<input type="file" name="file">
  		<input id="submit_file" type="button" value="开始"/>
  	</form>
  	<div id="loadgif"  style="width:66px;height:66px;position:absolute;top:50%;left:50%;">
　　	 <img  alt="加载中..." src="${contextPath}/image/loading.gif"/>
	</div>
	<div id="user_data">
	</div>
</body>
<script type="text/javascript">
	/* $(function(){
		$("#loadgif").show();
		setTimeout(function(){
			$("#loadgif").hide();
		}, 2000);
	}) */
	$("#loadgif").hide();
	$("#submit_file").click(function(){
		$("#loadgif").show();
		var form = new FormData($("#form_file")[0]);
		console.log(form);
		$.ajax({
			url:'${contextPath}'+"/excel/importExcel",
			type:"post",
			//data:$("#form_file").serialize(),
			dataType:"JSON",
			data : form, 
			// 告诉jQuery不要去处理发送的数据
			processData : false, 
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			//contentType:"multipart/form-data",
			success:function(result){
				if(result.status==0){
					$("#loadgif").hide();
					var div_user = $("#user_data");
					div_user.empty();
					var str_table = '<h5>内容预览:</h5><table border="1"><tr><td>姓名</td><td>密码</td></tr>';
					for(var i=0;i<result.data.length;i++){
						var user = result.data[i];
						var str_tr = '<tr><td>'+user.userName+'</td><td>'+user.passWord+'</td></tr>';
						var str_table = str_table + str_tr;
					}
					str_table += "</table>";
					div_user.append($(str_table));
				}
			},
			error:function(){
				alert("系统异常!");
			}
		})
	})
</script>



