<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Trang chủ</title>
	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
	<link rel="stylesheet" href="Template/css/font-awesome.min.css">	
    
</head>

<body class="home">	
	<c:if test="${empty menu}">
		<c:set scope="request" var="menu" value="0" />
	</c:if>
	<%@ page import="java.sql.* "%>
	
	<jsp:include page="View/_header.jsp"></jsp:include>
    <jsp:include page="View/_menu.jsp"></jsp:include>
    <jsp:include page="View/_search-form.jsp"></jsp:include>

	<div class="container body">
		<div class="row">
			<div class="col-8 left-col">
				<h5 style="margin: 10px 0 10px 20px;">Các bài đăng mới nhất</h5>
				<ul class="navbar-nav">
				<% 
				 Connection conn = null;
				 String database = "phongTroVip";
				 String url = "jdbc:jtds:sqlserver://localhost:1433/"
				            + database + ";instance=SQLEXPRESS";
				 String username = "sa";
				 String password = "123456";
				 Class.forName("net.sourceforge.jtds.jdbc.Driver");
				 conn = DriverManager.getConnection(url, username, password);

			     String sql1 = "Select top 10 * from Post where isActive='1' order by createdAt desc";
			     PreparedStatement ptmt = conn.prepareStatement(sql1);
				 ResultSet rs = ptmt.executeQuery();
			     
			     while (rs.next()) {
			    	 String cr = rs.getString("createdAt");
					 String[] parts =cr.split(" ");
					 cr = parts[0];
					 parts =cr.split("-");
					 cr = parts[2]+"-"+parts[1]+"-"+parts[0];
					 String sql4 = "select * from Category where categoryId ='"+rs.getInt("categoryId")+"'";
			    	 ptmt = conn.prepareStatement(sql4);
					 ResultSet rs4 = ptmt.executeQuery();
					 String cate="";
					 if(rs4.next()) cate= rs4.getString("name");
			    	 out.println("<li class='post-item' style='font-size:14px;''>");
			    	 String sql2 = "select * from Image where postId ='"+rs.getInt("postId")+"'";
			    	 ptmt = conn.prepareStatement(sql2);
					 ResultSet rs2 = ptmt.executeQuery();
			    	 if(rs2.next()) out.println("<a href='PostInfo?postId="+rs.getInt("postId")+"'><img src="+rs2.getString("url")+"></a>");
			    	 else out.println("<a href='PostInfo?postId="+rs.getInt("postId")+"'><img src='Template/img/logo2.png'></a>");
			    	 out.println("<div class='item-info'>");
			    	 out.println("<a href='PostInfo?postId="+rs.getInt("postId")+"'><h6>"+rs.getString("title")+"</h6></a>");
			    	 out.println("<span style='color:green;font-weight:bold;'><i class='fa fa-money'></i> "+rs.getInt("price")+" Đồng/Tháng</span>");
			    	 out.println("<span style='color:blue;font-weight:bold;'><i class='fa fa-th-large' style='margin-left: 50px;'></i> "+rs.getInt("area")+"m2</span>");
			    	 out.println("<div style='margin: 5px 0'><i class='fa fa-map-marker'></i> "+rs.getString("address")+", "+rs.getString("ward")+", "+rs.getString("district")+", "+rs.getString("city")+"</div>");
			    	 out.println("<div style='margin-bottom:10px;'><i class='fa fa-calendar'></i> "+cr+"</div>");
			    	 out.println("<div style='margin-bottom:10px;'><i class='fa fa-bars'></i> "+cate+"</div>");
			    	 String sql3 = "select * from Account where accId ='"+rs.getInt("accId")+"'";
			    	 ptmt = conn.prepareStatement(sql3);
					 ResultSet rs3 = ptmt.executeQuery();
					 if(rs3.next()){
					 out.println("<span class='float-left'>Người đăng: <b>"+rs3.getString("name")+"</b></span>");
			    	 out.println("<span class='float-right' style='color: blue;font-weight:bold;'><i class='fa fa-phone'></i> "+rs3.getString("phone")+"</span>");
					 }
			    	 out.println("</div>");
			    	 out.println("</li>");
			     }
			     
				%>
				</ul>
			</div>
			<% 
				String sql5 = "select COUNT(*) as total\r\n" + 
								"from Post\r\n" + 
								"where isActive='1' and categoryId like '"+1+"'";
				ptmt = conn.prepareStatement(sql5);
				ResultSet rs5 = ptmt.executeQuery();
				if(rs5.next()) request.setAttribute("total1", rs5.getInt("total"));
				
				String sql6 = "select COUNT(*) as total\r\n" + 
						"from Post\r\n" + 
						"where isActive='1' and categoryId like '"+2+"'";
				ptmt = conn.prepareStatement(sql6);
				ResultSet rs6 = ptmt.executeQuery();
				if(rs6.next()) request.setAttribute("total2", rs6.getInt("total"));
		
				String sql7 = "select COUNT(*) as total\r\n" + 
						"from Post\r\n" + 
						"where isActive='1' and categoryId like '"+3+"'";
				ptmt = conn.prepareStatement(sql7);
				ResultSet rs7 = ptmt.executeQuery();
				if(rs7.next()) request.setAttribute("total3", rs7.getInt("total"));

				String sql8 = "select COUNT(*) as total\r\n" + 
						"from Post\r\n" + 
						"where isActive='1' and categoryId like '"+4+"'";
				ptmt = conn.prepareStatement(sql8);
				ResultSet rs8 = ptmt.executeQuery();
				if(rs8.next()) request.setAttribute("total4", rs8.getInt("total"));
			%>
			<div class="col-4">
				<div class="right-col">
					<div class="row">
						<div class="col-12 first-row">
							<h6>Danh mục cho thuê</h6>
							<ul class="nav flex-column">
								<li class="nav-item">
								  <a class="nav-link" href="Search?menu=1"><i class="fa fa-chevron-right"></i> Cho thuê phòng trọ (${total1})</a>
								</li>
								<li class="nav-item">
								  <a class="nav-link" href="Search?menu=2"><i class="fa fa-chevron-right"></i> Nhà cho thuê (${total2})</a>
								</li>
								<li class="nav-item">
								  <a class="nav-link" href="Search?menu=3"><i class="fa fa-chevron-right"></i> Tìm người ở ghép (${total3})</a>
								</li>
								<li class="nav-item">
								  <a class="nav-link" href="Search?menu=4"><i class="fa fa-chevron-right"></i> Cho thuê mặt bằng (${total4})</a>
								</li>
							  </ul>
						</div>
					</div>
					<div class="row">
						<div class="col-12 first-row">
							<h6>Liên hệ với chúng tôi</h6>
							<div class="op"><i class="fa fa-fax"></i> 0123.456.789 </div>
  							<div class="op"><i class="fa fa-phone"></i> 0969951716 </div>
                			<div class="op"><i class="fa fa-envelope"></i>  ntnam.1712@gmail.com </div>
                			<div class="op"><a href="FeedBack?fb=1"><i class="fa fa-reply-all"></i> <b>Gửi phản hồi cho chúng tôi</b></a></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="View/_footer.jsp"></jsp:include>
	<script src="Template/js/axios.min.js"></script>
	<script src="Template/js/main.js"></script>
</body>

</html> 