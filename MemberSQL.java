package Member.service;

class MemberSQL {
	final static String SIGNUP = "INSERT INTO MEMBER VALUES (? , MEM_NUM_SEQ.NEXTVAL , ?, ?, ?, default , ?, ?, ?, ?, ?, ? ,? , 0)";
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
	final static String UPDATEMEMBERADMIN = "UPDATE MEMBER SET NICK = ? , mem_phone = ? , PWD = ? , license = ? , mem_loc = ? WHERE EMAIL = ?";
	final static String MEMBERFINDNICKX = "SELECT EMAIL , NICK FROM MEMBER WHERE NICK like ? ";
	final static String XSEARCH = "SELECT * FROM XWORD";
	final static String MEMBERXUPDATE = "UPDATE MEMBER SET NICK = ? WHERE EMAIL = ?";
	
	final static String UPDATEMEBERADMINNICK = "UPDATE MEMBER SET NICK = ? WHERE EMAIL = ?";
	final static String UPDATEMEBERADMINPASSWORD = "UPDATE MEMBER SET PWD = ? WHERE EMAIL = ?";
	final static String UPDATEMEBERADMINCOUPLE = "UPDATE MEMBER SET COUJPLE = ? WHERE EMAIL = ?";
	final static String UPDATEMEBERADMINANNI = "UPDATE MEMBER SET ANNI = ? WHERE EMAIL = ?";
	
	final static String UPDATEMEBERUSERNICK = "UPDATE MEMBER SET NICK = ? WHERE EMAIL = ?";
	final static String UPDATEMEBERUSERPASSWORD = "UPDATE MEMBER SET PWD = ? WHERE EMAIL = ?";
	final static String UPDATEMEBERUSERCOUPLE = "UPDATE MEMBER SET COUJPLE = ? WHERE EMAIL = ?";
	final static String UPDATEMEBERUSERANNI = "UPDATE MEMBER SET ANNI = ? WHERE EMAIL = ?";
}
