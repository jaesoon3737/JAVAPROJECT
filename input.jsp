<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, mvc.domain.Address"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String kname = (String)session.getAttribute("J_id");
%>
<html>
  <head>
    <title>간단한 게시판 input</title>
	<meta charset='utf-8'>
	<script language="javascript">
		function check(){
		   for(var i=0; i<document.input.elements.length; i++){
				if(document.input.elements[i].value == ""){
					alert("모든 값을 입력 하셔야 합니다. ");
					return false;
				}
			}
			document.input.submit();
		}
	</script>
	<style>
		table, th, td {
		   border: 1px solid black;
		   border-collapse: collapse;
		}
		th, td { padding: 5px;}
		a { text-decoration:none }
	</style>
  </head>
 <body onload="input.name.focus()">
	<center>
		<hr width="600" size="2" noshade>
		<h2>Simple Board (MVC) JSTL</h2>
		<a href='list.jsp'>글목록</a>
		<hr width="600" size="2" noshade>
<a href='../boardk/clientLoginS?m=logout'>로그아웃</a>       
<p><%=session.getAttribute("J_id") %>님 안녕하세요</p>
<p><%=session.getAttribute("J_power") %>등급으로 로그인 중</p>
	</center>
    <form name="input" method="post" action="../addr/boa.do?m=insert">
        <table border="1" width="600" align="center" cellpadding="3" cellspacing="1">
            <tr>
                <td width="30%" align="center">글쓴이</td>
                <td align="center"><input type="text" name="writer" size="60" value ="<%=kname%>" disabled></td>
            </tr>
            <tr>
                <td align="center">이메일</td>
                <td align="center"><input type="text" name="email" size="60" value ="<%=session.getAttribute("J_email")%>" disabled></td>
            </tr>
            <tr>
                <td align="center">글제목</td>
                <td align="center"><input type="text" name="subject" size="60"></td>
            </tr>
            <tr>
                <td align="center">글내용</td>
                <td align="center"><textarea name="content" rows="5" cols="53"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="button" value="전송" onclick="check()">
                    <input type="reset" value="다시입력">
                </td>
            </tr>
        </table>
        <br>
        <hr width="600" size="2" noshade>
    </form>
</body>
</html>