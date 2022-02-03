<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	//boolean flag = (Boolean)request.getAttribute("updates");
%>

<script>
	if(${powerchange}){
		alert("JSTL용 권한변경완료");
	}else{
		alert("권한변경실패");
	}
	location.href="../boardk/clientLoginS?m=AllFind";
</script>


