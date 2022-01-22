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
//if(idu.equalsIgnoreCase(loginServletList.ids)){
public class updateServletList1Pool extends HttpServlet {
	String idu;
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
		PrintWriter pw = res.getWriter();
		int eContss = 0;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8"); 
		System.out.println("수정  저장실패"+idu+loginServletListPool.ids);
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
			System.out.println("업데이트 키찾기 실패" + se5);
		}
		if(idu.equalsIgnoreCase(loginServletListPool.ids)){
		try{
			String selecSQL = "update borders set name ='"+name+"' , title = '"+title+"' , contents = '"+contents+"' where eriCount ="+eContss+"";
			st.executeUpdate(selecSQL);
		}catch(SQLException se4){
			System.out.println("수정  저장실패" + se4);
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
		pw.println("alert('자기꺼만 수정가능합니다')");
		pw.println("location.href='list.do'");
		pw.println("</script>");
		}
	}

}

