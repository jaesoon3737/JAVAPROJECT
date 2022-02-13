<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, Member.domain.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>MemberList</title>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td { padding: 5px; }
    a { text-decoration: none }
</style>
</head>
<body onload='javascript:document.f.email.focus();'>

        <hr width='600' size='2' noshade>
        <h2>회원관리</h2>
        <p>${sessionScope.Member_Email} 관리자님 안녕하세요</p>
        <p>${sessionScope.Member_Grade} 등급으로 로그인 중입니다.</p>  
        <span><a href='../member/mController?message=logout' class="basicLogin_item">로그아웃</a> </span>
        <a href='../Member/list.jsp'>List</a>
        <hr width='600' size='2' noshade>
    
		<c:choose>
		<c:when test="${sessionScope.Member_Grade == '관리자'}">
		<form name='f' method='post' action='../member/mController?message=memberManagementChange'>
			<select name ="fnames"> 
				<c:forEach items="${allList}" var="memberListValue">
					<option value="${memberListValue.email}">${memberListValue.email}</option>
				</c:forEach>
			</select> 
			<select name ="fname"> 
				<option value="nick">닉네임</option>
				<option value="grade">등급</option>
			</select>
			<input type='text' name='memberInfoChange' size='10' placeholder = "변경내용" >
			<input type="submit" name = "checked" value="수정"/> 
			  
			<table border='1' width='900' align='center' cellpadding='3' cellspacing='1'>
				 <tr>
				 	<th width='70' align='center'>이메일</th>
					<th width='40' align='center'>고유번호</th>
					<th width='40' align='center'>이름</th>
					<th width='50' align='center'>생일</th>
					<th width='30' align='center'>등급</th>
					<th width='50' align='center'>닉네임</th>
					<th width='50' align='center'>핸드폰번호</th>
					<th width='200' align='center'>주소</th>
					<th width='20' align='center'>성별</th>
					<th width='50' align='center'>기념일</th>
					<th width='30' align='center'>연애</th>
					<th width='30' align='center'>운전면허</th>
				 </tr>
			<c:if test="${empty allList}">
					<tr>
						<td colspan="5" style="text-align:center">data가 없습니다.</td>
					</tr> 
			</c:if>	
			<c:forEach items="${allList}" var="memberListValue">
						<input type='hidden' name='emails' value='${memberListValue.email}'>				
						<tr>
							<td align='center'><a href="../member/mController?message=memberManagementFindform&email=${memberListValue.email}">${memberListValue.email}</a></td>
							<td align='center'>${memberListValue.memNumber}</td>
							<td align='center'>${memberListValue.memName}</td>
							<td align='center'>${memberListValue.birth}</td>
							<td align='center'>${memberListValue.grade}</td>
							<td align='center'>${memberListValue.nick}</td>
							<td align='center'>${memberListValue.memPhone}</td>
							<td align='center'>${memberListValue.memLoc}</td>
							<td align='center'>${memberListValue.gender}</td>
							<td align='center'>${memberListValue.anni}</td>
							<td align='center'>${memberListValue.couple}</td>
							<td align='center'>${memberListValue.license}</td>
						</tr>		
			</c:forEach>		   
			</table>
			<select name ="Search"> 
				<option value="0">이메일</option>
				<option value="1">닉네임</option>
				<option value="2">등급</option>
			</select>
				<input type='text' name='memberInfoSearch' size='10' placeholder = "검색하세요" >
				<input type="submit" name = "checked" value="회원검색"/> 
			</form>
		</c:when>
		<c:otherwise>
			<p>권한 밖의 행동입니다. 리스트로 돌아가십시오.</p>
			<a href='../Member/list.jsp'>List</a>
		</c:otherwise>
		</c:choose>
   
</body>
</html>