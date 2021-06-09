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


@WebServlet("/AdminReport")
public class AdminReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminReport() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		Account admin = (Account)httpSession.getAttribute("admin");
		if(admin==null) {
			RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
			rd.forward(request,response);
		}else {
			String delete =  request.getParameter("delete");
			String solve =  request.getParameter("solve");
			if(delete!=null) {
				ReportDAO.deleteRp(Integer.parseInt(delete));
			}else if(solve !=null) {
				ReportDAO.solveRp(Integer.parseInt(solve));
			}
			String page=  request.getParameter("page");
			int pg = 1;
			if(page!= null)	pg = Integer.parseInt(page);			
    		int totalRp = ReportDAO.getTotalRp();
    		int maxResult = 10;
    		int maxPage = (totalRp+maxResult-1)/maxResult;
    		int offset = (pg-1)* maxResult;
    		
			request.setAttribute("maxPage",maxPage);
			request.setAttribute("pg", pg);
			ArrayList<Report> list = ReportDAO.getRp(offset, maxResult);
			for(Report rp:list) {
				Post post = PostDAO.getPost(rp.getPostId());
				String url = ImageDAO.getUrl(post.getPostId());
				rp.setUrl(url);
				Account poster = AccountDAO.getAcc(post.getAccId());
				rp.setName(poster.getName());
				rp.setAccId(post.getAccId());
			}
			request.setAttribute("list", list);
			request.setAttribute("menu", 6);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/Report.jsp");
			rd.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String post = request.getParameter("post");
		int postId = Integer.parseInt(post);
		String type = request.getParameter("type");
		String phone = request.getParameter("phone");
		String content = request.getParameter("content");
		ReportDAO.newRp(postId, type, phone, content);
		response.sendRedirect("PostInfo?postId="+postId);
	}
}
