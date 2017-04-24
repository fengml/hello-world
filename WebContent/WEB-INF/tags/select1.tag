<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fns" %>

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
    <c:if test="${not empty typeCode}">
       <%--  <c:forEach items="${fns:getDictList(typeCode)}" var='dict'>
            <option value='${dict.VALUE}' ${defaultValue==dict.VALUE?'selected':''}>${dict.TEXT}</option>
        </c:forEach> --%>
        
    </c:if>
</select>

<script type="text/javascript">
	$(function(){
		//alert('${str}')
		var selectId = '${id}';
		var dictName = '${dictName}';
		var itemCode = '${itemCode}';
		//console.log(selectId);
		var contextPath = '${contextPath}';
		var obj = {};
		obj.dictName = dictName;
		if(itemCode.replace(/(^s*)|(s*$)/g, "").length != 0){
			obj.itemCode = itemCode;
		}
		console.log(obj);
		$.ajax({
			url: contextPath+"/dictCode/getDict",
			type: "post",
			data: obj,
			dataType: "JSON",
			success: function(result){
				var list = result.data;
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					if(obj.itemCode == itemCode){
						var $str_option = $('<option value="" selected="selected">'+obj.itemDesc+'</option>');
					}else{
						var $str_option = $('<option value="">'+obj.itemDesc+'</option>');
					}
					
					$("#"+selectId).append($str_option);
				}
			},
			error:function(){
				console.log("系统繁忙!");
			}
		});
		
	})
</script>

