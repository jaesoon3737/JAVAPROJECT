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
			throw new NullPointerException("이메일값이 주입되지 않았습니다."); // 스크립트처리
		 return memberRepository.emailCheck(email);
	}
	
	public ArrayList<Member> loginMember(String email , String pwd) {
		String exist = null;
		email.trim();
		email.toLowerCase();
		pwd.trim();
		if(email == null || email.equals("") || pwd == null || pwd.equals("")) {
			throw new NullPointerException("이메일 및 비밀번호가 주입되지 않았습니다."); // 스크립트처리
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
