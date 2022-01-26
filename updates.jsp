<%@ page contentType="text/html;charset=utf-8"%>
<jsp:useBean id="addrDAO" class="soo.mv.model.AddrDAO" scope="application"/>
<jsp:useBean id="dto" class="soo.mv.model.AddrDTO"/>
<jsp:setProperty name="dto" property="*"/>


<%		
		int eriCount = 0;
		String eContst = request.getParameter("eriCount").trim();
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		String title = request.getParameter("title").trim();
		String contents = request.getParameter("contents").trim();
		if(eContst != null){
			eriCount = Integer.parseInt(eContst);
		}	
		System.out.println();
	
		boolean flag = addrDAO.update2(name , title ,contents ,eriCount);	
%>
		<script>
			if(<%=flag%>){
					alert("dbdb수정성공");
			}else{
					alert("dbdb수정실패");
			}
		location.href='list.jsp';
		</script>


