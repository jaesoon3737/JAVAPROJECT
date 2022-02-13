package Member.service;

class MemberSQL {
	final static String SIGNUP = "INSERT INTO MEMBER VALUES (? , MEM_NUM_SEQ.NEXTVAL , ?, ?, ?, default , ?, ?, ?, ?, ?, ? ,? )";
	final static String MEMBERFINDNICK = "SELECT * FROM MEMBER WHERE NICK like ? order by mem_num ";
	final static String MEMBERFINDEMAIL = "SELECT * FROM MEMBER WHERE EMAIL like ? order by mem_num ";
	final static String MEMBERFINDGRADE = "SELECT * FROM MEMBER WHERE GRADE like ? order by mem_num ";
	final static String FINDALL = "SELECT * FROM MEMBER";
	final static String LOGIN = "SELECT * FROM MEMBER WHERE EMAIL = ? ";
	final static String MEMBERINFO = "SELECT * FROM MEMBER WHERE EMAIL = ? ";
	final static String EMAILFIND = "SELECT * FROM MEMBER WHERE MEM_NUM = ? ";
	final static String EMAILCHECK = "SELECT EMAIL FROM MEMBER WHERE EMAIL = ? ";
	final static String LOGINCOMPLE = "SELECT * FROM MEMBER WHERE EMAIL = ? ";
	final static String UPDATEMEMBERNICK = "UPDATE MEMBER SET NICK = ? WHERE EMAIL = ? ";
	final static String UPDATEMEMBERGRADE = "UPDATE MEMBER SET GRADE = ? WHERE EMAIL = ? ";
}
