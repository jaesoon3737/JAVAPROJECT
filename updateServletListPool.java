package soo.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Vector;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.sql.Date;
import soo.db.ConnectionPoolBean;

public class updateServletListPool extends HttpServlet {
	String idu;
	private ConnectionPoolBean getPool() throws SQLException{
		ServletContext application = this.getServletContext(); // �������� ����Ÿ��
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool"); // Ǯ�� �ִٸ� ���� �� �����ʿ䰡 �����ϱ� Ǯ�̶�� �̸����� ��������ִٸ� 
		if(pool == null){
			try{
				pool = new ConnectionPoolBean();
				application.setAttribute("pool" , pool);
			}catch(ClassNotFoundException cn){
				System.out.println("����̹� �ε� ����");
			}
		} // Ǯ�� �ϳ��� �������ϴϱ� ������ �ɾ��ذ��� 
		return pool;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { //SQL������ -> list.html 
		res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
		int eContss = 0;
		req.setCharacterEncoding("utf-8");
		String eContst = req.getParameter("eriCount").trim();
		String id = req.getParameter("id").trim();
		if(eContst != null){
			eContss = Integer.parseInt(eContst);
		}

			pw.println("<meta charset='utf-8'>");
			pw.println("<style>");
			pw.println("table, th, td {");
			pw.println("border: 1px solid black;");
			pw.println("border-collapse: collapse;");
			pw.println("}");
			pw.println("th, td {");
			pw.println("padding: 5px;");
			pw.println("}");
			pw.println("a { text-decoration:none }"); 
			pw.println("</style>");
	     	pw.println("<body onload='javascript:document.f.name.focus();'>");
			pw.println("<center>");	
			pw.println("<hr width='600' size='2' noshade>");
			pw.println("<h2>Simple Board with Servlet PoolUpdate</h2>");
			pw.println("<form name='f' method='post' action='update.do?eriCount="+eContss+"'>");
		
			//pw.println("<input type='hidden' name='idx' value='4'>");
			//pw.println("<input type='hidden' name='writer' value='������'");
			
			Connection con = null;
			Statement st = null;
			ConnectionPoolBean pool = null;
            ResultSet rs = null;
		try{
			pool = getPool();
			con = pool.getConnection();
			st = con.createStatement();
			String selecSQL = "select eriCount ,name ,title , borderdate , contents from Borders where eriCount ="+eContss+"";
			rs = st.executeQuery(selecSQL);
			ArrayList<Map<String,Object>> rsMetas = new ArrayList<Map<String,Object>>();
			while(rs.next()){
				Map<String,Object> rsMeta = new HashMap<String,Object>();
				rsMeta.put("eriCount" , rs.getInt("eriCount"));
				//rsMeta.put("id" , rs.getString("id"));
				rsMeta.put("name" , rs.getString("name"));
				rsMeta.put("title" , rs.getString("title"));
				rsMeta.put("borderdate" , rs.getString("borderdate"));
				rsMeta.put("contents", rs.getString("contents"));
				rsMetas.add(rsMeta);
			}

		String html = "";
		pw.println("<table border='1' width='600' align='center' cellpadding='3' cellspacing='1'>");
		for(Map<String,Object> ts : rsMetas){
			html += "<tr>";
			html += "<td width='100' align='center'>�۹�ȣ</td>";
			html += "<td align='center'><input type='text' name='eriCounts' size='60' value='"+ts.get("eriCount")+"' disabled></td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td width='100' align='center'>�۾���</td>";
			html += "<td align='center'><input type='text' name='id' size='60' value='"+loginServletListPool.ids+"'></td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td width='100' align='center'>�г���</td>";
			html += "<td align='center'><input type='text' name='name' size='60' value='"+ts.get("name")+"'></td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td width='100' align='center'>����</td>";
			html += "<td align='center'><input type='text' name='title' size='60' value='"+ts.get("title")+"' ></td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td width='100' align='center'>����</td>";
			html += "<td align='center'><textarea name='contents' rows='5' cols='53'></textarea></td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td colspan='2' align='center'>";
			html += "</tr>";
		}
		res.getWriter().append(html);
		pw.println("</table>");

		}catch(SQLException se4){
			System.out.println("" + se4);
		}finally{
			 try{
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}
		pw.println("<input type='submit' value='����'>");
		//pw.println("<a href='updates.do?eriCount="+eContss+"'>����</a>");
		pw.println("<a href='list.do'>���</a>");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</center>"); 

	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { //SQL������ -> list.html 
		PrintWriter pw = res.getWriter();
		int eContss = 0;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8"); 
		System.out.println("����  �������"+idu+loginServletListPool.ids);
		String eContst = req.getParameter("eriCount").trim();
		String id = req.getParameter("id").trim();
		String name = req.getParameter("name").trim();
		String title = req.getParameter("title").trim();
		String contents = req.getParameter("contents").trim();
		if(eContst != null){
			eContss = Integer.parseInt(eContst);
		}
		pw.println("<script>");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ConnectionPoolBean pool = null;
		try{
			pool = getPool();
			con = pool.getConnection();
			st = con.createStatement();
			String selecSQL1 = "select id from borders where eriCount ="+eContst;
			rs =st.executeQuery(selecSQL1);
			while(rs.next()){
			  idu = rs.getString("id");
			}
		}catch(SQLException se5){
			System.out.println("������Ʈ Űã�� ����" + se5);
		}
		if(idu.equalsIgnoreCase(loginServletListPool.ids)){
		try{
			String selecSQL = "update borders set name ='"+name+"' , title = '"+title+"' , contents = '"+contents+"' where eriCount ="+eContss+"";
			st.executeUpdate(selecSQL);
		}catch(SQLException se4){
			System.out.println("����  �������" + se4);
		}finally{
			try{
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		
		}
		pw.println("location.href='list.do'");
		pw.println("</script>");
		}else{
		pw.println("alert('�ڱⲨ�� ���������մϴ�')");
		pw.println("location.href='list.do'");
		pw.println("</script>");
		}
	}	

}

