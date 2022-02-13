package Member.service;



import java.util.ArrayList;

import Member.domain.Member;

public class MemberService {
	
	private MemberRepository memberRepository;
	
	public static MemberService instance = new MemberService();
	
	public MemberService() {
		memberRepository =  new MemberRepository();
	}
	
	public static MemberService getInstance() {
		return instance;
	}
	
	public boolean saveService(Member m) {
		return memberRepository.save(m);
	}
	
	public boolean duplicateMember(String email) {
		 if(email == null || email.length() == 0) 
			throw new NullPointerException("�̸��ϰ��� ���Ե��� �ʾҽ��ϴ�."); // ��ũ��Ʈó��
		 return memberRepository.emailCheck(email);
	}
	
	public ArrayList<Member> loginMember(String email , String pwd) {
		String exist = null;
		email.trim();
		email.toLowerCase();
		pwd.trim();
		if(email == null || email.equals("") || pwd == null || pwd.equals("")) {
			throw new NullPointerException("�̸��� �� ��й�ȣ�� ���Ե��� �ʾҽ��ϴ�."); // ��ũ��Ʈó��
		} else {
			String result = memberRepository.loginMember(email , pwd);
			if(result.equals("not")) {
				return null;
			} else {
				return memberRepository.loginComple(email , pwd);	
			}
		} 
	}
}
