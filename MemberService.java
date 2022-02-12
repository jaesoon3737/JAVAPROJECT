package Member.service;

import java.util.Optional;

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
			throw new NullPointerException("이메일값이 주입되지 않았습니다.");
		 return memberRepository.emailCheck(email);
	}
	
}
