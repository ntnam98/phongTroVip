<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Quản lí bài đăng</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
    <link rel="stylesheet" href="Template/css/font-awesome.min.css">
    <script src="Template/js/jquery-3.2.1.slim.min.js"></script>
  	<script src="Template/js/bootstrap.min.js"></script>
</head>

<body>	
    <div class="user-body container-fluid">
        <div class="row">
            <jsp:include page="_left-col.jsp"></jsp:include>
            <div class="col-9 right-col">
                <h4>Danh sách các bài đăng</h4>
                <c:if test="${not empty maxPage}">
	                <div class="float-right">
	  					<ul class="pagination"><li>
	  					<c:forEach var = "i" begin = "1" end = "${maxPage}">
	    					<c:if test="${pg!=i}">
	    						<li class="page-item"><a class="page-link" href="AdminPost?page=${i}">${i}</a></li><li>
	    					</c:if>
	    					<c:if test="${pg==i}">
	    						<li class="page-item active"><a class="page-link" href="AdminPost?page=${i}">${i}</a></li>
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
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Ngày đăng</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="post" items="${posts}">
                      		<tr>
                        		<th scope="row">${post.getPostId()}</th>
                        		<td>Id: ${post.getAccId()}<br>${post.getName()}</td>
                        		<td><c:if test="${not empty post.url}"><a href="PostInfo?postId=${post.getPostId()}"><img src="${post.getUrl()}" style="width:100px;height: 100px;"></a></c:if></td>
                        		<td>${post.getCategory()}</td>
                        		<td><a href="PostInfo?postId=${post.getPostId()}">${post.getTitle()}</a></td>
                        		<td class="text-success">${post.getPrice()} Đồng/Tháng</td>
                        		<td class="text-warning">
                        			<c:if test="${post.isActive()==true}">Đang hoạt động</c:if>
                        			<c:if test="${post.isActive()==false}">Đang ẩn</c:if>
                        		</td>
                        		<td>${post.getCreatedAt()}</td>
   
                        		<c:if test="${post.isActive()==true}"><td><a href="AdminPost?hide=${post.getPostId()}&page=${pg}">Ẩn</a></td></c:if>
                        		<c:if test="${post.isActive()==false}"><td><a href="AdminPost?show=${post.getPostId()}&page=${pg}">Hiện</a></td></c:if>
  
                        		<td>
                        			<a href="" data-toggle="modal" data-target="#delete">Xóa</a>							
								  	<div class="modal" id="delete">
								      <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Bạn muốn xóa bài đăng này?</h5>
								          </div>
										  <div class="modal-body">
								            <p style="font-size:15px;">Bài đăng này sẽ bị xóa vĩnh viễn trên hệ thống</p>
								          </div>
								          <div class="modal-footer">
								            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
								            <a href ="DeletePost?post=${post.getPostId()}&pg=${pg}&admin=1"  class="btn btn-primary">Xóa</a>
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