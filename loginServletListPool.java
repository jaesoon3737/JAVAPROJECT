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
public class loginServletListPool extends HttpServlet {
	static String ids;
	private ConnectionPoolBean getPool() throws SQLException{
		ServletContext application = this.getServletContext(); // 스코프의 방의타입
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool"); // 풀이 있다면 굳이 또 만들필요가 없으니까 풀이라는 이름으로 만들어져있다면 
		if(pool == null){
			try{
				pool = new	ConnectionPoolBean();
				application.setAttribute("pool" , pool);
			}catch(ClassNotFoundException cn){
				System.out.println("드라이버 로딩 실패");
			}
		} // 풀을 하나만 만들어야하니까 이프문 걸어준거임 
		return pool;
	}


	public void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { //SQL문수행 -> list.html 
		res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
        ResultSet rs = null;
			pw.println("<script>");

		Connection con = null;
		Statement st = null;
		ConnectionPoolBean pool = null;
		try{
			pool = getPool();
		    con = pool.getConnection();
			st = con.createStatement();
			String selecSQL = "select  id , password from Members";
			rs = st.executeQuery(selecSQL);
			while(rs.next()){
				ids =  rs.getString("id");
                String passwords = rs.getString("password");
				String passwordreq = req.getParameter("password").trim();
				String idreq = req.getParameter("id").trim();
				if(ids.equalsIgnoreCase(idreq) && passwords.equalsIgnoreCase(passwordreq)){
					    pw.println("location.href='list.do'");
						break;
				}else{
				if(passwords.equalsIgnoreCase(passwordreq)){ 
						pw.println("alert('아이디 확인해주세요')");
						pw.println("location.href='../'");
				}else if(ids.equalsIgnoreCase(idreq)){
						pw.println("alert('비밀번호 확인해주세요')");
						pw.println("location.href='../'");
				}pw.println("location.href='../'");
				}
			}
				pw.println("</script>");
		}catch(SQLException se4){
			System.out.println("아이디찾기 실패" + se4);
			pw.println("location.href='../'");
		}finally{
			try{
				if(st != null) st.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}
		pw.println("</center>");
	}

}

