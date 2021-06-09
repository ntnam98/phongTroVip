package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ImageDAO;
import DAO.PostDAO;
import DAO.ReportDAO;
import DAO.StoredPostDAO;

@WebServlet("/DeletePost")
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeletePost() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String admin = request.getParameter("admin");
		String post = request.getParameter("post");
		int postId = Integer.parseInt(post);
		String pg = request.getParameter("pg");
		int page = Integer.parseInt(pg);
	
		ArrayList<String> list = ImageDAO.getAllUrl(postId);
		for(String url: list) {
			File file = new File(System.getProperty("user.home") + "/eclipse-workspace/PhongTroVIP/WebContent/"+url);
			file.delete();
		}
		StoredPostDAO.deleteSP1(postId);
		ReportDAO.deleteRp1(postId);
		PostDAO.DeletePost(postId);
			
		if(admin==null) {
			if(page == 1) {
				response.sendRedirect("ManagePost");
			}
			else {
				response.sendRedirect("ManagePost?page="+page);
			}
		}else {
			if(page == 1) {
				response.sendRedirect("AdminPost");
			}
			else {
				response.sendRedirect("AdminPost?page="+page);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);

	}
}
