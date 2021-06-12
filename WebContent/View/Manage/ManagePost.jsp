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
    <jsp:include page="_user-menu.jsp"></jsp:include>
    
    <div class="user-body container-fluid">
        <div class="row">
            <jsp:include page="_left-col.jsp"></jsp:include>
            <div class="col-9 right-col">
                <h5 class="float-left">Quản lí bài đăng</h5>
                     
                <c:if test="${not empty maxPage}">
	                <div class="float-right">
	  					<ul class="pagination"><li>
	  					<c:forEach var = "i" begin = "1" end = "${maxPage}">
	    					<c:if test="${pg!=i}">
	    						<li class="page-item"><a class="page-link" href="ManagePost?page=${i}">${i}</a></li><li>
	    					</c:if>
	    					<c:if test="${pg==i}">
	    						<li class="page-item active"><a class="page-link" href="ManagePost?page=${i}">${i}</a></li>
	    					</c:if>
	    				</c:forEach>
	  					</li></ul>
					</div>
				</c:if>
				
                <table class="table table-bordered" style="font-size: 13px;">
                    <thead>
                      <tr>
                        <th scope="col">Mã tin</th>
                        <th scope="col">Ảnh</th>
                        <th scope="col">Chuyên mục</th>
                        <th scope="col">Tiêu đề</th>
                        <th scope="col">Giá</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Ngày đăng</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="post" items="${postList}">
                      		<tr>
                        		<th scope="row">#${post.getPostId()}</th>
                        		<td><c:if test="${not empty post.url}"><a href="PostInfo?postId=${post.getPostId()}"><img src="${post.getUrl()}" style="width:100px;height: 100px;"></a></c:if></td>
                        		<td>${post.getCategory()}</td>
                        		<td><a href="PostInfo?postId=${post.getPostId()}">${post.getTitle()}</a></td>
                        		<td class="text-success">${post.getPrice()} Đồng/Tháng</td>
                        		<td class="text-warning">
                        			<c:if test="${post.isActive()==true}">Đang hoạt động</c:if>
                        			<c:if test="${post.isActive()==false}">Đang ẩn</c:if>
                        		</td>
                        		<td>${post.getCreatedAt()}</td>
   
                        		<c:if test="${post.isActive()==true}"><td><a href="ManagePost?hide=${post.getPostId()}&page=${pg}">Ẩn</a></td></c:if>
                        		<c:if test="${post.isActive()==false}"><td><a href="ManagePost?show=${post.getPostId()}&page=${pg}">Hiện</a></td></c:if>
  
                        		<td><a href="" data-toggle="modal" data-target="#fix${post.getPostId()}">Sửa</a>							
								  	<div class="modal" id="fix${post.getPostId()}">
								      <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Sửa bài đăng</h5>
								          </div>
										  <div class="modal-body">
										  	<form action="ChangePost?post=${post.getPostId()}&pg=${pg}" method="POST">   					                    
							                    <label class="lb" style="margin-top:0;">Địa chỉ (số nhà, ngõ, tên đường)</label>
							                    <input type="text" name="address" class="form-control" value="${post.getAddress()}" required>
							
							                    <label class="lb">Tiêu đề</label>
							                    <input type="text" name="title" class="form-control" value="${post.getTitle()}" required>
							
							                    <label class="lb">Thông tin mô tả</label>
							                    <textarea name="content" class="form-control" required rows="6">${post.getContent()}</textarea>
										
							                    <label class="lb">Giá cho thuê</label>
							                    <div class="form-group tb">
							                        <input type="number" name="price" class="form-control"  value="${post.getPrice()}" required>
							                        <span class="input-group-append">
							                            <span class="input-group-text">Đồng/Tháng</span>
							                        </span>
							                    </div>
							
							                    <label class="lb">Diện tích</label>
							                    <div class="form-group tb">
							                        <input type="number" name="area" class="form-control" value="${post.getArea()}" required>
							                        <span class="input-group-append">
							                            <span class="input-group-text">m2</span>
							                        </span>
							                    </div>
							                    <button type="submit" class="btn btn-primary">Cập nhật</button>
							                </form>
										  </div>
								        </div>
								      </div>
								    </div>
								</td>
                        		<td>
                        			<a href="" data-toggle="modal" data-target="#delete${post.getPostId()}">Xóa</a>							
								  	<div class="modal" id="delete${post.getPostId()}">
								      <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Bạn muốn xóa bài đăng này?</h5>
								          </div>
										  <div class="modal-body">
								            <p style="font-size:15px;">Bài đăng này sẽ bị xóa vĩnh viễn trên hệ thống.</p>
								          </div>
								          <div class="modal-footer">
								            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
								            <a href ="DeletePost?post=${post.getPostId()}&pg=${pg}"  class="btn btn-primary">Xóa</a>
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