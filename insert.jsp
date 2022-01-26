<%@ page contentType="text/html;charset=utf-8" import="javax.sql.DataSource, java.sql.* , java.util.*"%>
<jsp:useBean id="addrDAO" class="soo.mv.model.AddrDAO" scope="application"/>
<jsp:useBean id="dto" class="soo.mv.model.AddrDTO"/>
<jsp:setProperty name="dto" property="*"/>
<%
		boolean flag = addrDAO.insert(dto);
%>

		<script>
			if(<%=flag%>){
				alert("DA입력성공");
				
			}else{
				alert("DA입력실패");	
			}
		
		location.href='list.jsp';// �ڹٽ�ũ��Ʈ �����̼� ����ָ� �̸��� �ٷ� ���ư�
		</script>

