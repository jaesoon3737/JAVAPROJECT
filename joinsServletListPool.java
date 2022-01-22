package soo.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class joinsServletListPool extends HttpServlet {
	private ConnectionPoolBean getPool() throws SQLException{
		ServletContext application = this.getServletContext(); 
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool"); 
		if(pool == null){
			try{
				pool = new	ConnectionPoolBean();
				application.setAttribute("pool" , pool);
			}catch(ClassNotFoundException cn){
				System.out.println("드라이버 로딩 실패");
			}
		} 
		return pool;
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { 
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id").trim();
		String password = req.getParameter("password").trim();
		String name = req.getParameter("name").trim();
		res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
		pw.println("<script>");
		Connection con = null;
		PreparedStatement ptst = null;
		ConnectionPoolBean pool = null;
		String inseSQL = "insert into Members values ( ? , ? , ?)";
		try{
			pool = getPool();
			con = pool.getConnection();
			ptst = con.prepareStatement(inseSQL);
			ptst.setString(1, id);
			ptst.setString(2, password);
			ptst.setString(3, name);
			int i = ptst.executeUpdate();
			if(i>0){
				pw.println("alert('회원가입 성공')");
				pw.println("location.href='/db1'");
			}else{
				pw.println("alert('회원가입 실패 동일한 아이디가 존재합니다')");
				pw.println("location.href='/db1'");
			}
		}catch(SQLException se){
			pw.println("alert('회원가입 실패 동일한 아이디가 존재합니다')");
			pw.println("location.href='/db1'");
		}finally{
			try{
				if(ptst != null) ptst.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		
		}
		//pw.println("<a href='list.do'>리스트</a><br/>");  // 얘는 바로 못가니까
		// 자바스크립트 로케이션 잡아주면 이리로 바로 돌아감
		pw.println("</script>");

	}
}

