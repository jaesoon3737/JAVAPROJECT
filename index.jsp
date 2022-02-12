<%@ page contentType="text/html; charset=UTF-8"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!doctype html>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script language="javascript">
       function f_join()
       {
           Member_login = window.open(
           "Member/singup.jsp", "Jeju Sign Up", 
                "width=600, height=900, top=100, left=100");
       }
</script>
<ul>
<li class="nav-item" id="signupLogout"><a id="signup" class="nav-link" onclick = "f_join()">Sign Up</a></li>
</ul>
</body>
</html>