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
import DAO.*;

@WebServlet("/AdminPost")
public class AdminPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPost() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		Account admin = (Account)httpSession.getAttribute("admin");	
		
		if(admin==null) {
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Login.jsp");
			rd.forward(request,response);
		}
		else {
			String hide=  request.getParameter("hide");
			String show=  request.getParameter("show");
			if(show!=null) {
				PostDAO.ShowPost(Integer.parseInt(show));
			}else if(hide !=null) {
				PostDAO.HidePost(Integer.parseInt(hide));
			}
			String page=  request.getParameter("page");
			int pg = 1;
			if(page!= null)	pg = Integer.parseInt(page);			
    		int total = PostDAO.getTotalPost1("%","%","%","%","%",0,2000000000,0,2000000);
    		int maxResult = 10;
    		int maxPage = (total+maxResult-1)/maxResult;
    		int offset = (pg-1)* maxResult;
    		
			request.setAttribute("maxPage",maxPage);
			request.setAttribute("pg", pg);
			
			ArrayList<Post> posts = PostDAO.getPost1("%","%","%","%","%",0,2000000000,0,2000000,offset,maxResult);
			for(Post post:posts) {
				String url = ImageDAO.getUrl(post.getPostId());
				post.setUrl(url);
				String cate = CategoryDAO.getCategory(post.getCategoryId());
				post.setCategory(cate);
				Account user = AccountDAO.getAcc(post.getAccId());
				post.setName(user.getName());
			}
			request.setAttribute("posts", posts);
			request.setAttribute("menu", 3);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Post.jsp");
			rd.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
