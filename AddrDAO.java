package soo.mv.model;

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


public class AddrDAO {
	private DataSource ds;
	public AddrDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("#tomcat이 만든 dbcp객체(jdbc/myoracle)이름을 못찾음");
		}
		//Connection conn = ds.getConnection();
	}
	public DataSource getDs(){
		return ds;
	}
	
	public ArrayList<AddrDTO> list(){
		Connection con =null;
		Statement st =null;
		ResultSet rs = null;
		ArrayList<AddrDTO> list = new ArrayList<AddrDTO>();
		try{
			con = ds.getConnection();
			st = con.createStatement();
			String selecSQL = "select eriCount, id ,name ,title , borderdate  from Borders ORDER BY eriCount DESC";
			rs = st.executeQuery(selecSQL);
			while(rs.next()){
				int eriCount = rs.getInt("eriCount");
				String id = rs.getString("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				Date borderdate = rs.getDate("borderdate");
				list.add(new AddrDTO(eriCount, id, name, title, borderdate));
			}
				return list;
			}catch(SQLException se4){
					System.out.println("셀렉문 계시판 글쓰기 저장실패" + se4);
					return null;
			}finally{
				try{
					rs.close();
					st.close();
					con.close();
			}catch(SQLException se){
			}	
		}			
	}
	
	public boolean insert(AddrDTO dto) {
		String inseSQL = "insert into Borders values ( eriCountSEQ.nextval , ? , ? , ? , SYSDATE , ? )";
		Connection con = null;
		PreparedStatement ptst =null;

		try{
			con = ds.getConnection();
			ptst = con.prepareStatement(inseSQL);
			ptst.setString(1, dto.getId());
			ptst.setString(2, dto.getName());
			ptst.setString(3, dto.getTitle());
			ptst.setString(4, dto.getContents());
			int i =ptst.executeUpdate();
			if(i>0) {
				return true;
			}else{
				return false;	
			}
		}catch(SQLException se){

		}finally{
			try{
				if(ptst != null) ptst.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		
		}return false;
		
		
	}
	public boolean delete(AddrDTO dto) {
		String sql = "delete from Borders where eriCount = ? ";
		Connection con = null;
		PreparedStatement ptmt = null;
		
		try{
	
			con = ds.getConnection();
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getEricount());
		}catch(SQLException se){
			System.out.println("실패2" + se);
			return false;		
		}
		try{
			int i = ptmt.executeUpdate();
			if(i>0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException se1){
			return false;
		}finally{
			try{
				if(ptmt != null) ptmt.close();
				if(con != null)  con.close();
			}catch(SQLException se){}
		}
		
	}
	public ArrayList<AddrDTO> contents(int eContst) {
		ArrayList<AddrDTO> lists = new ArrayList<AddrDTO>();
		Connection con = null;
		Statement st = null;
	    ResultSet rs = null;
	    String selecSQL = "select eriCount, id ,name ,title , borderdate , contents from Borders where eriCount ="+eContst+"";
		try{
			con = ds.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(selecSQL);
			while(rs.next()){
				int eriCount = rs.getInt("eriCount");
				String id = rs.getString("id");
				String name = rs.getString("name");
			    String title = rs.getString("title");
				Date borderdate = rs.getDate("borderdate");
				String contents = rs.getString("contents");
				lists.add(new AddrDTO(eriCount, id, name, title, borderdate, contents));
				}
				return lists;
			}catch(SQLException se) {		
			}finally{
				try {
					rs.close();
					con.close();
					st.close();
				}catch(SQLException se1){}
			}return null;
		}
	public ArrayList<AddrDTO> update1(int eriCount , String id) {
		ArrayList<AddrDTO> list = new ArrayList<AddrDTO>();
		Connection con = null;
		Statement st = null;

		System.out.println(eriCount+id+"");
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			st = con.createStatement();
			String selecSQL = "select * from Borders where eriCount ="+eriCount+"";
			rs = st.executeQuery(selecSQL);
			while(rs.next()){
				eriCount = rs.getInt("eriCount");
				id = rs.getString("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				Date borderdate =rs.getDate("borderdate");
				String contents = rs.getString("contents");
				list.add(new AddrDTO(eriCount, id, name, title, borderdate, contents));
			}return list;
		
		}catch(SQLException se){	
			return null;
		}finally {
			try {
				rs.close();
				st.close();
				con.close();
			}catch(SQLException se1){}
		}
	}
	
	public boolean update2(String name , String title , String contents , int eriCount) {
		Connection con = null;
		Statement st = null;
		try{
			con = ds.getConnection();
			st = con.createStatement();
			String selecSQL = "update borders set name ='"+name+"' , title = '"+title+"' , contents = '"+contents+"' where eriCount ="+eriCount+"";
			int i = st.executeUpdate(selecSQL);
			if(i>0) {
				return true;	
			}else {
				return false;		
			}
		}catch(SQLException se4){
			System.out.println("수정  저장실패" + se4);
			return false;		
		}finally{
			try{
				if(st != null) st.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
		
	}
}