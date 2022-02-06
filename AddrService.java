package addr.mvc.model;

import java.util.ArrayList;
import java.util.Vector;

import mvc.domain.Address;
import mvc.domain.setPage;

public class AddrService {
	//singleton Object Model start;
	private AddrDAO dao;
	private static final AddrService instance = new AddrService();
	private AddrService() {
		dao = new AddrDAO();
		
	}
	
	public static AddrService getInstance() {
		return instance;
	}
	
	public ArrayList<Address> listS(String writerrq , Integer page ){
		return dao.list(page);
	}
	
	public ArrayList<Address> contentS(int seq) {
		dao.viewCountUpdate(seq, dao.viewCountK(seq));
		return dao.content(seq);
	}
	
	public boolean insertS(Address dto , String writer , String email) {
		return dao.insert(dto , writer , email);
	}
	
	public boolean deleteS(Address dto) {
		return dao.delete(dto);
	}
	
	public int count() {
		return dao.counts();
	}
	
	public Vector<setPage> pageN( Integer pageIndex) {
		return dao.page(pageIndex);
	}
	
	public ArrayList<Address> updateFormS(int seq) {
		return dao.updateForm(seq);
	}
	public boolean updateS(Address dto) {
		return dao.update(dto);
	}
	
	
	public int viewCountss(Integer seq) {
		return dao.viewCountK(seq);
	}
}
