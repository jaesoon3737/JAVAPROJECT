<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	//boolean flag = (Boolean)request.getAttribute("updates");
%>

<script>
	if(${updates}){
		alert("update JSTL 은 다 데헷");
	} else{
		alert("update 데 ㅔ헤 ㅅ");
	}
	location.href="boa.do?m=list";
</script>


