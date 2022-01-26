<%@ page contentType="text/html;charset=utf-8" import="javax.sql.DataSource, java.sql.* , java.util.*"%>
<jsp:useBean id="dbcp" class="soo.dbcp.DbcpBean" scope="application"/>
<jsp:useBean id="addrDAO" class="soo.mv.model.AddrDAO" scope="application"/>
<html>
  <head>
    <title>DBCP</title>
	<meta charset='utf-8'>
	<script language="javascript">
	   function check()
	   {
	       for(var i=0; i<document.input.elements.length; i++)
		   {
		      if(document.input.elements[1].value == "")
			  {	
			     alert("모든 값을 입력 하셔야 합니다. ");
				 return false;
			  }
			  if(document.input.elements[2].value == "")
			  {	
			     alert("모든 값을 입력 하셔야 합니다. ");
				 return false;
			  }
			  if(document.input.elements[3].value == "")
			  {	
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
		th, td {
		   padding: 5px;
		}
		a { text-decoration:none }
	</style>
  </head>
  <body onload="input.name.focus()">
    <center>
	   <hr width="600" size="2" noshade>
	      <h2>DAO</h2>
		  <a href='list.jsp'>글목록</a>
		  
	   <hr width="600" size="2" noshade>
	</center>
	<form name="input" method="post" action="insert.jsp">
	   <table border="1" width="600" align="center"  cellpadding="3" cellspacing="1">
	      <tr>
		     <td width="30%" align="center">아이디</td>
			 <td align="center"><input type="text" name="id" size="60" ></td>
		  </tr>
		  <tr>
		     <td align="center">닉네임</td>
			 <td align="center"><input type="text" name="name" size="60"></td>
		  </tr>
          <tr>
		     <td align="center">제목</td>
			 <td align="center"><input type="text" name="title" size="60"></td>
		  </tr>
		  <tr>
		     <td align="center">내용</td>
			 <td align="center"><textarea name="contents" rows="5" cols="53"></textarea></td>
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