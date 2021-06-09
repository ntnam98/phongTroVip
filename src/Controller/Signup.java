package Controller;
import UTILS.PW;
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

@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Signup() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String admin = request.getParameter("admin");
		if(admin==null) {
			request.setAttribute("menu", "0");
			RequestDispatcher rd = request.getRequestDispatcher("View/Signup.jsp");
			rd.forward(request,response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Login.jsp");
			rd.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		password = PW.md5(password);
		String name = request.getParameter("name");
		String phone =  request.getParameter("phone");
	
		boolean isValid = AccountDAO.kiemTraDangKi(userName, phone);
		String admin = request.getParameter("admin");
		if(admin==null) {
			if(isValid) {
				AccountDAO.insertAccount(userName, name, phone, password,"user");
				HttpSession httpSession = request.getSession();
				Account user = AccountDAO.getAcc(userName);
				httpSession.setAttribute("user", user);
				response.sendRedirect("Home");
				
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("View/Signup.jsp?error=0");
				rd.forward(request,response);
			}
		}else {
			if(isValid) {
				AccountDAO.insertAccount(userName, name, phone, password,"admin");
				response.sendRedirect("AdminAdmin");		
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Admin.jsp?error=0");
				rd.forward(request,response);
			}
		}
	}

}
