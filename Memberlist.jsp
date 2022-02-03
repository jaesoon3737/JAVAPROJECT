<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, mvc.domain.Address" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
	private int getSeq(HttpServletRequest request){
		// HttpServletRequest : 서비스에서만 사용할 수 있는 객체
		int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr);
					return seq;
				} catch(NumberFormatException ne){
				}
			}
		}
		return seq;
	}
%>
<%
	int seq = getSeq(request);
%>

<meta charset='utf-8'>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td { padding: 5px; }
    a { text-decoration: none }
</style>

<body onload='javascript:document.f.email.focus();'>
    <center>
        <hr width='600' size='2' noshade>
        <h2>회원관리</h2>
        <a href='list.jsp'>글목록</a>
        <hr width='600' size='2' noshade>
    </center>
 <form name='f' method='post' action='../boardk/clientLoginS?m=powerchange'>       
 <table border='1' width='600' align='center' cellpadding='3' cellspacing='1'>
 <tr>
 	<th width='10%' align='center'>아이디</th>
	<th width='10%' align='center'>이메일</th>
	<th width='10%' align='center'>닉네임</th>
	<th width='10%' align='center'>권한</th>
	<th colspan="3" align='center'>변경</th>
</tr>					
<a href='../boardk/clientLoginS?m=logout'>로그아웃</a>       
<p><%=session.getAttribute("J_id") %>님 안녕하세요</p>
<p><%=session.getAttribute("J_power") %>등급으로 로그인 중</p>	
<c:if test="${empty All}">
<tr>
	<td colspan="5" style="text-align:center">data가 없습니다.</td>
	
</tr> 
</c:if>

<tr>
<td align='center'>
<select name ="fnames"> 
<c:forEach items="${All}" var="addressk">
<option value="${addressk.id}">${addressk.id}</option>
</c:forEach>
</select>
</td>
		<td align='center'>
		<select name ="fname"> 
		<option value="병아리" selected="selected">병아리</option>
		<option value="고양이">고양이</option>
		<option value="호랑이">호랑이</option>
		<option value="관리자">관리자</option>
		</select>
		</td>
		<td>
		<input type="submit" name = "checked" value="권한변경"/>
		</td>
<td><input type='text' name='nickChange' size='10' placeholder = "변경할 닉네임" ></td>
<td align='center'><input type="submit" name = "checked" value="닉네임변경"/></td>

</tr>

<c:forEach items="${All}" var="address">
	<input type='hidden' name='ids' value='${address.id}'>				
	<tr>
		<td align='center'>${address.id}</td>
		<td align='center'>${address.email}</td>
		<td align='center'>${address.nickName}</td>
		<td align='center'>${address.power}</td>
		
	</tr>		
</c:forEach>	
</table>
</form>
</body>
