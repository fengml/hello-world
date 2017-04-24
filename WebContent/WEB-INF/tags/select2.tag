<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fsl" uri="/WEB-INF/tagTest.tld" %>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<script type="text/javascript" src="${contextPath}/js/common/jquery.min.js"></script> 

<%@ attribute name="typeCode" type="java.lang.String"  description="字典code"%>
<%@ attribute name="defaultValue" type="java.lang.String" description="默认选中"%>
<%@ attribute name="style" type="java.lang.String" description="默认选中"%>
<%@ attribute name="cls" type="java.lang.String" description="默认选中"%>


<%@ attribute name="id" type="java.lang.String" required="true" description="默认选中"%>
<%@ attribute name="name" type="java.lang.String" description="默认选中"%>
<%@ attribute name="dictName" type="java.lang.String" required="true" description="默认选中"%>
<%@ attribute name="itemCode" type="java.lang.String" description="默认选中"%>
<%@ attribute name="itemDesc" type="java.lang.String" description="默认选中"%>


<c:set var="str" value="${2000}"/>
<c:set var="message" value="${2000}"></c:set>

<select id="${id}" name="${name}" >
    <option value="" >请选择...      </option>
    <c:if test="${not empty dictName}">
	    <c:forEach items="${fsl:getDict('class','1')}" var='dict'>
	    	<c:choose>
	    		<c:when test="${dict.itemCode==1 }">
	    			<option value='${dict.itemCode}' selected="selected">${dict.itemDesc}</option>
	    		</c:when>
	    		<c:otherwise>
	    			<option value='${dict.itemCode}'>${dict.itemDesc}</option>
	    		</c:otherwise>
	    	</c:choose>
	    </c:forEach>
    </c:if>
</select>


