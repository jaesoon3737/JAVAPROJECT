package ClientTestRepository;

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
	
	public boolean joinS(String id , String password , String name , String nickname , String email) {
		return dao.join(id, password, name, nickname, email);
	}
	
	public int loginS(ClientMembers dto) {
		return dao.login(dto); // id 로그인
	}
}
