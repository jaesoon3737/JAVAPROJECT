<%@ page contentType="text/html;charset=utf-8"%>
<jsp:useBean id="addrDAO" class="soo.mv.model.AddrDAO" scope="application"/>
<jsp:useBean id="dto" class="soo.mv.model.AddrDTO"/>
<jsp:setProperty name="dto" property="*"/>




<%		
	String SEQs = request.getParameter("eriCount");

	int seqss = 0;
	if(SEQs != null){
			SEQs = SEQs.trim();
			seqss = Integer.parseInt(SEQs);
		}
	
	boolean flag = addrDAO.delete(dto);
%>
		<script>
			if(<%=flag%>){
				alert("mm삭제성공");
				
			}else{
				alert("mm삭제실패");
				
			}
			location.href='list.jsp';
		</script>