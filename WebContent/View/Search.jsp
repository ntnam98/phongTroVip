<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<c:if test="${not empty category}">
		<title>${category}</title>
	</c:if>
	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
	<link rel="stylesheet" href="Template/css/font-awesome.min.css">
</head>

<body class="home">	
	<jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    <jsp:include page="_search-form.jsp"></jsp:include>
	
	<div class="container body">
		
		<div class="row">
			<div class="col-8 left-col">	
				<c:if test="${not empty category}">
					<h5 style="margin: 10px 0 10px 20px;">${category}</h5>
				</c:if>
				<c:if test="${empty postList}">
					<a style="margin: 10px 0 10px 20px;">Không tìm thấy bài đăng nào! Vui lòng tìm kiếm khác</a>
				</c:if>
				<ul class="navbar-nav">		
					<c:forEach var="post" items="${postList}">	
					<li class="post-item" style="font-size:14px;">
						<c:if test="${not empty post.url}">
							<a href="PostInfo?postId=${post.getPostId()}"><img  src="${post.getUrl()}"></a>
						</c:if>
						<c:if test="${empty post.url}">
							<a href="PostInfo?postId=${post.getPostId()}"><img  src="Template/img/logo2.png"></a>
						</c:if>
						<div class="item-info">
							<a href="PostInfo?postId=${post.getPostId()}"><h6>${post.getTitle()}</h6></a>
							<span style="color:green;font-weight:bold;"><i class="fa fa-money"></i> ${post.getPrice()} Đồng/Tháng</span>
							<span style="color:blue;font-weight:bold;"><i class="fa fa-th-large" style="margin-left: 50px;"></i> ${post.getArea()}m2</span>
							<div style="margin: 5px 0"><i class="fa fa-map-marker" aria-hidden="true"></i> ${post.getAddress()}, ${post.getWard()}, ${post.getDistrict()}, ${post.getCity()}</div>
							<div style="margin-bottom:10px;"><i class="fa fa-calendar" aria-hidden="true"></i> ${post.getCreatedAt()}</div>
							<div style="margin-bottom:10px;"><i class="fa fa-bars" aria-hidden="true"></i> ${category}</div>
							<span class="float-left">Người đăng: <b>${post.getName()}</b></span>
							<span class="float-right" style="color: blue;font-weight:bold;"><i class="fa fa-phone" aria-hidden="true"></i> ${post.getPhone()}</span>
						</div>
					</li> 
					</c:forEach>	
				</ul>
				
				<c:if test="${not empty maxPage}">
	                <div class="" style="margin:10px">
	  					<ul class="pagination"><li>
	  					<c:forEach var = "i" begin = "1" end = "${maxPage}">
	    					<c:if test="${pg!=i}">
	    						<li class="page-item"><a class="page-link" href="Search?menu=${menu}&page=${i}">${i}</a></li><li>
	    					</c:if>
	    					<c:if test="${pg==i}">
	    						<li class="page-item active"><a class="page-link" href="Search?menu=${menu}&page=${i}">${i}</a></li>
	    					</c:if>
	    				</c:forEach>
	  					</li></ul>
					</div>
				</c:if>
			</div>
			<div class="col-4">
				<jsp:include page="_right-col.jsp"></jsp:include>
			</div>
		</div>
	</div>
	
	<jsp:include page="_footer.jsp"></jsp:include>
	
	<script src="Template/js/axios.min.js"></script>
	<script src="Template/js/main.js"></script>
</body>

</html> 