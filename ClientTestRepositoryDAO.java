package ClientTestRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ClientTest.domain.ClientMembers;





 class ClientTestRepositoryDAO implements ClientTestRepository {
	 
	DataSource ds;
		
	public ClientTestRepositoryDAO() {
			try {
				Context inContext = new InitialContext();
				Context envContext = (Context)inContext.lookup("java:/comp/env");
				ds = (DataSource)envContext.lookup("jdbc/myoracle");
				
			}catch(NamingException ne) {
				System.out.println("Context Naming Error : " + ne); 
			}
		}
	
	@Override
	public List<ClientMembers> allFind() {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			String sql = ClientSQL.ALL;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				List<ClientMembers> member = new ArrayList<>();
				while(rs.next()) {
					ClientMembers cm = new ClientMembers();
					cm.setId(rs.getString("id"));
					cm.setName(rs.getString("name"));
					cm.setEmail(rs.getString("email"));
					cm.setPower(rs.getString("power"));
					cm.setNickName(rs.getString("nickName"));
					member.add(cm);
				}
				return member;
			}catch(Exception e){
				throw new IllegalStateException(e);
			}finally {
				close(con,pstmt,rs);
			}
				
				
	}

	@Override
	public Optional<ClientMembers> idFind(Integer numbers) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = ClientSQL.NUMBERS;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pstmt.setInt(1 , numbers);
			if(rs.next()) {
				ClientMembers cm = new ClientMembers();
				cm.setNumber(rs.getInt("NUMBERS"));
				cm.setName(rs.getString("ID"));
				cm.setEmail(rs.getString("EMAIL"));
				return Optional.of(cm);
			}else {
				return Optional.empty();
			}	
		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally {
			close(con,pstmt,rs);
		}
			
	}
	
	@Override
	public Optional<ClientMembers> nickNameFind(String id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = ClientSQL.NICKNAME;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pstmt.setString(1 , id);
			if(rs.next()) {
				ClientMembers cm = new ClientMembers();
				cm.setId(rs.getString("id"));
				cm.setName(rs.getString("nickName"));
				cm.setEmail(rs.getString("EMAIL"));
				return Optional.of(cm);
			}else {
				return Optional.empty();
			}	
		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally {
			close(con,pstmt,rs);
		}
			
	}

	@Override
	public Optional<ClientMembers> emailFind(String id) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = ClientSQL.EMAIL;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			pstmt.setString(1 , id);
			if(rs.next()) {
				ClientMembers cm = new ClientMembers();
				cm.setId(rs.getString("id"));
				cm.setEmail(rs.getString("EMAIL"));
				return Optional.of(cm);
			}else {
				return Optional.empty();
			}	
		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally {
			close(con,pstmt,rs);
		}
			
	}

	
	int login(ClientMembers dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ClientSQL.LOGIN;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			if(dto.getId() != null) {
				String getIds = dto.getId().toUpperCase();
				pstmt.setString(1, getIds);
			}else {
				return -1; // id에러
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("id").toUpperCase();
				String password = rs.getString("password").toUpperCase();
				if(id.equalsIgnoreCase(dto.getId())) {
					if(dto.getPassword() != null) {
						if(password.equalsIgnoreCase(dto.getPassword())) {
							return 1; // 성공
						}else {
							return -2; // PASSWORD 에러
						}
					}else {
						return -2; // password 에러
					}
				}else {
					return -1; // id 에러
				}
			}
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(con,pstmt,rs);
		}
		return -1;
	
	}
		
	
	

	int logincheck(String id) { // 아이디가 존재한다면, -1
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql = ClientSQL.LOGINCHECK;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			if(id != null) {
				pstmt.setString(1 , id);
				rs = pstmt.executeQuery();
				if(rs.next() || id.equals("")) {
					return -1;
				}else {
					return 1;
				}
			}else {
				return -1;
			}
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}finally {
			close(con,pstmt,rs);
		}
		
	}

	ArrayList<ClientMembers> logins(String id ,String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ClientSQL.LOGININFO;
		ArrayList<ClientMembers> list = new ArrayList<ClientMembers>(); 
		if(id != null) {
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int numbers = rs.getInt("numbers");
					String rsid = rs.getString("id");
					String rspassword = rs.getString("password");
					String nickname = rs.getString("nickname");
					String email = rs.getString("email");
					String power = rs.getString("power");
					if(password.equals(rspassword)) {
						list.add(new ClientMembers(numbers, rsid, password, nickname, nickname, email, power));
						return list;
					}else {
						return null;
					}
				}
			}catch(Exception e) {
				throw new IllegalStateException(e);
			}finally {
				close(con,pstmt,rs);
			}
		}return null;
	}


	boolean joincheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = ClientSQL.JOINCHECK;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			int s = 0;
			while(rs.next()) {
				s++;
			}
			if(s==0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}return false;
	}

	
	boolean join(String id , String password , String name , String nickname , String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = ClientSQL.JOIN;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, nickname);
			pstmt.setString(5, email);
			System.out.println(""+sql + id + password +":"+ name +":"+ nickname +":"+ email);
			int i = pstmt.executeUpdate();
			if(i>0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			close(con,pstmt,null);
		}return false;
		
	}
	
	boolean update(String id ,String power) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = ClientSQL.POWERUPDATE;

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, power);
			pstmt.setString(2, id);
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			} else{
				System.out.println("권한 변경 실패");
				return false;
			}
		} catch(SQLException se){
			System.out.println("권한변경 실패 se: " + se);
			return false;
		} 
	}
	
	boolean updateN(String id ,String nickName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = ClientSQL.NICKNAMEUPDATE;

		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			pstmt.setString(2, id);
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			} else{
				System.out.println("닉네임 변경 실패");
				return false;
			}
		} catch(SQLException se){
			System.out.println("닉네임 실패 se: " + se);
			return false;
		} 
	}
	
    private void close(Connection con, PreparedStatement pstmt, ResultSet rs)
    {	
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

		
}

