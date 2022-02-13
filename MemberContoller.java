package Member.Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Member.domain.Member;
import Member.service.MemberService;

/**
 * Servlet implementation class MemberContoller
 */
@WebServlet("/member/mController")
public class MemberContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    public MemberContoller() {
        super();
  
    }


	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberMessage = request.getParameter("message");
		if(memberMessage != null) {
			memberMessage.trim();
			switch(memberMessage) {
				case "emailCheck" :	 emailCheck(request , response); break; 
				case "signup" :      signup(request , response); break;
				case "signform" :    signForm(request , response); break;
				case "login" : 	     login(request , response); break;
			}
		} else {
			loginForm(request , response);
		}
	}
	
	public void emailCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		response.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		boolean flag = service.duplicateMember(email);
		PrintWriter pw = response.getWriter();
		if(flag) {
			pw.print("not-usable");
			pw.close();
		} else {
			System.out.println("usa" + flag); 
			pw.print("1");	
			pw.close();
		}
	}
	public void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../Member/login.jsp");
	}
	
	public void signForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../Member/singup.jsp");
	}
	
	public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		Date birth = java.sql.Date.valueOf(request.getParameter("birth"));
		String nick = request.getParameter("nick");
		String phone = request.getParameter("phone");
		Date anni = java.sql.Date.valueOf(request.getParameter("anni"));
		String postNumber =  request.getParameter("postNumber");
		String addressed = request.getParameter("Address");
		String upAddress = request.getParameter("upAddress");
		String address = postNumber + " " + addressed + " " + upAddress;
		int gender = Integer.parseInt(request.getParameter("gender"));
		int couple = Integer.parseInt(request.getParameter("couple"));
		int license = Integer.parseInt(request.getParameter("license"));
		Member m = new Member(email, -1 , name, birth, pwd, "default" , nick, phone, address, gender, anni, couple, license);
		boolean flag = service.saveService(m);
		
		request.setAttribute("flag", flag);
		
		String view = "../";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		response.setCharacterEncoding("utf-8");
		System.out.println("Controller login 들어옴");
		if(email == null || email.equals("") || pwd == null || pwd.equals("")) {
			if(email == null || email.equals("")) {
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert(\"email Check\");");
				pw.print("location.href='http://localhost:8080/project/Member/login.jsp'");
				pw.print("</script>");
				pw.close();
				
			} else if (pwd == null || pwd.equals("")) {
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert(\"password Check\");");
				pw.print("location.href='http://localhost:8080/project/Member/login.jsp'");
				pw.print("</script>");
				pw.close();
				
			}
		} else {
			ArrayList<Member> list = service.loginMember(email, pwd);
			HttpSession session = request.getSession();
			if(list != null) {
				for(Member sessionIn : list) {
					String sessionEmail = sessionIn.getEmail();
					String sessionGrade = sessionIn.getGrade();
					long sessionNumber = sessionIn.getMemNumber();
					String sessionName = sessionIn.getMemName();	
					Date sessionBirth = sessionIn.getBirth();
					Date sessionAnni = sessionIn.getAnni();
					String sessionNick = sessionIn.getNick();
					String sessionPhone = sessionIn.getMemPhone();
					int sessionGender = sessionIn.getGender();
					String sessionLoc = sessionIn.getMemLoc();
					int sessionCouple = sessionIn.getCouple();
					int sessionLicense = sessionIn.getLicense();
					
					session.setAttribute("Member_Email" , sessionEmail);
					session.setAttribute("Member_Number" , sessionNumber);
					session.setAttribute("Member_Grade" , sessionGrade);
					session.setAttribute("Member_Name" , sessionName);
					session.setAttribute("Member_Birth" , sessionBirth);
					session.setAttribute("Member_Phone" , sessionPhone);
					session.setAttribute("Member_Loc" , sessionLoc);
					session.setAttribute("Member_Gender" , sessionGender);
					session.setAttribute("Member_Anni" , sessionAnni);
					session.setAttribute("Member_Couple" , sessionCouple);
					session.setAttribute("Member_License" , sessionLicense);
					
					boolean flag = true;
					request.setAttribute("flag", flag);
					System.out.println("로그인 완료 검증 email 명 : " + sessionEmail);
					String view = "../";
					RequestDispatcher rd = request.getRequestDispatcher(view);
					rd.forward(request, response);
				}
			} else {
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert(\"login fail\");");
				pw.print("location.href='http://localhost:8080/project/Member/login.jsp'");
				pw.print("</script>");
				pw.close();
			}
		}
	}

}

