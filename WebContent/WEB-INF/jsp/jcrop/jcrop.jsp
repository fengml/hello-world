<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="appPath" value="${pageContext.servletContext.contextPath}${initParam.prePath}"/><%-- 写页面绝对路径时使用，如果需要在模块和应用上下文增加一级路径时候，这个定义的价值就体现出来了 --%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/><%-- 写静态资源时使用 --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>jcrop</title>
    <link rel="stylesheet" href="${contextPath}/css/jcrop/common.css" type="text/css" />
    <link rel="stylesheet" href="${contextPath}/css/jcrop/jquery.Jcrop.css" type="text/css"></link>
    <script type="text/javascript" src="${contextPath}/js/common/jquery.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jcrop/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jcrop/jcrop.js"></script>
    <style type="text/css">
.crop_preview{position:absolute; left:450px; top:0; width:120px; height:120px; overflow:hidden;}
</style>
  </head>
  <body>
   		<%-- <img src="${contextPath}/image/sago.jpg"> --%>
	<form name="form" action="${contextPath}/jcrop/jc2" class="form-horizontal" method="post" enctype="multipart/form-data">
		<div class="modal-body text-center">
			<div class="zxx_main_con">
				<div class="zxx_test_list">
					<input class="photo-file" type="file" name="imageFile" id="fcupload" onchange="readURL(this);" /> 
					<img alt="" src="" id="cutimg" />
					<span id="preview_box" class="crop_preview"><img style="border: 1px red solid" src="" id="crop_preview"> </span>
					<input type="hidden" id="x" name="x" /> 
					<input type="hidden" id="y" name="y" /> 
					<input type="hidden" id="w" name="w" /> 
					<input type="hidden" id="h" name="h" />
				</div>
			</div>
		</div>
		
		<div class="modal-footer">
			<button id="submit" onclick="">上传</button>
		</div>
	</form>

</body>
</html>
