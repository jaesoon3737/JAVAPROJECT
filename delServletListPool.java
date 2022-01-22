
package soo.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class delServletListPool extends HttpServlet {
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

	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException { 
		int seqss = 0;
		req.setCharacterEncoding("utf-8");
		String SEQs = req.getParameter("eriCount");
		String Sid = req.getParameter("id");
		String kk = loginServletListPool.ids;
		System.out.println(""+Sid + kk);

		if(SEQs != null){
			SEQs = SEQs.trim();
			seqss = Integer.parseInt(SEQs);
		}
		res.setContentType("text/html;charset=utf-8"); 
		PrintWriter pw = res.getWriter();
		pw.println("<script>");
		Connection con = null;
		PreparedStatement ptmt = null;
		ConnectionPoolBean pool = null;
		String sql = "delete from borders where eriCount = ?";
		try{
			pool = getPool();
			con = pool.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, seqss);
		if(Sid.equalsIgnoreCase(loginServletListPool.ids)){
			int i = ptmt.executeUpdate();
			if(i>0){
				pw.println("alert('입력성공')");
			}else{
				pw.println("alert('입력실패')");
			}
		}else{
				pw.println("alert('자기꺼만 지워져요')");
		}
		}catch(SQLException se){
			System.out.println("실패2" + se);
		}finally{
			try{
				if(ptmt != null) ptmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		
		}

		//pw.println("<a href='list.do'>리스트</a><br/>");  // 얘는 바로 못가니까
		pw.println("location.href='list.do'"); // 자바스크립트 로케이션 잡아주면 이리로 바로 돌아감
		pw.println("</script>");
	
	}
}

