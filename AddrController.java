package addr.jstl.control;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import  javax.servlet.http.HttpServlet;
import  javax.servlet.http.HttpServletRequest;
import  javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ClientTestRepository.ClientTestService;
import mvc.domain.Address;
import mvc.domain.setPage;

import javax.servlet.RequestDispatcher;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Vector;

import addr.mvc.model.AddrService;

@WebServlet("/addr/boa.do")
public class AddrController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddrController() {
        super();
    }

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m!=null) {
			m = m.trim();
			switch (m) {
			case "list" : if("p" != null)list(request, response);  break;
			case "content" : content(request, response); break;
			case "input" : 	input(request, response); break;
			case "insert" : insert(request, response); break;
			case "delete" : delete(request, response); break;
			case "updateForm" : updateForm(request, response); break;
			case "update" : update(request, response); break;
			case "pageindexs" : page(request, response);  list(request, response); break;
			}	
		}else{
			list(request, response);
		}

	}
	
	public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddrService service = AddrService.getInstance();
		//String pageLocationString = request.getParameter("p");
		String pageIndexString = request.getParameter("pageindex");	
		if(  pageIndexString != null) {
			//pageLocationString.trim();
			//int pageLocation = Integer.parseInt( pageLocationString);
			pageIndexString.trim();
			int pageIndex = Integer.parseInt(pageIndexString);
			System.out.println("페이지 인덱스 체크 : "+pageIndex  );
			Vector<setPage> pageSet = service.pageN(pageIndex);
			for(setPage dot : pageSet) {
				int indexs = dot.getPageIndex();
				request.setAttribute("pageindex", indexs);
			}	
		}
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddrService service = AddrService.getInstance();
		HttpSession  session = request.getSession();
		//int count = service.count();
		//int pageNum = -1;
		//pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String writerrq =(String)session.getAttribute("J_id");
		String emailrq =(String)session.getAttribute("J_email");
		String power = (String)session.getAttribute("J_power");
		String s = request.getParameter("p");
		String pageInde = request.getParameter("pageindex");
		System.out.println(s);
		int sk = -1;
		if(s==null){
			 sk = 1; 
		}else {
			if(s.equals("")) {
				 sk = 1;
			}else {
				 sk = Integer.parseInt(s);
			}
		}
		/*
		int pageIndex =5;
		if(pageInde !=null) {
			 pageIndex = Integer.parseInt(pageInde);
		}else {
			 pageIndex = 5;
		}
		System.out.println("페이지 인덱스 넘어가남여 : "+pageIndex);
		
		*/
		//System.out.println(sk); // 페이지값 
		System.out.println("st : "+ sk);
		ArrayList<Address> list = service.listS(writerrq , sk);
		int kcount = service.count();
		request.setAttribute("list", list);
		request.setAttribute("listcount", kcount);
		//request.setAttribute("count", count);
		//request.setAttribute("page", pageNum);
		String view = "list.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request , response);
	}
	
	public void content(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddrService service = AddrService.getInstance();
		int jseq = getSeq(request);
		ArrayList<Address> list = service.contentS(jseq);
		request.setAttribute("content", list);
		
		String view = "content.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request , response);
	}
	
	public void input(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//AddrService service = AddrService.getInstance();
		//ArrayList<Address> list = service.listS();
		//request.setAttribute("input", list);
		response.sendRedirect("input.jsp");
	}
	
	
	public void count(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		AddrService service = AddrService.getInstance();
		int count = service.count();
		request.setAttribute("count", count);
		
		RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
		rd.forward(request , response);
	}
	
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddrService service = AddrService.getInstance();
		HttpSession  session = request.getSession();
		String writerrq =(String)session.getAttribute("J_id");
		String emailrq =(String)session.getAttribute("J_email");
		String power = (String)session.getAttribute("J_power");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int viewCount = 0;
		Address dto = new Address(-1, writerrq,emailrq,subject,content, null , viewCount); 
		boolean flag = service.insertS(dto , writerrq , emailrq);
		request.setAttribute("flag", flag);
		String view = "insert.jsp"; // 처리 결과를 들고가서 보여ㅑ줘야하기땜에 포워드 해야하는데
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request , response);
	}
	
	private int getSeq(HttpServletRequest request){
	      int seq = -1;
	      String seqStr = request.getParameter("seq");
	      if(seqStr != null){
	         seqStr = seqStr.trim();
	         if(seqStr.length() != 0){
	            try{
	               seq = Integer.parseInt(seqStr); 
	               return seq;
	            }catch(NumberFormatException ne){
	            }
	         }
	      }
	      return seq;
	   }
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddrService service = AddrService.getInstance();
		int jseq = getSeq(request);
		HttpSession  session = request.getSession();
		String writerrq =(String)session.getAttribute("J_id");
		String emailrq =(String)session.getAttribute("J_email");
		String power = (String)session.getAttribute("J_power");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		System.out.println("wr :" + writer + "wrrp :" + writerrq); 
		if(writerrq.equals(writer) || power.equals("관리자")) {
			Address dto = new Address(jseq, writer,email,subject,content, null , 0); 
			boolean flag = service.deleteS(dto);
			request.setAttribute("flags", flag);
			String view = "del.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request , response);
		}else {
			response.sendRedirect("../addr/updateCh.jsp");
		}
	}
	
	public void updateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddrService service = AddrService.getInstance();
		int jseq = getSeq(request);
		ArrayList<Address> uplist = service.updateFormS(jseq);
		request.setAttribute("uplist", uplist);
		String view = "updateForm.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request , response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddrService service = AddrService.getInstance();
		int jseq = getSeq(request);
		HttpSession  session = request.getSession();
		String writerrq =(String)session.getAttribute("J_id");
		String emailrq =(String)session.getAttribute("J_email");
		String power = (String)session.getAttribute("J_power");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int viewCount = Integer.parseInt(request.getParameter("viewCount"));
		System.out.println("wr : " + writer + "wrrq :" + writerrq);
		if(writerrq.equals(writer) || power.equals("관리자")) {
			Address dto = new Address(jseq, writer,email,subject,content, null , viewCount); 
			boolean updates = service.updateS(dto);
			request.setAttribute("updates", updates);
			String view = "update.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request , response);
		}else {
			response.sendRedirect("../addr/updateCh.jsp");
		}
	}
}