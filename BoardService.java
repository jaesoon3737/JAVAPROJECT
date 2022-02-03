package board.mvc.model;

import java.util.ArrayList;

import borad.domain.boardS;
import mvc.domain.Address;


public class BoardService {
	private BoardDAO dao;
	private static final BoardService instance = new BoardService();
	private BoardService() {
		dao = new BoardDAO(); // instance 1개당 DAO 객체 하나를 가져옴  인스턴스는 1개고정
	}
	
	public static BoardService getInstance() {
		return instance;
	}
	
	public ArrayList<boardS> listS(){	
		return dao.list();
	}
	
	public ArrayList<boardS> contentS(int seq){
		return dao.content(seq);
	}
	
	public boolean insertS(boardS dto) {
		return dao.insert(dto);
	}
	
	public boolean deleteS(boardS dto) {
		return dao.delete(dto);
	}
	
	public ArrayList<boardS> updateFormS(int seq) {
		return dao.updateForm(seq);
	}
	
	public boolean updateS(boardS dto) {
		return dao.update(dto);
	}
}
