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

@WebServlet("/Search")

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String menu = request.getParameter("menu");
		String city = "%";
		String district = "%";
		String ward = "%";
		int area1 = 0;
		int area2 =  2000000000;
		int price1 = 0;
		int price2 =  2000000000;
		
		String page=  request.getParameter("page");
		if(page== null) page="1";
		int pg = Integer.parseInt(page);
		int totalPost = PostDAO.getTotalPost1("1",menu, city, district, ward, price1, price2, area1, area2);
		int maxResult = 10;
		int maxPage = (totalPost+maxResult-1)/maxResult;
		int offset = (pg-1)* maxResult;
		
		int total1 = PostDAO.getTotalPost1("1","1","%","%","%",0,2000000000,0,2000000000);
		int total2 = PostDAO.getTotalPost1("1","2","%","%","%",0,2000000000,0,2000000000);
		int total3 = PostDAO.getTotalPost1("1","3","%","%","%",0,2000000000,0,2000000000);
		int total4 = PostDAO.getTotalPost1("1","4","%","%","%",0,2000000000,0,2000000000);
		request.setAttribute("total1",total1);
		request.setAttribute("total2",total2);
		request.setAttribute("total3",total3);
		request.setAttribute("total4",total4);
		request.setAttribute("menu",Integer.parseInt(menu));
		String category = CategoryDAO.getCategory(Integer.parseInt(menu));
		request.setAttribute("category",category);
		
		ArrayList<Post> postList =  PostDAO.getPost1("1",menu, city, district, ward, price1, price2, area1, area2, offset, maxResult);
		for(Post post: postList) {
			Account user = AccountDAO.getAcc(post.getAccId());
			String url = ImageDAO.getUrl(post.getPostId());
			post.setName(user.getName());
			post.setPhone(user.getPhone());
			post.setUrl(url);
			String cr = post.getCreatedAt();
			String[] parts =cr.split(" ");
			cr = parts[0];
			parts =cr.split("-");
			cr = parts[2]+"-"+parts[1]+"-"+parts[0];
			post.setCreatedAt(cr);
		}
		request.setAttribute("postList", postList);
		request.setAttribute("maxPage",maxPage);
		request.setAttribute("pg", pg);
		RequestDispatcher rd = request.getRequestDispatcher("View/Search.jsp");
		rd.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String menu = request.getParameter("type");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String ward = request.getParameter("ward");
		String price = request.getParameter("price");
		int p = Integer.parseInt(price);
		String area = request.getParameter("area");
		int a = Integer.parseInt(area);
		
		String page=  request.getParameter("page");
		if(page== null) page="1";
		int pg = Integer.parseInt(page);
		int area1 = 0;
		int area2 =  2000000000;
		int price1 = 0;
		int price2 =  2000000000;
		if(p==1) {	price2 = 1000000;}
		else if(p==2) {	price1 = 1000000; price2=2000000;}
		else if(p==3) {	price1 = 2000000; price2=5000000;}
		else if(p==4) {	price1 = 5000000;}
		
		if(a==1) {	area2=20;}
		else if(a==2) {	area1 = 20; area2=40;}
		else if(a==3) {	area1 = 40; area2=80;}
		else if(a==4) {	area1 = 80;}
		
		int totalPost = PostDAO.getTotalPost1("1",menu, city, district, ward, price1, price2, area1, area2);
		int maxResult = 10;
		int maxPage = (totalPost+maxResult-1)/maxResult;
		int offset = (pg-1)* maxResult;
		
		int total1 = PostDAO.getTotalPost1("1","1","%","%","%",0,2000000000,0,2000000000);
		int total2 = PostDAO.getTotalPost1("1","2","%","%","%",0,2000000000,0,2000000000);
		int total3 = PostDAO.getTotalPost1("1","3","%","%","%",0,2000000000,0,2000000000);
		int total4 = PostDAO.getTotalPost1("1","4","%","%","%",0,2000000000,0,2000000000);
		request.setAttribute("total1",total1);
		request.setAttribute("total2",total2);
		request.setAttribute("total3",total3);
		request.setAttribute("total4",total4);
		request.setAttribute("menu",Integer.parseInt(menu));
		if(menu.equals("0")) { 
			menu="%";
			request.setAttribute("category","Danh sách bài đăng");
		}
		else {
			String category = CategoryDAO.getCategory(Integer.parseInt(menu));
			request.setAttribute("category",category);
		}
		ArrayList<Post> postList =  PostDAO.getPost1("1",menu, city, district, ward, price1, price2, area1, area2, offset, maxResult);
		for(Post post: postList) {
			Account user = AccountDAO.getAcc(post.getAccId());
			String url = ImageDAO.getUrl(post.getPostId());
			post.setName(user.getName());
			post.setPhone(user.getPhone());
			post.setUrl(url);
			String cr = post.getCreatedAt();
			String[] parts =cr.split(" ");
			cr = parts[0];
			parts =cr.split("-");
			cr = parts[2]+"-"+parts[1]+"-"+parts[0];
			post.setCreatedAt(cr);
		}
		request.setAttribute("postList", postList);
		request.setAttribute("maxPage",maxPage);
		request.setAttribute("pg", pg);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Search.jsp");
		rd.forward(request,response);
	}
	
}
