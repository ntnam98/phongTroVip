package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Post;
import DAO.*;

@WebServlet("/AdminDelete")
public class AdminDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pg = request.getParameter("pg");
		int page = Integer.parseInt(pg);
		String admin = request.getParameter("admin");
		int userId;
		if(admin==null) { 
			String user = request.getParameter("user");
			userId = Integer.parseInt(user);
		}else {
			userId = Integer.parseInt(admin);
		}		
		
		ArrayList<Post> posts = PostDAO.getPosts(userId, 0, 1000000);
		for(Post post: posts) {
			ArrayList<String> list = ImageDAO.getAllUrl(post.getPostId());
			for(String url: list) {
				File file = new File(System.getProperty("user.home") + "/eclipse-workspace/PhongTroVIP/WebContent/"+url);
				file.delete();
			}
			StoredPostDAO.deleteSP1(post.getPostId());
			ReportDAO.deleteRp1(post.getPostId());
			PostDAO.DeletePost(post.getPostId());
		}
		StoredPostDAO.deleteSP2(userId);
		AccountDAO.deleteAcc(userId);
		
		if(admin!=null) { 
			if(page == 1) {
				response.sendRedirect("AdminAdmin");
			}
			else {
				response.sendRedirect("AdminAdmin?page="+page);
			}
		}else {
			if(page == 1) {
				response.sendRedirect("AdminUser");
			}
			else {
				response.sendRedirect("AdminUser?page="+page);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);

	}
}

