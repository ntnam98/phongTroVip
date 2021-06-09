package Controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.*;
import BEAN.*;
@WebServlet("/NewPost")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50, // 50MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

public class NewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NewPost() {
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
			RequestDispatcher rd = request.getRequestDispatcher("View/Manage/NewPost.jsp");
			rd.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String ward = request.getParameter("ward");
		String address = request.getParameter("address");
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String area = request.getParameter("area");
		int categoryInt = Integer.parseInt(category);
		int priceInt = Integer.parseInt(price);
		int areaInt = Integer.parseInt(area);
		
		HttpSession httpSession = request.getSession();
		Account user = (Account)httpSession.getAttribute("user");

		PostDAO.addNewPost(user.getAccId(), city, district, ward, address, categoryInt, title, content, priceInt, areaInt);
		int postId = PostDAO.getPostId();
		
		int i=0;
		for (Part part : request.getParts()) {
			String fileName = extractFileName(part);
		    fileName = new File(fileName).getName();
		   
		    if(fileName != null && fileName.length() > 0) {
			i++;
			String url = "Image/PostId-" + postId +"-"+ i+".png";
			ImageDAO.addImage(postId, url);
		    part.write(this.getFolderUpload().getAbsolutePath() + File.separator + url);
		    }
		}
		
		response.sendRedirect("ManagePost");
	}
			   
	private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	      if (s.trim().startsWith("filename")) {
	        return s.substring(s.indexOf("=") + 2, s.length() - 1);
	      }
	    }
	    return "";
	}
	public File getFolderUpload() {   
		File folderUpload = new File(System.getProperty("user.home") + "/eclipse-workspace/PhongTroVIP/WebContent");
		if (!folderUpload.exists()) {
		    folderUpload.mkdirs();
		}
		return folderUpload;
	}
	
}
