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
import DAO.PostDAO;

@WebServlet("/ChangePost")
public class ChangePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangePost() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		request.setAttribute("item", "0");
		Account user = (Account)httpSession.getAttribute("user");
		if(user==null) {
			RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
			rd.forward(request,response);
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("View/Manage/ManagePost.jsp?page=1");
			rd.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String address = request.getParameter("address");
		System.out.print("ok"+address);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String area = request.getParameter("area");
		int priceInt = Integer.parseInt(price);
		int areaInt = Integer.parseInt(area);
		
		String page=  request.getParameter("pg");
		int pg = Integer.parseInt(page);
		String post=  request.getParameter("post");
		int postId = Integer.parseInt(post);
		PostDAO.ChangePost(postId, address, title, content, priceInt, areaInt);

		if(pg == 1) {
			response.sendRedirect("ManagePost");
		}
		else {
			response.sendRedirect("ManagePost?page="+pg);
		}
	}
}
