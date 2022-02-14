<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>       
<p><%=session.getAttribute("Member_Email") %>님 안녕하세요</p>
<p>${sessionScope.Member_Email} 세션 스코프님 안녕하세요</p> 
<p><%=session.getAttribute("Member_Number") %>고유 번호</p>
<p><%=session.getAttribute("Member_Name") %>님 안녕하세요</p>
<p><%=session.getAttribute("Member_Nick") %>닉네임이군요</p>
<p><%=session.getAttribute("Member_Phone") %>핸드폰번호군요</p>
<p><%=session.getAttribute("Member_Birth") %>생일이시군요</p>
<p><%=session.getAttribute("Member_Grade") %>등급으로 로그인 중</p>

<c:choose>
	<c:when test="${Member_Grade == 'admin'}">
		<span><a href="../member/mController?message=myPage" class="basicLogin_item">마이페이지</a> </span>
		<span><a href="../member/mController?message=memberManagement" class="basicLogin_item">회원관리</a> </span>
		<span><a href='../member/mController?message=logout' class="basicLogin_item">로그아웃</a> </span>
	</c:when>
	<c:when test="${ not empty Member_Email}">
		<span><a href="../member/mController?message=myPage" class="basicLogin_item">마이페이지</a> </span>
		<span><a href='../member/mController?message=logout' class="basicLogin_item">로그아웃</a> </span>
	</c:when>
	<c:otherwise>
		<span><a href="../member/mController?message=loginform" class="basicLogin_item">로그인</a> </span>
		<span><a href="../member/mController?message=signform" class="basicLogin_item">회원가입</a> </span>
	</c:otherwise>
</c:choose>
</body>
</html>