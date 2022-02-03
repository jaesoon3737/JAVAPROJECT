package ClientTestControl;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

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
		if(m != null) {
			m.trim();
			switch(m) {
			case "join" : {
				if(check.equals("중복체크")) {
					joinCheck(request,response); break;
				}else if(check.equals("회원가입")){
					joinMember(request,response); break;
				}
			}
			case "joinIndex" : joinIndex(request,response); break;
			case "logins" : loginS(request,response); break;
			case "logout" : logout(request,response); break;
			
			}
		}else {
			response.sendRedirect("../ClientTest_mvc");
		}
	}
	
	public void joinCheck (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientTestService service = ClientTestService.getInstance();
		String id = request.getParameter("id");
		boolean flag = service.joinCheckS(id);
		request.setAttribute("idCheck", flag);
		
		RequestDispatcher rd = request.getRequestDispatcher("../ClientTest_mvc/joinCheck.jsp");
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
