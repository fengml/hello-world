<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../common/comm_const_tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
	<!-- <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> -->
	<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	
	<script type="text/javascript" src="${contextPath}/js/common/jquery.min.js"></script>
  </head>
	<style>
		 body{
		   boder: none;
		   margin: 0px;
		 }
	 </style>

  <body>
  	<iframe border="3" id="frame3d" name="frame3d" width="100%" height="100%" src="${src }">
    <%-- <iframe src="${src }"></iframe> --%>
    
</iframe>
  </body>
  <script type="text/javascript">
     //   !function(e,i){var t=e.documentElement,n=navigator.userAgent.match(/iphone|ipod|ipad/gi),a=n?Math.min(i.devicePixelRatio,3):1,m="orientationchange"in window?"orientationchange":"resize";t.dataset.dpr=a;for(var d,l,c=!1,o=e.getElementsByTagName("meta"),r=0;r<o.length;r++)l=o[r],"viewport"==l.name&&(c=!0,d=l);if(c)d.content="width=device-width,initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0,user-scalable=no";else{var o=e.createElement("meta");o.name="viewport",o.content="width=device-width,initial-scale=1.0,maximum-scale=1.0, minimum-scale=1.0,user-scalable=no",t.firstElementChild.appendChild(o)}var s=function(){var e=t.clientWidth;e/a>640&&(e=640*a),window.remScale=e/640,t.style.fontSize=200*(e/640)+"px"};s(),e.addEventListener&&i.addEventListener(m,s,!1)}(document,window);
       var height = $(window).height();
       $("#frame3d").height(height);
   </script>
</html>
