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
import DAO.FeedBackDAO;


@WebServlet("/FeedBack")
public class FeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FeedBack() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fb = request.getParameter("fb");
		if(fb==null) {
			HttpSession httpSession = request.getSession();
			request.setAttribute("item", "3");
			Account user = (Account)httpSession.getAttribute("user");
			if(user==null) {
				RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
				rd.forward(request,response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher("View/Manage/FeedBack.jsp");
				rd.forward(request,response);
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("View/FeedBack.jsp");
			rd.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String fb = request.getParameter("fb");
		if(fb==null) {
			HttpSession httpSession = request.getSession();
			request.setAttribute("item", "3");
			Account user = (Account)httpSession.getAttribute("user");
			if(user==null) {
				RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
				rd.forward(request,response);
			}
			else{
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String content = request.getParameter("content");
				FeedBackDAO.newFb(name, phone, content);
				request.setAttribute("rs", 1);
				RequestDispatcher rd = request.getRequestDispatcher("View/Manage/FeedBack.jsp");
				rd.forward(request,response);
			}
		}else {
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String content = request.getParameter("content");
			FeedBackDAO.newFb(name, phone, content);
			request.setAttribute("rs", 1);
			RequestDispatcher rd = request.getRequestDispatcher("View/FeedBack.jsp");
			rd.forward(request,response);
		}
	}
}
