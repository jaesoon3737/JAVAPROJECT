<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:useBean id="member" class="Member.domain.Member" scope="page">
 <jsp:setProperty name="member" property="*"/>
 </jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form name='fs' method="post" action="../member/mController?message=signup">
		<input type="email"  id="email" name="email" value = "${MemberInfoz.email}" placeholder="이메일">
		<input type="password"  id="pwd" name="pwd" value = "${MemberInfoz.pwd}" placeholder="비밀번호">
		<input type="text"  id="name"  name="name" value = "${MemberInfoz.memName}" placeholder="이름">		<br>
		<input type="date"  id="birth" value = "${MemberInfoz.birth}" placeholder="생일">	<br>
		<input type="text"  id="nick"  value = "${MemberInfoz.nick}"  placeholder="닉네임">	<br>
		<input type="tel"   id="phone" value = "${MemberInfoz.memPhone}" placeholder="전화번호">	<br>
		<input type="date"  id="anni"  value = "${MemberInfoz.anni}"  placeholder="기념일">	<br>
		<input type="text"  class="form-control" name = "address" value = "${MemberInfoz.memLoc}" placeholder="주소">
		<input type="text"  class="form-control" name = "gender" value = "${MemberInfoz.gender}" placeholder="성별" disabled>
		<input type="radio" id ="solo" name="couple" value="0" checked>
		<label for="solo">없음</label>
		<input type="radio" id ="coupleC" name="couple" value="1" >
		<label for="coupleC">커플</label><br>
		<br>	
		<input type="radio" id ="defaultLicense" name="license" value="0" checked>
		<label for="defaultLicense">면허없음</label>
		<input type="radio" id ="licenseC" name="license" value="1" >
		<label for="licenseC">면허있음</label><br>
</form>
</body>
</html>