package ClientTestRepository;


class ClientSQL {
	final static String LOGIN = "SELECT * FROM MEMBER where id = ?";
	final static String ALL = "SELECT * FROM MEMBER";
	final static String NUMBERS = "SELECT * FROM MEMBER where NUMBERS = ?";
	final static String NICKNAME = "SELECT * FROM MEMBER where id = ?";
	final static String EMAIL = "SELECT * FROM MEMBER where id = ?";
	final static String LOGINCHECK = "SELECT * FROM MEMBER where id = ?";
	final static String LOGININFO = "SELECT * FROM MEMBER where id = ?";
	final static String JOINCHECK = "SELECT * FROM MEMBER";
	final static String JOIN ="INSERT INTO MEMBER VALUES ( MEMBER_SEQ.nextval , ? , ? , ? , ? , ? , default )";
	final static String LOGINS = "SELECT * FROM MEMBER WHERE ID = ? ";
	
	
	
	
	
	
	
}
