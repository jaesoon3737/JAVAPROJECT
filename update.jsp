<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList , soo.mv.model.AddrDTO"%>
<jsp:useBean id="addrDAO" class="soo.mv.model.AddrDAO" scope="application"/>
<jsp:useBean id="dto" class="soo.mv.model.AddrDTO"/>
<jsp:setProperty name="dto" property="*"/>
<%
			String ee = request.getParameter("eriCount");
			String id = request.getParameter("id");
			int eriCount = Integer.parseInt(ee);
			System.out.println("ff"+eriCount);
%>
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
	     	<body onload='javascript:document.f.name.focus();'>
			<center>
			<hr width='600' size='2' noshade>
			<h2>Simple Board with Servlet dto</h2>
			<form name='f' method='post' action='updates.jsp?eriCount=<%=eriCount%>'>

			<table border='1' width='600' align='center' cellpadding='3' cellspacing='1'>
<%		
		ArrayList<AddrDTO> list = addrDAO.update1(eriCount,id);
			if(list != null){
				int size = list.size();
				if(size !=0){
					for(AddrDTO dtos : list){
%>
			<tr>
			<td width='100' align='center'>글번호</td>
			<td align='center'><input type='text' name='eriCount' size='60' value='<%=dtos.getEricount()%>' disabled></td>
			</tr>
			<tr>
			<td width='100' align='center'>글쓴이</td>
			<td align='center'><input type='text' name='id' size='60' value='<%=dtos.getId()%>'></td>
			</tr>
			<tr>
			<td width='100' align='center'>닉네임</td>
			<td align='center'><input type='text' name='name' size='60' value='<%=dtos.getName()%>'></td>
			</tr>
			<tr>
			<td width='100' align='center'>제목</td>
			<td align='center'><input type='text' name='title' size='60' value='<%=dtos.getTitle()%>' ></td>
			</tr>
			<tr>
			<td width='100' align='center'>내용</td>
			<td align='center'><textarea name='contents' rows='5' cols='53'></textarea></td>
			</tr>
			<tr>
			<td colspan='2' align='center'>
			</tr>
<%					}
				}
			}
		
		%>
			</table>
				<input type='submit' value='수정'>
				<a href='list.jsp'>목록</a>
				</form>
				</body>
				</center>
