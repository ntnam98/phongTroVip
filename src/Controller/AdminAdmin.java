package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.Post;
import BEAN.Account;
import DAO.PostDAO;
import DAO.AccountDAO;
@WebServlet("/AdminAdmin")
public class AdminAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAdmin() {
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
			String lock=  request.getParameter("lock");
			String unlock=  request.getParameter("unlock");
			if(lock!=null) {
				AccountDAO.LockAcc(Integer.parseInt(lock));
				ArrayList<Post> posts = PostDAO.getPosts(Integer.parseInt(lock), 0, 1000000);
				for(Post post: posts) {
					PostDAO.HidePost(post.getPostId());
				}
			}else if(unlock!=null) {
				AccountDAO.unLockAcc(Integer.parseInt(unlock));
				ArrayList<Post> posts = PostDAO.getPosts(Integer.parseInt(unlock), 0, 1000000);
				for(Post post: posts) {
					PostDAO.ShowPost(post.getPostId());
				}
			}
			String page=  request.getParameter("page");
			int pg = 1;
			if(page!= null)	pg = Integer.parseInt(page);			
    		int totalAcc = AccountDAO.getTotalAcc("admin");
    		int maxResult = 10;
    		int maxPage = (totalAcc+maxResult-1)/maxResult;
    		int offset = (pg-1)* maxResult;
    		
			request.setAttribute("maxPage",maxPage);
			request.setAttribute("pg", pg);
			
			ArrayList<Account> admins = AccountDAO.getAllAccount("admin",offset,maxResult);
			
			request.setAttribute("admins", admins);
			request.setAttribute("menu", 1);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Admin.jsp");
			rd.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
