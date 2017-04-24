<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/comm_const_tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<label for="id">id:</label><input id="id" name="id"><br>
	<label for="userName">userName:</label><input id="userName" name="userName"><br>
	<label for="passWord">passWord</label><input id="passWord" name="passWord" type="password"><br>
	<input id="fileId" name="fileId" type="hidden">
	<label for="addLabProdPic">头像:</label><button id="addLabProdPic">选择图片</button><br>
	<img id="viewImg">
  
    <input onClick="fun_addUser()" type="button" value="提 交">
  </body>
  <script type="text/javascript" src="${contextPath}/js/common/jquery.min.js"></script>
  <script type="text/javascript" src="${contextPath}/js/common/ajaxupload.js"></script>
  <script type="text/javascript">
  	$(function(){
  		//上传图片
  		new AjaxUpload('#addLabProdPic', {
  			action: contextPath+'/upload/uploadFile', 
  			name: 'picFile',
  			responseType: 'json',
  			onSubmit : function(file , ext){
  				if (ext && /^(jpg|png|bmp)$/.test(ext.toLowerCase())){
  					this.setData({
  						'picName': file
  					});
  				} else {
  					alert("请上传格式为 jpg|png|bmp 的图片！");
  					return false;				
  				}
  			},
  			onComplete : function(file,response){
  				if(response.status==1) {
  					alert("上传失败!");
  					return;
  				}else{
  					console.log(file);
  					$('#viewImg').attr('src',response.data.picUrl);
  					$("#fileId").val(response.data.fileId);
  				}
  			}		
  		});
  		
  		
  	});
  	
  	function fun_addUser(){
			var user = {};
			var id = $("#id").val();
			var name = $("#userName").val();
			var passWord = $("#passWord").val();
			user.id = id;
			user.userName = name;
			user.passWord = passWord;
			user.fileId = $("#fileId").val();
			
			console.log(user);
			
			$.ajax({
  				url: contextPath+"/test/addOrUpdate",
  				type: "post",
  			    dataType: "JSON",
  				data: user,
  				success:function(result){
  					if(result.status==0){
  						alert("操作成功!");
  					}else{
  						alert("操作失败!");
  					}
  				},
  				error:function(result){
  					alert("系统异常");
  				}
  			});
  	}
  </script>
</html>
