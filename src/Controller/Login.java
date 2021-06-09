package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import BEAN.Account;
import UTILS.PW;
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		password = PW.md5(password);
		
		boolean isValid = AccountDAO.validateAcc(userName, password);
		boolean isActive = AccountDAO.isActiveAcc(userName);
		if(isValid) {	
			if(isActive) {
				HttpSession httpSession = request.getSession();
				Account acc = AccountDAO.getAcc(userName);
				if(acc.getRole().equals("user")) {
				httpSession.setAttribute("user", acc);
				response.sendRedirect("Home");
				}
				else {
					httpSession.setAttribute("admin", acc);
					response.sendRedirect("AdminAdmin");
				}
			}
			else {
				request.setAttribute("error", 1);
				RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
				rd.forward(request,response);
			}
		}
		else {
			request.setAttribute("error", 2);
			RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
			rd.forward(request,response);
		} 
		
	}

}
