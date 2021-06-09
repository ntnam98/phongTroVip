package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.*;
import DAO.*;

@WebServlet("/PostInfo")
public class PostInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostInfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String postId = request.getParameter("postId");
		int id = Integer.parseInt(postId);
		Post post = PostDAO.getPost(id);
		Account user = AccountDAO.getAcc(post.getAccId());
		ArrayList<String> url =ImageDAO.getAllUrl(id);
		String cate = CategoryDAO.getCategory(post.getCategoryId());
		
		String cr = post.getCreatedAt();
		String[] parts =cr.split(" ");
		String[] part1 = parts[0].split("-");
		String[] part2 = parts[1].split("\\.");
		cr = part1[2]+"-"+part1[1]+"-"+part1[0]+" "+part2[0];
		post.setCreatedAt(cr);
		
		String content = post.getContent();
		String ct = content.replace("\n", "<br>");
		post.setContent(ct);
		int total1 = PostDAO.getTotalPost1("1","1","%","%","%",0,2000000000,0,2000000000);
		int total2 = PostDAO.getTotalPost1("1","2","%","%","%",0,2000000000,0,2000000000);
		int total3 = PostDAO.getTotalPost1("1","3","%","%","%",0,2000000000,0,2000000000);
		int total4 = PostDAO.getTotalPost1("1","4","%","%","%",0,2000000000,0,2000000000);
		request.setAttribute("total1",total1);
		request.setAttribute("total2",total2);
		request.setAttribute("total3",total3);
		request.setAttribute("total4",total4);
		request.setAttribute("post", post);
		request.setAttribute("poster", user);
		request.setAttribute("url", url);
		request.setAttribute("cate", cate);
		request.setAttribute("menu", post.getCategoryId());
		RequestDispatcher rd = request.getRequestDispatcher("View/Post.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Post.jsp");
		rd.forward(request,response);
	}
}
