package addr.mvc.model;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import mvc.domain.Address;
import mvc.domain.setPage;

 class AddrDAO {
	private DataSource ds;
	private Vector<setPage> spv;
	AddrDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch(NamingException ne) {	
		}
	}
	
	
	int viewCountK(Integer seq) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = AddrSQL.VIEWCOUNTS;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
			int count = rs.getInt(1);
			count++;
			return count;
			}return -2;
		}catch(SQLException se) {
			System.out.println("에러"+se);
			return -3;
		}finally {
			try {
				con.close();
				pstmt.close();
			}catch(SQLException se) {	
			}
		}
	}
	
	void viewCountUpdate(Integer seq , int count) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = AddrSQL.VIEWCOUNTUPDATE;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			//int countQ = count;
			pstmt.setInt(1 , count);
			pstmt.setInt(2, seq);
			int i = pstmt.executeUpdate();
			if(i == 1) {
				System.out.println("업데이트 성공");
			}else {
				System.out.println("업데이트 실패 ");
			}
		}catch(SQLException se) {
		}finally{
			try {
				con.close();
				pstmt.close();
			}catch(SQLException se){
				
			}
		}
	}
	
	int counts(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = AddrSQL.COUNT;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int count= rs.getInt(1);
				return count;
			}
			return -1;
		}catch(SQLException se){
			System.out.println("count() exception: " + se);
			return -1;
		} finally{
			try{
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch(SQLException se){
				System.out.println("count : " + se);
			}
		}
	} // end of list()
	Vector<setPage> page(Integer pageIndex) {
		spv = new Vector<setPage>();
		System.out.println("listDao 오늘의 검증 page"+"ss :"+ pageIndex);
		spv.add(new setPage(-1, pageIndex, -1, -1));
		
		return spv;
	}
	
	ArrayList<Address> list(Integer page){
		ArrayList<Address> list = new ArrayList<Address>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = AddrSQL.LIST;
		int pageIndex = -1;
		if(this.spv == null) {
			 page = 1;
			 pageIndex = 5;
		}else {
			for(setPage dk : spv){
				if( dk.getPageIndex() != -1) {
				  //page = dk.getViewPage();
				  pageIndex = dk.getPageIndex();
				  System.out.println("listDao pageIndex : " + pageIndex);
				}else {
					System.out.println("listDao 에러 인덱스 못찾음 널");
				}
			}
		}
		int tPage = pageIndex;
		int pageEnd = pageIndex;
		int pageStart = -1;
		System.out.println("page number :" + page);
		if(page>=2 && pageIndex==5) {
			tPage = 5;
			pageStart = page * tPage - 4;
			pageEnd = page * 5;
			System.out.println("페이지 pageindex5" + page);
		}else if(page >=2 && pageIndex==7) {
			tPage = 7;
			pageStart = page * tPage - 6; // 8
			pageEnd = page * 7; //
			System.out.println("페이지 pageindex7" + page);
		}else if(page >=2 && pageIndex==10) {
			tPage = 10;
			pageStart = page * tPage - 9;
			pageEnd = page * 10;
			System.out.println("페이지 pageindex10" + page);
		}else if(page >=2 && pageIndex==15) {
			tPage = 15;
			pageStart = page * tPage - 14;
			pageEnd = page * 15;
			System.out.println("페이지 pageindex15" + page);
		}
		
		System.out.println("들어가기전 페이지값 page :" + page + "pagened :" + pageEnd);
		System.out.println("sql" + sql);
		try{
			//System.out.println("1");
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pageStart);
			pstmt.setInt(2, pageEnd);
			//System.out.println("2");
			rs = pstmt.executeQuery();
			//System.out.println("3");
			while(rs.next()){
				int seq = rs.getInt("seq");
				//System.out.println("4");
				String writer = rs.getString("writer");
				String email = rs.getString("email");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				Date rdate = rs.getDate("rdate");
				int viewCount = rs.getInt("viewcount");
				
				//System.out.println(writer +email + subject + "");
				//for(int i=0;i<list.size();i++) {
					//Object s = list.get(i);
					//System.out.println(s.toString());
				//}
				list.add(new Address(seq, writer, email, subject, content, rdate , viewCount));
				
			}
			//System.out.println("5");
			return list;
		}catch(SQLException se){
			System.out.println("list() exception: " + se);
			return null;
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
				System.out.println("닫음 ");
			} catch(SQLException se){
				System.out.println("finally : " + se);
			}
		}
		
	} // end of list()
	
	ArrayList<Address> content(int seq) {
		ArrayList<Address> contentList = new ArrayList<Address>();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql =AddrSQL.CONTENT;
		
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
					int viewCount = rs.getInt(7);
					contentList.add(new Address(seq, writer, email, subject, content, rdate ,  viewCount));
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
	
	boolean insert(Address dto , String writer , String Email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = AddrSQL.INSERT;

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, Email);
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
	
	boolean delete(Address dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = AddrSQL.DELETE;

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
	} // end of delete()
	
	ArrayList<Address> updateForm(int seq) {
		ArrayList<Address> updateFormList = new ArrayList<Address>();
		Connection con = null;
		PreparedStatement pstmt1 = null;
		String sql1 = AddrSQL.UPDATEFORM;
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
				int viewCount = rs.getInt(7);
				updateFormList.add(new Address(seq, writer, email, subject, content, rdate , viewCount));
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
	
	boolean update(Address dto) {
		Connection con = null;
		PreparedStatement pstmt2 = null;
		String sql2 =AddrSQL.UPDATE;

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