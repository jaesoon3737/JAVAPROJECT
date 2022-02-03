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
        <h2>Simple Board (MVc) JSTL</h2>
        <a href='list.jsp'>글목록</a>
        <hr width='600' size='2' noshade>
    </center>
    <form name='f' method='post' action='boa.do?m=update&seq=<%= seq%>'>
        <input type='hidden' name='seq' value='<%= seq%>'>
        
        <table border='1' width='600' align='center' cellpadding='3' cellspacing='1'>
<a href='../boardk/clientLoginS?m=logout'>로그아웃</a>       
<p><%=session.getAttribute("J_id") %>님 안녕하세요</p>
<p><%=session.getAttribute("J_power") %>등급으로 로그인 중</p>	
<c:if test="${empty uplist}">
<tr>
	<td colspan="5" style="text-align:center">data가 없습니다.</td>
</tr> 
</c:if>
<c:forEach items="${uplist}" var="address">
					<input type='hidden' name='writer' value='${address.writer}'>
					<tr>
						<td width='30%' align='center'>글쓴이</td>
						<td align='center'><input type='text' name='writer' size='60' value='${address.writer}' disabled></td>
					</tr>
					<tr>
						<td width='30%' align='center'>이메일</td>
						<td align='center'><input type='text' name='email' size='60' value='${address.email}' disabled></td>
					</tr>
					<tr>
						<td width='30%' align='center'>글제목</td>
						<td align='center'><input type='text' name='subject' size='60' value='${address.subject}'></td>
					</tr>
					<tr>
						<td width='30%' align='center'>글내용</td>
						<td align='center'><textarea name='content' rows='5' cols='53'>${address.content}</textarea></td>
					</tr>
</c:forEach>		
<%
//	if(seq != -1){
	//	ArrayList<Address> updateFormList = (ArrayList<Address>)request.getAttribute("uplist");
	//	if(updateFormList != null){
	//		int size = updateFormList.size();
		//	if (size != 0){
		//		for(Address dto : updateFormList){
%>
				
<%
		//		}
	//		}
	//	}
//	} else{
//		System.out.println("데이터가 없습니다..");
//	}
%>
            <tr>
                <td colspan='2' align='center'>
                    <input type='submit' value='수정'>
                </td>
            </tr>
        </table>
    </form>
</body>
