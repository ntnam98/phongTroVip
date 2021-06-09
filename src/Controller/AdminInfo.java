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

@WebServlet("/AdminInfo")
public class AdminInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		Account admin = (Account)httpSession.getAttribute("admin");	
		
		if(admin==null) {
			RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
			rd.forward(request,response);
		}
		else {
			request.setAttribute("menu", 4);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Info.jsp");
			rd.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		HttpSession httpSession = request.getSession();
		Account admin = (Account)httpSession.getAttribute("admin");
		AccountDAO.changeInfo(admin.getUserName(), name, email);
		Account admin1 = AccountDAO.getAcc(admin.getUserName());
		httpSession.setAttribute("admin", admin1);
		request.setAttribute("menu", 4);
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Info.jsp");
		rd.forward(request,response);
	}

}
