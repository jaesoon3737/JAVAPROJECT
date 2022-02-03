<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, mvc.domain.Address" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
//	private int getSeq(HttpServletRequest request){
		// HttpServletRequest : 서비스에서만 사용할 수 있는 객체
//		int seq = -1;
//		String seqStr = request.getParameter("seq");
//	if(seqStr != null){
//		seqStr = seqStr.trim();
//		if(seqStr.length() != 0){
//			try{
///				return seq;
//				} catch(NumberFormatException ne){
//				}
//			}
//		}
//		return seq;
//	}
%>

<meta charset='utf-8'>
<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
    th, td { padding: 5px; }
    a { text-decoration: none}
</style>
<center>
    <hr width='600' size='2' noshade>
    <h2>Simple Board (MVc) jsjs</h2>
    &nbsp;&nbsp;&nbsp;
    <a href='boa.do?m=input'>글쓰기</a>
    <hr width='600' size='2' noshade>
    <table border='1' width='600' align='center' cellpadding='3'>
<c:if test="${!empty seq}">
		<tr>
			<td align='center'>content가 없습니다..</td>
		</tr>
</c:if>
<c:forEach items="${content}" var="address">
	<tr>
					<td width='100' align='center'>글번호</td>
						<td>${address.seq}</td>
					</tr>
					<tr>
						<td align='center'>글쓴이</td>
						<td>${address.writer}</td>
					</tr>
					<tr>
						<td align='center'>이메일</td>
						<td>${address.email}</td>
					</tr>
					<tr>
						<td align='center'>글제목</td>
						<td>${address.subject}</td>
					</tr>
					<tr>
						<td align='center'>글내용</td>
						<td>${address.content}</td>
					</tr>
				 </table>
    <hr width='600' size='2' noshade>
    <b>
        <a href='boa.do?m=updateForm&seq=${address.seq}'>수정 </a>
        <a href='boa.do?m=delete&seq=${address.seq}&writer=${address.writer}'>삭제 </a>
        <a href='boa.do?m=list'>목록</a>
    </b>
    <hr width='600' size='2' noshade>
</c:forEach>

<%
	//int seq = getSeq(request);
	
	//if(seq != -1){
	////	ArrayList<Address> contentList = (ArrayList<Address>)request.getAttribute("content");
	//	if(contentList != null){
	//		int size = contentList.size();
	//		if(size != 0){
	//			for(Address dto : contentList){
%>
<%
		//		}
		//	}
	//	} else {%>
		
<%		//}
//	}
%>				

    </table>

</center>