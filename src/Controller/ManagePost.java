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

import BEAN.*;
import DAO.*;

@WebServlet("/ManagePost")
public class ManagePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ManagePost() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		request.setAttribute("item", "1");
		Account user = (Account)httpSession.getAttribute("user");	
		
		if(user==null) {
			RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
			rd.forward(request,response);
		}
		else{
			String show = request.getParameter("show");
			String hide = request.getParameter("hide");
			if(show!=null) {
				PostDAO.ShowPost(Integer.parseInt(show));
			}else if(hide !=null) {
				PostDAO.HidePost(Integer.parseInt(hide));
			}
			String page=  request.getParameter("page");
			int pg = 1;
			if(page!= null)	pg = Integer.parseInt(page);			
    		int totalPost = PostDAO.getTotalPost(user.getAccId());
    		int maxResult = 10;
    		int maxPage = (totalPost+maxResult-1)/maxResult;
    		int offset = (pg-1)* maxResult;
    		
			ArrayList<Post> postList = PostDAO.getPosts(user.getAccId(), offset, maxResult);
			request.setAttribute("postList", postList);
			request.setAttribute("maxPage",maxPage);
			request.setAttribute("pg", pg);
			for(Post post : postList) {
				String url = ImageDAO.getUrl(post.getPostId());
				post.setUrl(url);
				String category =  CategoryDAO.getCategory(post.getCategoryId());
				post.setCategory(category);
			}
			RequestDispatcher rd = request.getRequestDispatcher("View/Manage/ManagePost.jsp");
			rd.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
