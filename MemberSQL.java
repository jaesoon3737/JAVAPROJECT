package Member.service;

class MemberSQL {
	final static String SIGNUP = "INSERT INTO MEMBER VALUES (? , MEM_NUM_SEQ.NEXTVAL , ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? )";
	final static String IDFIND = "SELECT * FROM MEMBER WHERE EMAIL = ? ";
	final static String LOGIN = "SELECT * FROM MEMBER WHERE EMAIL = ? ";
	final static String EMAILFIND = "SELECT * FROM MEMBER WHERE MEM_NUM = ? ";
	final static String EMAILCHECK = "SELECT EMAIL FROM MEMBER WHERE EMAIL = ? ";
	final static String LOGINCOMPLE = "SELECT * FROM MEMBER WHERE EMAIL = ? ";
}
