package ClientTestControl;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ClientTest.domain.ClientMembers;
import ClientTestRepository.ClientTestService;



@WebServlet("/boardk/clientLoginS")
public class ClientTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ClientTestController() {
        super();
   
    }

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("m");
		String check = request.getParameter("check");
		String checked = request.getParameter("checked");
		if(m != null) {
			m.trim();
			switch(m) {
			case "join" : {
				if(check.equals("�ߺ�üũ")) {
					joinCheck(request,response); break;
				}else if(check.equals("ȸ������")){
					joinMember(request,response); break;
				}
			}
			case "joinIndex" : joinIndex(request,response); break;
			case "logins" : loginS(request,response); break;
			case "logout" : logout(request,response); break;
			case "AllFind": AllFind(request,response); break;
			case "powerchange" : {
					if(checked.equals("�г��Ӻ���")) {
						nickNameChange(request,response); break;
					}else if(checked.equals("���Ѻ���")) {
						powerchange(request,response); break;
					}
				}
			
			}
		}else {
			response.sendRedirect("../ClientTest_mvc");
		}
	}
	public void powerchange (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientTestService service = ClientTestService.getInstance();
		String id = request.getParameter("fnames");
		String power = request.getParameter("fname");
		System.out.println(""+id+power);
		boolean flag = service.powerupdateS(id , power);
		request.setAttribute("powerchange", flag);
		
		RequestDispatcher rd = request.getRequestDispatcher("../addr/powerchange.jsp");
		rd.forward(request, response);		
		
	}
	public void nickNameChange (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientTestService service = ClientTestService.getInstance();
		String id = request.getParameter("fnames");
		String nickName = request.getParameter("nickChange");
		System.out.println(""+id+nickName);
		boolean flag = service.nicknameupdateS(id , nickName);
		request.setAttribute("nickchange", flag);
		
		RequestDispatcher rd = request.getRequestDispatcher("../addr/nicknamechange.jsp");
		rd.forward(request, response);		
		
	}
	public void joinCheck (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientTestService service = ClientTestService.getInstance();
		String id = request.getParameter("id");
		boolean flag = service.joinCheckS(id);
		request.setAttribute("idCheck", flag);
		
		RequestDispatcher rd = request.getRequestDispatcher("../ClientTest_mvc/joinCheck.jsp");
		rd.forward(request, response);		
		
	}
	public void AllFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientTestService service = ClientTestService.getInstance();
		List<ClientMembers> list = service.AllFindS();
		request.setAttribute("All" , list);
		
		RequestDispatcher rd = request.getRequestDispatcher("../addr/Memberlist.jsp");
		rd.forward(request, response);	
	}
	
	public void joinMember (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientTestService service = ClientTestService.getInstance();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		if(id == null || password == null || passwordCheck == null || name == null || nickName == null || email == null || id.equals("") || password.equals("") || passwordCheck.equals("") || name.equals("") || nickName.equals("") || email.equals("") ) {
			response.sendRedirect("../ClientTest_mvc/joinsk.jsp");
		}else {
			if(passwordCheck.equals(password)) {
				boolean flag = service.joinS(id , password , name , nickName , email);
				request.setAttribute("joinMember", flag);
				RequestDispatcher rd = request.getRequestDispatcher("../ClientTest_mvc/joinMember.jsp");
				rd.forward(request, response);		
			}else {
				response.sendRedirect("../ClientTest_mvc/joinsk.jsp");
			}
		}	
	}
	
	public void joinIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../ClientTest_mvc/joinn.html");
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../ClientTest_mvc/logout.jsp");
	}
	
	public void loginS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientTestService service = ClientTestService.getInstance();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		if( id == null || id.equals("") || password==null || password.equals("")) {
			response.sendRedirect("../ClientTest_mvc/index.jsp");
		}else {
			ArrayList<ClientMembers> list = service.loginSS(id , password);
			request.setAttribute("loginJ", list);
			RequestDispatcher rd = request.getRequestDispatcher("../ClientTest_mvc/logins.jsp");
			rd.forward(request, response);
		}
	}
}
