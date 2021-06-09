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

import BEAN.Account;
import BEAN.FeedBack;
import DAO.FeedBackDAO;


@WebServlet("/AdminFeedBack")
public class AdminFeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminFeedBack() {
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
				FeedBackDAO.deleteFb(Integer.parseInt(delete));
			}else if(solve !=null) {
				FeedBackDAO.solveFb(Integer.parseInt(solve));
			}
			String page=  request.getParameter("page");
			int pg = 1;
			if(page!= null)	pg = Integer.parseInt(page);			
    		int totalFb = FeedBackDAO.getTotalFb();
    		int maxResult = 10;
    		int maxPage = (totalFb+maxResult-1)/maxResult;
    		int offset = (pg-1)* maxResult;
    		
			request.setAttribute("maxPage",maxPage);
			request.setAttribute("pg", pg);
			ArrayList<FeedBack> list = FeedBackDAO.getFb(offset, maxResult);
			
			request.setAttribute("list", list);
			request.setAttribute("menu", 5);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/FeedBack.jsp");
			rd.forward(request,response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
