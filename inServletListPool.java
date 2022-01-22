package soo.sv.addr.pool;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import soo.db.ConnectionPoolBean;

public class inServletListPool extends HttpServlet {
	static String id;
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
				pw.println("alert('Pool �Է¼���')");
			}else{
				pw.println("alert('Pool �Է½���')");
			}
		}catch(SQLException se){
			System.out.println("'�μ�Ʈfff��'"+se);  
		}finally{
		 try{
			if(ptst != null) ptst.close();
			if(con != null) pool.returnConnection(con);
		}catch(SQLException se){}
		}
		//pw.println("<a href='list.do'>����Ʈ</a><br/>");  // ��� �ٷ� �����ϱ�
		pw.println("location.href='list.do'"); // �ڹٽ�ũ��Ʈ �����̼� ����ָ� �̸��� �ٷ� ���ư�
		pw.println("</script>");

	}
}

