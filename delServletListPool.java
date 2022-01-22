
package soo.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class delServletListPool extends HttpServlet {
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
				pw.println("alert('�Է¼���')");
			}else{
				pw.println("alert('�Է½���')");
			}
		}else{
				pw.println("alert('�ڱⲨ�� ��������')");
		}
		}catch(SQLException se){
			System.out.println("����2" + se);
		}finally{
			try{
				if(ptmt != null) ptmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		
		}

		//pw.println("<a href='list.do'>����Ʈ</a><br/>");  // ��� �ٷ� �����ϱ�
		pw.println("location.href='list.do'"); // �ڹٽ�ũ��Ʈ �����̼� ����ָ� �̸��� �ٷ� ���ư�
		pw.println("</script>");
	
	}
}

