package board.mvc.model;

class BoardSQL {
	final static String LIST = "select * from BOARD order by SEQ desc";
	final static String CONTENT ="select * from BOARD where SEQ=?";
	final static String INSERT = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	final static String DELETE = "delete from BOARD where seq=?";
	final static String UPDATEFORM ="select * from BOARD where SEQ=?";
	final static String UPDATE ="update BOARD set SUBJECT=?, CONTENT=? where SEQ=?";
}
