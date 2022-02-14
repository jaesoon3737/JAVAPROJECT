package Member.service;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import Member.domain.Member;

public class MemberService {
	private static int XChangememberCount;
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
	public ArrayList<Member> memberList() {
		return memberRepository.memberList();
		
	}
	public ArrayList<Member> loginMember(String email , String pwd) {
		String exist = null;
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
	public Member MemberInfo(String email){
		Member result =  memberRepository.MemberInfo(email).get();
		System.out.println(result.toString());
		return result;
	}
	
	public boolean updateMember(String email , String category , String content){
		String sql = null;
		if(category == null || email == null || content == null || content.equals("") ) {
			return false;
		} else  {
			if(category.equals("nick")) {
				sql = MemberSQL.UPDATEMEMBERNICK;
			} else if(category.equals("grade")) {
				sql = MemberSQL.UPDATEMEMBERGRADE;
			}
			return memberRepository.updateMember(email , content , sql);
		}
		
	}
	public boolean updateMemberAdmin(String email , String categoryChoicePwd , String categoryChoiceNick , Integer categoryChoicePhone , Integer categoryChoiceLisence ,String categoryLoc) {
		String sql = null;
		if(email == null || categoryChoicePwd == null || categoryChoicePwd.equals("") || categoryChoiceNick == null ||  categoryChoiceNick.equals("") || categoryChoicePhone.equals("") || categoryChoicePhone == null || categoryLoc == null || categoryLoc.equals("")) {
			return false;
		} else {
		return memberRepository.updateMemberAdmin(email , categoryChoicePwd, categoryChoiceNick  , categoryChoicePhone , categoryChoiceLisence ,  categoryLoc);
		}
	}
	public boolean updateMemberUser(String email , String category , String content) {
		String sql = null;
		if(category == null || email == null || content == null || content.equals("") ) {
			return false;
		} else  {
			if(category.equals("nick")) {
				sql = MemberSQL.UPDATEMEBERUSERNICK;
			} else if (category.equals("anni")) {
				sql = MemberSQL.UPDATEMEBERUSERANNI;
			} else if (category.equals("pwd")) {
				sql = MemberSQL.UPDATEMEBERUSERPASSWORD;
			} else if (category.equals("couple")) {
				sql = MemberSQL.UPDATEMEBERUSERCOUPLE;
			}
		}
		
		return memberRepository.updateMemberUser(email, category, content);
	}
	public ArrayList<Member> MemberFind(Integer columnNumber ,String values){
		String sql = null;
		if(columnNumber != null) {
			if(columnNumber == 1) { // nickname 검색
				sql = MemberSQL.MEMBERFINDNICK;
			} else if(columnNumber == 0) { // EMAIL
				sql = MemberSQL.MEMBERFINDEMAIL;
			} else if(columnNumber == 2) { // GRADE
				sql = MemberSQL.MEMBERFINDGRADE;
			}
		}
		
		String value = "%" + values + "%";
		
		ArrayList<Member> list = memberRepository.MemberFind(value , sql);
		return list;
	}
	private void updateXNickNameExecute(String email , Integer number) {
		
		memberRepository.XUpdateNickName(email , number);
	}
	public boolean updateMemberNickNameXSelect(){
		HashMap<String , String > hm  = memberRepository.updateMemberNickNameXSelect();
		Set<String> keySet = hm.keySet();
		Iterator<String> emailKey = keySet.iterator();
		int checkSuccese = -1;
		
		while(emailKey.hasNext()) {
			String emailKeyed = emailKey.next();
			String changeNickName = hm.get(emailKeyed);
			if(emailKeyed != null || changeNickName !=null) {
				
				updateXNickNameExecute(emailKeyed , XChangememberCount);
				checkSuccese++;
				XChangememberCount++;
			}
		}
		if(checkSuccese != -1) {
			return true;
		} else {
			return false;
		}
		
	}
}
