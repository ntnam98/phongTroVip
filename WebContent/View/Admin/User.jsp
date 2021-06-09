<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Tài khoản người dùng</title>

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
                <h4>Danh sách các tài khoản người dùng</h4>
                <c:if test="${not empty maxPage}">
	                <div class="float-right">
	  					<ul class="pagination"><li>
	  					<c:forEach var = "i" begin = "1" end = "${maxPage}">
	    					<c:if test="${pg!=i}">
	    						<li class="page-item"><a class="page-link" href="AdminUser?page=${i}">${i}</a></li><li>
	    					</c:if>
	    					<c:if test="${pg==i}">
	    						<li class="page-item active"><a class="page-link" href="AdminUser?page=${i}">${i}</a></li>
	    					</c:if>
	    				</c:forEach>
	  					</li></ul>
					</div>
				</c:if>
                <table class="table table-bordered" style="font-size: 13px;">
                    <thead>
                      <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Tên đăng nhập</th>
                        <th scope="col">Quyền</th>
                        <th scope="col">Họ tên</th>
                        <th scope="col">SĐT</th>
                        <th scope="col">Số bài đăng</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="user" items="${users}">
	                      <tr>
	                        <th scope="row">${user.getAccId()}</th>
	                        <td>${user.getUserName()}</td>
	                        <td>${user.getRole()}</td>
	                        <td>${user.getName()}</td>
	                        <td>${user.getPhone()}</td>
	                        <td>${user.getTotalPost()}</td>
	                        <td class="text-warning">
                        			<c:if test="${user.isActive()==true}">Đang hoạt động</c:if>
                        			<c:if test="${user.isActive()==false}">Đang khóa</c:if>
                        	</td>
                        	<td>${user.getCreatedAt()}</td>
	                        
	                        <c:if test="${user.getAccId()!= admin.getAccId()}">
								<c:if test="${user.isActive()==true}"><td><a href="AdminUser?lock=${user.getAccId()}&page=${pg}">Khóa</a></td></c:if>
                        		<c:if test="${user.isActive()==false}"><td><a href="AdminUser?unlock=${user.getAccId()}&page=${pg}">Mở khóa</a></td></c:if>
							</c:if>
	                        <td>
	                        	<c:if test="${user.getAccId()!= admin.getAccId()}">
	                        		<a href="" data-toggle="modal" data-target="#delete${user.getAccId()}">Xóa</a>							
								  	<div class="modal" id="delete${user.getAccId()}">
								      <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Bạn muốn xóa tài khoản này?</h5>
								          </div>
										  <div class="modal-body">
								            <p style="font-size:15px;">Tất cả bài đăng của tài khoản này cũng bị xóa cùng tài khoản.</p>
								          </div>
								          <div class="modal-footer">
								            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
								            <a href ="AdminDelete?user=${user.getAccId()}&pg=${pg}"  class="btn btn-primary">Xóa</a>
								          </div>
								        </div>
								      </div>
								    </div>
								</c:if>
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