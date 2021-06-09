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
@WebServlet("/StorePost")
public class StorePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StorePost() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession httpSession = request.getSession();
		Account user = (Account)httpSession.getAttribute("user");	
		
		if(user==null) {
			response.sendRedirect("Login");
		}else {
			String sp = request.getParameter("sp");
			if(sp!=null) {
				String post = request.getParameter("post");
				int postId = Integer.parseInt(post);
				StoredPostDAO.newSP(user.getAccId(), postId);
				response.sendRedirect("PostInfo?postId="+postId);
			}else {
				String delete = request.getParameter("delete");
				if(delete!=null) {
					StoredPostDAO.deleteSP(user.getAccId(), Integer.parseInt(delete));
				}
				String page=  request.getParameter("page");
				int pg = 1;
				if(page!= null)	pg = Integer.parseInt(page);			
	    		int totalPost = StoredPostDAO.getTotalSP(user.getAccId());
	    		int maxResult = 10;
	    		int maxPage = (totalPost+maxResult-1)/maxResult;
	    		int offset = (pg-1)* maxResult;
	    		
				ArrayList<StoredPost> spList = StoredPostDAO.getAllSP(user.getAccId(), offset, maxResult);
				ArrayList<Post> postList = new ArrayList<Post>();
				for(StoredPost storedPost : spList) {
					Post post = PostDAO.getPost(storedPost.getPostId());
					postList.add(post);
				}
				for(Post post : postList) {
					String url = ImageDAO.getUrl(post.getPostId());
					post.setUrl(url);
					String category =  CategoryDAO.getCategory(post.getCategoryId());
					post.setCategory(category);
					Account name = AccountDAO.getAcc(post.getAccId());
					post.setName(name.getName());
					post.setPhone(name.getPhone());
				}
				request.setAttribute("postList", postList);
				request.setAttribute("maxPage",maxPage);
				request.setAttribute("pg", pg);
				request.setAttribute("item", "4");
				RequestDispatcher rd = request.getRequestDispatcher("View/Manage/StoredPost.jsp");
				rd.forward(request,response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
