<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	//boolean flag = (Boolean)request.getAttribute("updates");
%>

<script>
	if(${nickchange}){
		alert("JSTL용 닉네임변경완료");
	}else{
		alert("닉네임변경실패");
	}
	location.href="../boardk/clientLoginS?m=AllFind";
</script>


