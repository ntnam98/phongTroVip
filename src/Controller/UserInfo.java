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

@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		request.setAttribute("item", "2");
		Account user = (Account)httpSession.getAttribute("user");
		if(user==null) {
			RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
			rd.forward(request,response);
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("View/Manage/UserInfo.jsp");
			rd.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		HttpSession httpSession = request.getSession();
		Account user = (Account)httpSession.getAttribute("user");
		AccountDAO.changeInfo(user.getUserName(), name, email);
		Account user1 = AccountDAO.getAcc(user.getUserName());
		httpSession.setAttribute("user", user1);
		request.setAttribute("item", "2");
		RequestDispatcher rd = request.getRequestDispatcher("View/Manage/UserInfo.jsp");
		rd.forward(request,response);
	}
}
