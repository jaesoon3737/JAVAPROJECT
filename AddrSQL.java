package addr.mvc.model;

class AddrSQL {
	final static String LIST = "select d.* from ( select row_number() over(order by seq desc) rnum , c.* from board c ) d where d.rnum between ? and ?";
	final static String CONTENT ="select * from BOARD where SEQ=?";
	final static String INSERT = "insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE , 0)";
	final static String DELETE = "delete from BOARD where seq=?";
	final static String UPDATEFORM ="select * from BOARD where SEQ=?";
	final static String UPDATE ="update BOARD set SUBJECT=?, CONTENT=? where SEQ=?";
	final static String COUNT = "select COUNT(*) from BOARD";
	final static String VIEWCOUNTS = "SELECT VIEWCOUNT FROM BOARD WHERE SEQ = ?";
	final static String VIEWCOUNTUPDATE = "UPDATE BOARD SET VIEWCOUNT = ? WHERE SEQ = ?";
}
