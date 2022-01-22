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

public class AddrServletListPool extends HttpServlet {
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
		pw.println("<a href='input.html'>�۾���</a>"); // html Ȯ��
		pw.println("<a href='/db1/index.html'>�α׾ƿ�</a>");
	    pw.println("<hr width='600' size='2' noshade>");
		pw.println("</center>");

		ConnectionPoolBean pool = null;
        ResultSet rs = null;
		Connection con = null;
		Statement st = null;
		try{
			pool = getPool();
			con = pool.getConnection();
			st = con.createStatement();
			String selecSQL = "select eriCount, id ,name ,title , borderdate  from Borders ORDER BY eriCount DESC";
			rs = st.executeQuery(selecSQL);
			ArrayList<Map<String,Object>> rsMetas = new ArrayList<Map<String,Object>>();
			while(rs.next()){
				Map<String,Object> rsMeta = new HashMap<String,Object>();
				rsMeta.put("eriCount" , rs.getInt("eriCount"));
				rsMeta.put("id" , rs.getString("id"));
				rsMeta.put("name" , rs.getString("name"));
				rsMeta.put("title" , rs.getString("title"));
				rsMeta.put("borderdate" , rs.getString("borderdate"));
				rsMetas.add(rsMeta);
			}

			String html = "";
			pw.println("<table border='1' width='600' align='center' cellpadding='2'>");
			pw.println("<tr>");
			pw.println("<th align='center' width='10%'>�۹�ȣ</th>");
			pw.println("<th align='center' width='15%'>�ۼ���</th>");
			pw.println("<th align='center' width='15%'>�г���</th>");
			pw.println("<th align='center' width='30%'>������</th>");
			pw.println("<th align='center' width='15%'>��¥</th>");
			pw.println("</tr>");
			for(Map<String,Object> ts : rsMetas){
				html += "<div>";
				html += "<tr>";
				html += "<td align='center'>" + ts.get("eriCount") + "</td>";
				html += "<td align='center'>" + ts.get("id") + "</td>";
				html += "<td align='center'>" + ts.get("name") + "</td>";
				html += "<td align='center'>";
				html += "<a href='contents.do?eriCount="+ts.get("eriCount")+"&id="+ts.get("id")+"'>" + ts.get("title") + "</td>";
				html += "<td align='center'>" + ts.get("borderdate") + "</td>";
		}
		
		res.getWriter().append(html);
		pw.println("</table>");
		pw.println("<hr width='600' size='2' noshade>");
		
		}catch(SQLException se4){
			System.out.println("������ ����� �۾��� �������" + se4);
		}finally{
			try{
				if (rs != null) rs.close();
				if (st != null) st.close();
				if (con != null) pool.returnConnection(con);
			}catch(SQLException se1){
			}
		
		}
		pw.println("</center>");
	}

}

