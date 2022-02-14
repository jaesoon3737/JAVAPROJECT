<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	//boolean flag = (Boolean)request.getAttribute("updates");
%>

<script>
	if(${flag}){
		alert("닉네임 변경 성공");
	}else{
		alert("닉네임 변경 실패");
	}
	location.href="../member/mController?message=memberManagement";
</script>