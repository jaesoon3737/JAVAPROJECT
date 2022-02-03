<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, mvc.domain.Address"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset='utf-8'>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td {padding: 5px;}
    a {text-decoration: none}
</style>
<center>
    <hr width='600' size='2' noshade>
    <h2>Simple Board MVC~! + JSTL+EL</h2>
    &nbsp;&nbsp;&nbsp;
    <a href='boa.do?m=input'>글쓰기</a>
    &nbsp;&nbsp;&nbsp;
    <a href='../'>인덱스</a>
    <hr width='600' size='2' noshade>


<a href='../boardk/clientLoginS?m=logout'>로그아웃</a>       
<p><%=session.getAttribute("J_id") %>님 안녕하세요</p>
<p><%=session.getAttribute("J_power") %>등급으로 로그인 중</p>

<%
String Jpower =  (String)session.getAttribute("J_power");
if(Jpower.equals("병아리")){
%>
<a href='boa.do?m=input'>글쓰기</a>
<%
}else{
	if(Jpower.equals("관리자")){
%>
<a href='boa.do?m=input'>글쓰기</a>
<a href='../boardk/clientLoginS?m=AllFind'>회원목록보기</a>
<% 
	}
}
%>

</center>
<table border='1' width='600' align='center' cellpadding='2'>
    <tr>
        <th align='center' width='10%'>글번호</th>
        <th align='center' width='15%'>작성자</th>
        <th align='center' width='30%'>이메일</th>
        <th align='center' width='30%'>글제목</th>
        <th align='center' width='15%'>날짜</th>
    </tr>
    
<c:if test="${empty list}">
<tr>
	<td colspan="5" style="text-align:center">data가 없습니다.</td>
</tr>  
</c:if>   
<c:forEach items="${list}" var="address" >

				<tr>
					<td align='center'>${address.seq} </td>
					<td align='center'>${address.writer}</td>
					<td align='center'>${address.email}</td>
					<td align='center'><a href='boa.do?m=content&seq=${address.seq}'>${address.subject}</a></td>
					<td align='center'>${address.rdate}</td>
				</tr>
</c:forEach> 
<%	
	//ArrayList<Address> list = (ArrayList<Address>)request.getAttribute("list");
	//if(list != null){
		////int size = list.size();
		//if(size != 0){
			//for(Address dto : list){
%>
<%			
			//}
	//	}else{
%>
	
<%
	//	}
//	}
%>	
</table>
<hr width='600' size='2' noshade>



