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
	final static String JOIN ="INSERT INTO MEMBER VALUES(MEMBER_SEQ , ? , ? , ? , ? , ? , DEFAULT )";
	final static String CREATETABLE = "CREATE TABLE MEMBER (\r\n"
			+ "   NUMBERS NUMBER(4),\r\n"
			+ "   ID VARCHAR2(50),\r\n"
			+ "   PASSWORD VARCHAR2(50),\r\n"
			+ "   NAME VARCHAR2(50),\r\n"
			+ "   NICKNAME VARCHAR2(50),\r\n"
			+ "   EMAIL VARCHAR2(50),\r\n"
			+ "   POWER VARCHAR2(50)\r\n"
			+ ");";
}
