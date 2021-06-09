package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Account;
import DAO.AccountDAO;
import UTILS.PW;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		String type = request.getParameter("type");
		if(type==null) {
			Account user = (Account)httpSession.getAttribute("user");
			if(user==null) {
				RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
				rd.forward(request,response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("View/Manage/ChangePassword.jsp");
				rd.forward(request,response);
			}
		} else {
			Account admin = (Account)httpSession.getAttribute("admin");
			if(admin==null) {
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Login.jsp");
				rd.forward(request,response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ChangePassword.jsp");
				rd.forward(request,response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String pw = request.getParameter("pw");
		String pw2 = request.getParameter("pw2");
		pw = PW.md5(pw);
		pw2 = PW.md5(pw2);
		HttpSession httpSession = request.getSession();
		String type = request.getParameter("type");
		if(type==null) {
			Account user = (Account)httpSession.getAttribute("user");
	
			boolean isValid = AccountDAO.validateAcc(user.getUserName(), pw);
			if(isValid) {
				AccountDAO.changePassword(user.getUserName(), pw2);
				request.setAttribute("result", 1);
				RequestDispatcher rd = request.getRequestDispatcher("View/Manage/ChangePassword.jsp");
				rd.forward(request,response);
			}
			else {
				request.setAttribute("result", 0);
				RequestDispatcher rd = request.getRequestDispatcher("View/Manage/ChangePassword.jsp");
				rd.forward(request,response);
			}
		} else {
			Account admin = (Account)httpSession.getAttribute("admin");
			
			boolean isValid = AccountDAO.validateAcc(admin.getUserName(), pw);
			if(isValid) {
				AccountDAO.changePassword(admin.getUserName(), pw2);
				request.setAttribute("result", 1);
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ChangePassword.jsp");
				rd.forward(request,response);
			}
			else {
				request.setAttribute("result", 0);
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/ChangePassword.jsp");
				rd.forward(request,response);
			}
		}
	}
}
