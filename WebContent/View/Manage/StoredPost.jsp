<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Các bài đăng đã quan tâm</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
    <link rel="stylesheet" href="Template/css/font-awesome.min.css">
    <script src="Template/js/jquery-3.2.1.slim.min.js"></script>
  	<script src="Template/js/bootstrap.min.js"></script>
</head>

<body>	
    <jsp:include page="_user-menu.jsp"></jsp:include>
    
    <div class="user-body container-fluid">
        <div class="row">
            <jsp:include page="_left-col.jsp"></jsp:include>
            <div class="col-9 right-col">
                <h5 class="float-left">Các bài đăng đã quan tâm</h5>
                     
                <c:if test="${not empty maxPage}">
	                <div class="float-right">
	  					<ul class="pagination"><li>
	  					<c:forEach var = "i" begin = "1" end = "${maxPage}">
	    					<c:if test="${pg!=i}">
	    						<li class="page-item"><a class="page-link" href="StorePost?page=${i}">${i}</a></li><li>
	    					</c:if>
	    					<c:if test="${pg==i}">
	    						<li class="page-item active"><a class="page-link" href="StorePost?page=${i}">${i}</a></li>
	    					</c:if>
	    				</c:forEach>
	  					</li></ul>
					</div>
				</c:if>
				
                <table class="table table-bordered" style="font-size: 13px;">
                    <thead>
                      <tr>
                        <th scope="col">Mã tin</th>
                        <th scope="col">Người đăng</th>
                        <th scope="col">Ảnh</th>
                        <th scope="col">Chuyên mục</th>
                        <th scope="col">Tiêu đề</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Ngày đăng</th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="post" items="${postList}">
                      		<tr>
                        		<th scope="row">${post.getPostId()}</th>
                        		<td>${post.getName()} ${post.getPhone()}</td>
                        		<td><c:if test="${not empty post.url}"><img src="${post.getUrl()}" style="width:100px;height: 100px;"></c:if></td>
                        		<td>${post.getCategory()}</td>
                        		<td>${post.getTitle()}</td>
                        		<td class="text-success">${post.getPrice()} Đồng/Tháng</td>
                        		<td>${post.getCreatedAt()}</td>
   
                        		<td>
                        			<a href="" data-toggle="modal" data-target="#delete${post.getPostId()}">Xóa</a>							
								  	<div class="modal" id="delete${post.getPostId()}">
								      <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Bạn muốn xóa bài đăng này khỏi các bài đăng đã quan tâm?</h5>
								          </div>
					
								          <div class="modal-footer">
								            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
								            <a href ="StorePost?delete=${post.getPostId()}&page=${pg}"  class="btn btn-primary">Xóa</a>
								          </div>
								        </div>
								      </div>
								    </div>
                        		</td>
                      		</tr>
                      	</c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>

</html>