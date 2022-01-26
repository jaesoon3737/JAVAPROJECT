<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList , soo.mv.model.AddrDTO"%>
<jsp:useBean id="addrDAO" class="soo.mv.model.AddrDAO" scope="application"/>
<jsp:useBean id="dto" class="soo.mv.model.AddrDTO"/>
<jsp:setProperty name="dto" property="*"/>

		<meta charset='utf-8'>
		<style>
		table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
		}
		th, td {
		padding: 5px;
		}
		a { text-decoration:none }
		</style>
	<center>
		<hr width='600' size='2' noshade>
		<h2>Simple Board with JSP  MV</h2>
		<a href='input.jsp'>글쓰기</a>
		&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href='/db1/index.html'>로그아웃</a>
	    <hr width='600' size='2' noshade>
		</center>
		<table border='1' width='600' align='center' cellpadding='2'>		
		<tr>
			<th align='center' width='10%'>글번호</th>
			<th align='center' width='15%'>작성자</th>
			<th align='center' width='15%'>닉네임</th>
			<th align='center' width='30%'>글제목</th>
			<th align='center' width='15%'>날짜</th>
		</tr>
			
<%		
		ArrayList<AddrDTO> list = addrDAO.list();
		if(list != null){
			int size = list.size();
			if(size !=0){
				for(AddrDTO dtos : list){
%>
			<tr>
			<td align='center'><%=dtos.getEricount()%> </td>
			<td align='center'><%=dtos.getId()%></td>
			<td align='center'><%=dtos.getName()%></td>
			<td align='center'><a href='contents.jsp?eriCount=<%=dtos.getEricount()%>'><%=dtos.getTitle()%></a></td>
			<td align='center'><%=dtos.getBorderdate()%></td>
			
<%
		
				}
			}
		}

%>
			</table>
			<hr width='600' size='2' noshade> 
			</center>
		