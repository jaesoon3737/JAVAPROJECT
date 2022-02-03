package board.mvc.model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import borad.domain.boardS;

class BoardDAO {
	private DataSource ds;
	public BoardDAO() {
		try { // InitialContext은 웹어플리케이션이 처음 배치 될 때 사용됨.
			Context inContext = new InitialContext(); // 어떤일을 하기위해서 제공하는 정보 Context 라이브러리에 있는 거찾아오려 고 처음에 생성하고
			Context envContext = (Context)inContext.lookup("java:/comp/env"); // JNDI resource라고 함 자원의 위치 라이브러리를 들고오는거저장
			ds = (DataSource)envContext.lookup("jdbc/myoracle"); // 커넥션풀 들고오기
		}catch(NamingException ne){		
		}
	}
	
	ArrayList<boardS> list(){
		ArrayList<boardS> list = new ArrayList<boardS>();
		Connection con = null;
		Statement st =null;
		ResultSet rs = null;
		String sql = BoardSQL.LIST;
		try {
			con = ds.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				Date rdate = rs.getDate(6);
				list.add(new boardS(seq, writer, email, subject, content, rdate));
			}return list;		
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			}catch(SQLException se){	
			}
		}
		}
	ArrayList<boardS> content(int seq){
		ArrayList<boardS> contents = new ArrayList<boardS>();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = BoardSQL.CONTENT;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				Date rdate = rs.getDate(6);
				contents.add(new boardS(seq, writer, email, subject, content, rdate));
			}return contents;
		}catch(SQLException se) {
			return null;
		}finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {}
		}
	}
	
	boolean insert(boardS dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = BoardSQL.INSERT;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			int i = pstmt.executeUpdate();
			if(i>0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException se) {
			return false;
		}finally {
			try {
				pstmt.close();
				con.close();
			}catch(SQLException se) {}
		}
	}
	
	
	boolean delete(boardS dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = BoardSQL.DELETE;

		if(dto.getSeq() != -1){
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getSeq());
				int i = pstmt.executeUpdate();
				if(i>0){
					return true;
				} else{
					return false;
				}
			}catch(SQLException se){
				System.out.println("delete() exception se: "+se);
				return false;
			}finally{
				try{
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				}catch(SQLException se){
					return false;
				}
			}
		}
		return false;
	}
	ArrayList<boardS> updateForm(int seq) {
		ArrayList<boardS> updateFormList = new ArrayList<boardS>();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		String sql1 = BoardSQL.UPDATEFORM;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, seq);  
			rs = pstmt1.executeQuery();

			while(rs.next()){
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				Date rdate = rs.getDate(6);
				
				updateFormList.add(new boardS(seq, writer, email, subject, content, rdate));
			}
			return updateFormList;
		} catch(SQLException se){
			System.out.println("select 예외: "+se);
			return null;
		} finally{
			try{
				if(pstmt1!=null) pstmt1.close();
				if(con!=null) con.close();
			}catch(SQLException se){}
		}
	}
	
	
	boolean update(boardS dto) {
		Connection con = null;
		PreparedStatement pstmt2 = null;
		String sql2 =BoardSQL.UPDATE;

		try{
			con = ds.getConnection();
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, dto.getSubject());
			pstmt2.setString(2, dto.getContent());
			pstmt2.setInt(3, dto.getSeq());
			int i = pstmt2.executeUpdate();
			if(i>0){
				return true;
			} else{
				System.out.println("update() 실패");
				return false;
			}
		} catch(SQLException se){
			System.out.println("update() (MV) 실패 se: " + se);
			return false;
		} 
	}
}
