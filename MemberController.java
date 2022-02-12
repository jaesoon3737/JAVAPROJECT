package Member.Control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Member.service.MemberService;


@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MemberController() {
        super();
  
    }


	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberMessage = request.getParameter("message");
		if(memberMessage != null) {
			memberMessage.trim();
			switch(memberMessage) {
				case "emailCheck" : emailCheck(request , response); break; 
			}
		}
	}
	
	public void emailCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		MemberService service = MemberService.getInstance();
		String email = request.getParameter("email");
		boolean flag = service.duplicateMember(email);
		request.setAttribute("emailCheckFlag", flag);
		PrintWriter pw = response.getWriter();
		if(flag) {
			pw.print("not-usable");
		} else {
			pw.print("usable");	
		}
	}
}
