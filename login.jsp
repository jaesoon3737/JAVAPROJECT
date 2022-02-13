<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta charset="utf-8">
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
<body onload="document.f.name.focus()">

<center>
   <h1>
		JEJU 
   </h1>
   
   <form name="f" action="../member/mController?message=login" method="post">
       <table border="1" width="300" height="200">
	      <tr>
		     <td width="30%" colspan="2" align="center"><h2>로그인</h2></td> 
		  </tr>
		  <tr>
		     <th width="30%">Email</th> 
			 <td><input type="email" name="email" align="center" size="20" align="center" placeholder="email"></td>
		  </tr>
		  <tr>
		     <th width="30%">비밀번호</th> 
			 <td><input type="password" name="pwd" size="20" align="center" placeholder="password"></td>
		  </tr>
		  <tr>
		     <td colspan="2" align="center">
			     <input type="submit" value="전송"/>
				 <input type="reset" value="취소"/>
			 </td> 
		  </tr>
	   </table>
   </form>
   <a href='../member/mController?message=signform'>회원가입</a>
</center>
</body>