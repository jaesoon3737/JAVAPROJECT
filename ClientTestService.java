package ClientTestRepository;

import java.util.ArrayList;
import java.util.List;

import ClientTest.domain.ClientMembers;

public class ClientTestService {
	private ClientTestRepositoryDAO dao;
	private static ClientTestService instance = new ClientTestService();
	public ClientTestService() {
		dao = new ClientTestRepositoryDAO();
	}
	
	public static ClientTestService getInstance() {
		return instance;
	}
	
	public boolean joinCheckS(String id) {
		return dao.joincheck(id); // insert 회원가입
	}
	
	public boolean powerupdateS(String id, String power) {
		return dao.update(id, power); // insert 회원가입
	}
	
	public boolean nicknameupdateS(String id, String nickName) {
		return dao.updateN(id, nickName); // insert 회원가입
	}
	public boolean joinS(String id , String password , String name , String nickname , String email) {
		return dao.join(id, password, name, nickname, email);
	}
	
	public int loginS(ClientMembers dto) {
		return dao.login(dto); // id 로그인
	}
	
	public ArrayList<ClientMembers> loginSS(String id , String password) {
		return dao.logins(id , password); // id 로그인
	}
	
	public List<ClientMembers> AllFindS() {
		return dao.allFind(); // id 로그인
	}
}
