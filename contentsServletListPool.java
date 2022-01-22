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

public class contentsServletListPool extends HttpServlet {
	private ConnectionPoolBean getPool() throws SQLException{
		ServletContext application = this.getServletContext(); // �������� ����Ÿ��
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool"); // Ǯ�� �ִٸ� ���� �� �����ʿ䰡 �����ϱ� Ǯ�̶�� �̸����� ��������ִٸ� 
		if(pool == null){
			try{
				pool = new	ConnectionPoolBean();
				application.setAttribute("pool" , pool);
			}catch(ClassNotFoundException cn){
				System.out.println("����̹� �ε� ����");
			}
		} // Ǯ�� �ϳ��� �������ϴϱ� ������ �ɾ��ذ��� 
		return pool;
	}
	public void service(HttpServletRequest req, HttpServletResponse res)
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
	     	pw.println("<center>");
			
			pw.println("<hr width='600' size='2' noshade>");
			pw.println("<h2>Simple Board with Servlet Pool</h2>");
			pw.println("&nbsp;&nbsp;&nbsp;");
			pw.println("<a href='input.html'>�۾���</a>");
			pw.println("<hr width='600' size='2' noshade>");
			System.out.println("1");
			Connection con = null;
			Statement st = null;	
            ResultSet rs = null;
			ConnectionPoolBean pool = null;
			String selecSQL = "select eriCount, id ,name ,title , borderdate , contents from Borders where eriCount ="+eContss+"";
		try{
			pool = getPool();
			con = pool.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(selecSQL);
			ArrayList<Map<String,Object>> rsMetas = new ArrayList<Map<String,Object>>();
			while(rs.next()){
				Map<String,Object> rsMeta = new HashMap<String,Object>();
				rsMeta.put("eriCount" , rs.getInt("eriCount"));
				rsMeta.put("id" , rs.getString("id"));
				rsMeta.put("name" , rs.getString("name"));
				rsMeta.put("title" , rs.getString("title"));
				rsMeta.put("borderdate" , rs.getString("borderdate"));
				rsMeta.put("contents", rs.getString("contents"));
				rsMetas.add(rsMeta);
			}

		String html = "";
		pw.println("<table border='1' width='600' align='center' cellpadding='3'>");
		pw.println("<table border='1' width='600' align='center' cellpadding='3'>");
		for(Map<String,Object> ts : rsMetas){
			html += "<div>";
			html += "<tr>";
			html += "<td width='100' align='center'>�۹�ȣ</td>";
			html += "<td>" + ts.get("eriCount") + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td width='100' align='center'>���̵�</td>";
			html += "<td>" + ts.get("id") + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td width='100' align='center'>�г���</td>";
			html += "<td>" + ts.get("name") + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td width='100' align='center'>����</td>";
			html += "<td>" + ts.get("title") + "</td>";
			html += "</tr>";
			html += "<tr>";
			html += "<td width='100' align='center'>����</td>";
			html += "<td>" + ts.get("contents") + "</td>";
			html += "</tr>";
		}
		res.getWriter().append(html);
		pw.println("</table>");
		}catch(SQLException se4){
			System.out.println("������ ����� �۾��� �������" + se4);
		}finally{
			try{
				if(st != null) st.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		
		}
		pw.println("<div ���� : ><a href='del.do?eriCount="+eContss+"&id="+id+"'>����</a></div>");
		pw.println("<div ���� : ><a href='update.do?eriCount="+eContss+"&id="+id+"'>����</a></div>");
		pw.println("<div ���� : ><a href='list.do'>���</a></div>");
		pw.println("</center>");
		
		
	}

}

