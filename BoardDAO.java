package soo.mv.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.sql.*;

public class BoardDAO {
	private DataSource ds;
	public BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch(NamingException ne) {	
		}
	}
	
	public ArrayList<BoardDTO> list(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from BOARD order by SEQ desc";
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt(1);
				String writer = rs.getString(2);
				String email = rs.getString(3);
				String subject = rs.getString(4);
				String content = rs.getString(5);
				Date rdate = rs.getDate(6);
				
				list.add(new BoardDTO(seq, writer, email, subject, content, rdate));
			}
			return list;
		}catch(SQLException se){
			System.out.println("list() exception: " + se);
			return null;
		} finally{
			try{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch(SQLException se){
				System.out.println("finally : " + se);
			}
		}
	} // end of list()
	
	public ArrayList<BoardDTO> content(int seq) {
		ArrayList<BoardDTO> contentList = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql ="select * from BOARD where SEQ=?";

		ResultSet rs = null;
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, seq);
				rs = pstmt.executeQuery();
				while(rs.next()){
					//seq=rs.getInt(1);
					String writer = rs.getString(2);
					String email = rs.getString(3);
					String subject = rs.getString(4);
					String content = rs.getString(5);
					Date rdate = rs.getDate(6);
					
					contentList.add(new BoardDTO(seq, writer, email, subject, content, rdate));
				}
				return contentList;
			} catch(SQLException se){
				System.out.println("content sql 예외: " + se);
				return null;
			}finally{
				try{
					if(rs!=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				}catch(SQLException se){
				}
			}
	} // end of content();
	
	public boolean insert(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());

			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			} else{
				return false;
			}
		} catch(SQLException se){
			System.out.println("입력 실패 (MV)" + se);
			return false;
		} finally{
			try{
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se){
			}
		}
	} // end of insert()
	
	public void delete(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete from BOARD where seq=?";

		if(dto.getSeq() != -1){
			try{
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getSeq());
				pstmt.executeUpdate();
			}catch(SQLException se){
				System.out.println("delete() exception se: "+se);
			}finally{
				try{
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				}catch(SQLException se){
				}
			}
		}
	} // end of delete()
	
	public ArrayList<BoardDTO> updateForm(int seq) {
		ArrayList<BoardDTO> updateFormList = new ArrayList<BoardDTO>();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		String sql1 ="select * from BOARD where SEQ=?";
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
				
				updateFormList.add(new BoardDTO(seq, writer, email, subject, content, rdate));
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
	} // end of updateForm()
	
	public boolean update(BoardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt2 = null;
		String sql2 ="update BOARD set SUBJECT=?, CONTENT=? where SEQ=?";

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
	} // end of update()
} // end of class
