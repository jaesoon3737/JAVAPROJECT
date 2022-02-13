package Member.domain;

import java.sql.Date;

public class Member {
	
	private String email;
	private long memNumber;
	private String memName;
	private Date birth;
	private	String pwd;
	private String grade;
	private String nick;
	private String memPhone;
	private String memLoc;
	private int gender;
	private Date anni;
	private int couple;
	private	int license;
	
	public Member() {
		super();
	}

	public Member(String email, long memNumber, String memName, Date birth, String pwd, String grade, String nick,
			String memPhone, String memLoc, int gender, Date anni, int couple, int license) {
		super();
		this.email = email;
		this.memNumber = memNumber;
		this.memName = memName;
		this.birth = birth;
		this.pwd = pwd;
		this.grade = grade;
		this.nick = nick;
		this.memPhone = memPhone;
		this.memLoc = memLoc;
		this.gender = gender;
		this.anni = anni;
		this.couple = couple;
		this.license = license;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMemNumber() {
		return memNumber;
	}

	public void setMemNumber(long memNumber) {
		this.memNumber = memNumber;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemLoc() {
		return memLoc;
	}

	public void setMemLoc(String memLoc) {
		this.memLoc = memLoc;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getAnni() {
		return anni;
	}

	public void setAnni(Date anni) {
		this.anni = anni;
	}

	public int getCouple() {
		return couple;
	}

	public void setCouple(int couple) {
		this.couple = couple;
	}

	public int getLicense() {
		return license;
	}

	public void setLicense(int license) {
		this.license = license;
	}
	
	public String toString() {
		
		return "Member{" +
				"email = " + email +
				"memNumber = " + memNumber +
				"memNAme = " + memName + "}";
	}

}
