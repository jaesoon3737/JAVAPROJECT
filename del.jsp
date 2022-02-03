<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%	
	//	boolean flag = (Boolean)request.getAttribute("flags");
%>
<script>
	if(${flags}){
		alert("삭제 성공! 데헷2 ");
	} else{
		alert("삭제 실패! 데헷 ");
	}
	location.href="boa.do?m=list";
</script>


