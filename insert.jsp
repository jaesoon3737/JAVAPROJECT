<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	//boolean flag = (Boolean)request.getAttribute("flag");
%>

<script>
	if(${flag}){
		alert("insert 성공! 데헷(MVC) ");
	} else{
		alert("isnert 실패! 잇");
	}
	location.href="boa.do?m=list";
</script>
