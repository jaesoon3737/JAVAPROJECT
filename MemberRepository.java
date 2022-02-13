package Member.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Member.domain.Member;

public class MemberRepository { // DAO
	
	DataSource ds;
	
	public MemberRepository() {
			try {
				Context inContext = new InitialContext();
				Context envContext = (Context)inContext.lookup("java:/comp/env");
				ds = (DataSource)envContext.lookup("jdbc/myoracle");
			}catch(Exception e) {
				throw new IllegalStateException(e);
			}
	}
	
	String loginMember(String email , String pwd){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = MemberSQL.LOGIN;
		String exist = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String emailCheck = rs.getString("email");
				String pwdCheck = rs.getString("pwd");
				if(emailCheck != null) {
					if(pwdCheck.equals(pwd)){
						return emailCheck;
					} else {
						exist = "not";
						return exist;
					}
				} else {
					exist = "not";
					return exist;
				}
			} else {
				exist = "not";
				return exist;
			}	
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(con, pstmt, rs);
		}
	}
	
    Optional<Member> MemberInfo(String email) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = MemberSQL.MEMBERINFO;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1 , email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Member m = new Member();
				m.setEmail(rs.getString(1));
				m.setMemNumber(rs.getInt(2));
				m.setMemName(rs.getString(3));
				m.setBirth(rs.getDate(4));
				m.setPwd(rs.getString(5));
				m.setGrade(rs.getString(6));
				m.setNick(rs.getString(7));
				m.setMemPhone(rs.getString(8));
				m.setMemLoc(rs.getString(9));
				m.setGender(rs.getInt(10));
				m.setAnni(rs.getDate(11));
				m.setCouple(rs.getInt(12));
				m.setLicense(rs.getInt(13));
				return Optional.of(m);
			}else {
				return Optional.empty();
			}	
		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally {
			close(con,pstmt,rs);
		}
			
	}
	
    boolean updateMember(String email ,String content , String sql) {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	try {
    		con = ds.getConnection();
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, content);
    		pstmt.setString(2, email);
    		int i = pstmt.executeUpdate();
    		if(i > 0) {
    			return true ;
    		} else {
    			return false;
    		}
    	} catch (Exception e) {
    		throw new IllegalStateException(e);
    	} finally {
    		close( con , pstmt , null);
    	}
    } 
    
	ArrayList<Member> memberList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = MemberSQL.FINDALL;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String email = rs.getString("email");
				long number = rs.getLong("mem_num");
				String name =  rs.getString("mem_name");
				Date birth = rs.getDate("birth");
				String pwd = "";
				String pwdCheck =  rs.getString("pwd");
				String gradeDefaultValue = rs.getString("grade");
				String nick = rs.getString("nick");
				String phone = rs.getString("mem_phone");
				String loc = rs.getString("mem_loc");
				int gender = rs.getInt("gender");
				Date anni = rs.getDate("anni");
				int couple = rs.getInt("couple");
				int license = rs.getInt("license");
				list.add(new Member(email, number, name, birth, pwd, gradeDefaultValue, nick, phone, loc, gender, anni, couple, license));		
			}
			return list;
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(con,pstmt,rs);
		}
	
	}
	
	ArrayList<Member> loginComple(String emails , String pwds){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = MemberSQL.LOGINCOMPLE;
		ArrayList<Member> list = new ArrayList<Member>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, emails);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String email = rs.getString("email");
				long number = rs.getLong("mem_num");
				String name =  rs.getString("mem_name");
				Date birth = rs.getDate("birth");
				String pwd = "";
				String pwdCheck =  rs.getString("pwd");
				String gradeDefaultValue = rs.getString("grade");
				String nick = rs.getString("nick");
				String phone = rs.getString("mem_phone");
				String loc = rs.getString("mem_loc");
				int gender = rs.getInt("gender");
				Date anni = rs.getDate("anni");
				int couple = rs.getInt("couple");
				int license = rs.getInt("license");
				if(pwds.equals(pwdCheck)) {
					list.add(new Member(email, number, name, birth, pwd, gradeDefaultValue, nick, phone, loc, gender, anni, couple, license));
					return list;
				} else {
					return null;
				}
			}
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(con,pstmt,rs);
		}
		return null;	
	}
	
	boolean save(Member m) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = MemberSQL.SIGNUP;
		String email = m.getEmail();
		String name = m.getMemName();
		Date birth = m.getBirth();
		String pwd = m.getPwd();
		String nick = m.getNick();
		String phone = m.getMemPhone();
		int gender = m.getGender();
		String loc = m.getMemLoc();
		Date anni = m.getAnni();
		int couple = m.getCouple();
		int license = m.getLicense();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setDate(3, birth);
			pstmt.setString(4 , pwd);
			pstmt.setString(5, nick);
			pstmt.setString(6, phone);
			pstmt.setString(7, loc);
			pstmt.setInt(8 , gender);
			pstmt.setDate(9, anni);
			pstmt.setInt(10, couple);
			pstmt.setInt(11, license);
			int i = pstmt.executeUpdate();
			
			if(i <= 1) {
				return true;
			} else {
				return false;
			}
			
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(con, pstmt, null);
		}
	}
	
	//이메일 존재 여부 체크
	boolean emailCheck(String email){
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = MemberSQL.EMAILCHECK;
		System.out.println("1");
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(con, pstmt, rs);
		}
	}
	
	//중복체크용 메소드
	ArrayList<Member> MemberFind(String value , String sql){
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<Member> list = new ArrayList<Member>();
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String email = rs.getString(1);
				long number = rs.getLong(2);
				String name = rs.getString(3);
				Date birth = rs.getDate(4);
				String pwd = rs.getString(5);
				String grade = rs.getString(6);
				String nick = rs.getString(7);
				String phone = rs.getString(8);
				String loc = rs.getString(9);
				int gender = rs.getInt(10);
				Date anni = rs.getDate(11);
				int couple = rs.getInt(12);
				int license = rs.getInt(13);
				System.out.println("rs 내" + email);
				list.add(new Member(email, number, name, birth, pwd, grade, nick, phone, loc, gender, anni, couple, license));
			} 
			return list;
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(con, pstmt, rs);
		}
	}
	
	
	private void close(Connection con , PreparedStatement pstmt , ResultSet rs ) {
		
		try {
			if (rs != null) {
				rs.close();
			}		
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		try {
			if (pstmt != null) {
				pstmt.close();
			}		
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		try {
			if (con != null) {
				con.close();
			}		
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
