<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList , soo.mv.model.AddrDTO"%>
<jsp:useBean id="addrDAO" class="soo.mv.model.AddrDAO" scope="application"/>
<jsp:useBean id="dto" class="soo.mv.model.AddrDTO"/>
<jsp:setProperty name="dto" property="*"/>

<%!
	private int getSeq(HttpServletRequest request){
	    // HttpServletRequest : 서비스에서만 사용할 수 있는 객체
	    int seq = -1;
	    String seqStr = request.getParameter("eriCount");
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
		<h2>Simple Board with dd</h2>
		&nbsp;&nbsp;&nbsp;
		<a href='input.jsp'>글쓰기</a>
		<hr width='600' size='2' noshade>
		<table border='1' width='600' align='center' cellpadding='3'>
<%
	
	int eriCount = getSeq(request);
	  ArrayList<AddrDTO> lists = addrDAO.contents(eriCount);
	if(lists != null){
		int size = lists.size();
		if(size !=0){
			for(AddrDTO dtos : lists){
%> 		

		<tr>
		<td width='100' align='center'>글번호</td>
		<td><%=dtos.getEricount()%></td>
		</tr>
		<tr>
		<td width='100' align='center'>아이디</td>
		<td><%=dtos.getId()%></td>
		</tr>
		<tr>
		<td width='100' align='center'>닉네임</td>
		<td><%=dtos.getName()%></td>
		</tr>
		<tr>
		<td width='100' align='center'>제목</td>
		<td><%=dtos.getTitle()%></td>
		</tr>
		<tr>
		<td width='100' align='center'>내용</td>
		<td><%=dtos.getContents()%></td>
		</tr>
</table>
<%
			}
		}
	}
%>
		<a href='del.jsp?eriCount=<%=eriCount%>'>삭제</a>
		<a href='update.jsp?eriCount=<%=eriCount%>'>수정</a>
</center>
