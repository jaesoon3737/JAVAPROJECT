package Member.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	boolean save(Member m) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = MemberSQL.SIGNUP;
		String email = m.getEmail();
		String name = m.getMemName();
		Date birth = m.getBirth();
		String pwd = m.getPwd();
		String gradeDefaultValue = m.getGrade();
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
			pstmt.setString(5, gradeDefaultValue);
			pstmt.setString(6, nick);
			pstmt.setString(7, phone);
			pstmt.setString(8, loc);
			pstmt.setInt(9 , gender);
			pstmt.setDate(10, anni);
			pstmt.setInt(11, couple);
			pstmt.setInt(12, license);
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
	Optional<Member> idFind(String email){
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = MemberSQL.IDFIND;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pstmt.setString(1, email);
			if(rs.next()) {
				Member m = new Member();
				m.setEmail(rs.getString(1));
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
			} else {
				return Optional.empty();
			}
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
