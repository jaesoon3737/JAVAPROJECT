package soo.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class inServletListPool extends HttpServlet {
	static String id;
	private ConnectionPoolBean getPool() throws SQLException{
		ServletContext application = this.getServletContext(); // 스코프의 방의타입
		ConnectionPoolBean pool = (ConnectionPoolBean)application.getAttribute("pool"); // 풀이 있다면 굳이 또 만들필요가 없으니까 풀이라는 이름으로 만들어져있다면 
		if(pool == null){
			try{
				pool = new ConnectionPoolBean();
				application.setAttribute("pool" , pool);
			}catch(ClassNotFoundException cn){
				System.out.println("드라이버 로딩 실패");
			}
		} // 풀을 하나만 만들어야하니까 이프문 걸어준거임 
		return pool;
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { 
		req.setCharacterEncoding("utf-8");
		id = loginServletListPool.ids.trim();
		String name = req.getParameter("name").trim();
		String title = req.getParameter("title").trim();
		String contents = req.getParameter("contents").trim();
		res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
		pw.println("<script>");

		Connection con = null;
		PreparedStatement ptst = null;
		Statement st = null;
		ConnectionPoolBean pool = null;
		String inseSQL = "insert into borders values ( eriCountSEQ.nextval , ? , ? , ? , SYSDATE , ? )";
		try{
			pool = getPool();
			con = pool.getConnection();
			ptst = con.prepareStatement(inseSQL);
			ptst.setString(1, id);
			ptst.setString(2, name);
			ptst.setString(3, title);
			ptst.setString(4, contents);
			int i = ptst.executeUpdate();
			if(i>0){
				pw.println("alert('Pool 입력성공')");
			}else{
				pw.println("alert('Pool 입력실패')");
			}
		}catch(SQLException se){
			System.out.println("'인설트fff됨'"+se);  
		}finally{
		 try{
			if(ptst != null) ptst.close();
			if(con != null) pool.returnConnection(con);
		}catch(SQLException se){}
		}
		//pw.println("<a href='list.do'>리스트</a><br/>");  // 얘는 바로 못가니까
		pw.println("location.href='list.do'"); // 자바스크립트 로케이션 잡아주면 이리로 바로 돌아감
		pw.println("</script>");

	}
}

