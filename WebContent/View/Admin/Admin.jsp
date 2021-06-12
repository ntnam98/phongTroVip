<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Tài khoản Admin</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
    <link rel="stylesheet" href="Template/css/font-awesome.min.css">
    <script src="Template/js/jquery.min.js"></script>
	<script src="Template/js/main.js"></script>
	<script src="Template/js/jquery-3.2.1.slim.min.js"></script>
  	<script src="Template/js/bootstrap.min.js"></script>
</head>

<body>	
    <div class="user-body container-fluid">
        <div class="row">
            <jsp:include page="_left-col.jsp"></jsp:include>
            <div class="col-9 right-col">
                <h4>Danh sách các tài khoản Admin</h4>
                <a href=""  data-toggle="modal" data-target="#add" class="bn">Thêm mới +</a>
                <c:if test="${not empty maxPage}">
	                <div class="float-right">
	  					<ul class="pagination"><li>
	  					<c:forEach var = "i" begin = "1" end = "${maxPage}">
	    					<c:if test="${pg!=i}">
	    						<li class="page-item"><a class="page-link" href="AdminAdmin?page=${i}">${i}</a></li><li>
	    					</c:if>
	    					<c:if test="${pg==i}">
	    						<li class="page-item active"><a class="page-link" href="AdminAdmin?page=${i}">${i}</a></li>
	    					</c:if>
	    				</c:forEach>
	  					</li></ul>
					</div>
				</c:if>
                <div class="modal" id="add">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Thêm tài khoản</h5>
							</div>
							<div class="modal-body">
								<form action="Signup?admin=1" method="POST" class="needs-validation" novalidate>
	                                <div class="form-group">
	                                    <input type="text" name="name" class="input-text form-control" id ="name" placeholder="Họ Tên" required>
	                                    <div class="invalid-feedback text-left">Vui lòng nhập họ tên</div>
	                                </div>
	                                <div class="form-group">
	                                    <input type="text" name="userName" class="input-text form-control" id = "user" placeholder="Tên đăng nhập" required>
	                                    <div class="invalid-feedback text-left">Vui lòng nhập tên đăng nhập</div>
	                                </div>
	                                <div class="form-group">
	                                    <input type="text" name="phone" class="input-text form-control" id ="phone" placeholder="Số điện thoại" required>
	                                    <div class="invalid-feedback text-left">Vui lòng nhập số điện thoại</div>
	                                </div>
	                                <div class="form-group">
	                                    <input type="password" name="password" class="input-text form-control" id="pw" placeholder="Mật khẩu" required>
	                                    <div class="invalid-feedback text-left">Vui lòng nhập mật khẩu</div>
	                                </div>
	                                <%
										if(request.getParameter("error") != null){
											out.print("<p style='color:red'>Vui lòng nhập tên đăng nhập/số điện thoại khác</p>");
										}
									%>
	                                <div class="submit-btn">
	                                    <button type="submit" class="btn btn-primary">Đăng ký</button>
	                                </div>
                            	</form>
							</div>
						</div>
					</div>
				</div>
                <table class="table table-bordered" style="font-size: 13px;">
                    <thead>
                      <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Tên đăng nhập</th>
                        <th scope="col">Quyền</th>
                        <th scope="col">Họ tên</th>
                        <th scope="col">SĐT</th>
                        <th scope="col">Email</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="ad" items="${admins}">
                      	<tr>
                        	<th scope="row">${ad.getAccId()}</th>
	                        <td>${ad.getUserName()}</td>
	                        <td>${ad.getRole()}</td>
	                        <td>${ad.getName()}</td>
	                        <td>${ad.getPhone()}</td>
	                        <td>${ad.getEmail()}</td>
	                        <td class="text-warning">
                        			<c:if test="${ad.isActive()==true}">Đang hoạt động</c:if>
                        			<c:if test="${ad.isActive()==false}">Đang ẩn</c:if>
                        	</td>
	                        <td>${ad.getCreatedAt()}</td>
	                        <c:if test="${ad.getAccId()!= admin.getAccId()}">
								<c:if test="${ad.isActive()==true}"><td><a href="AdminAdmin?lock=${ad.getAccId()}&page=${pg}">Khóa</a></td></c:if>
                        		<c:if test="${ad.isActive()==false}"><td><a href="AdminAdmin?unlock=${ad.getAccId()}&page=${pg}">Mở khóa</a></td></c:if>
							</c:if>
	                        <td>
	                        	<c:if test="${ad.getAccId()!= admin.getAccId()}">
	                        		<a href="" data-toggle="modal" data-target="#delete${ad.getAccId()}">Xóa</a>							
								  	<div class="modal" id="delete${ad.getAccId()}">
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
								            <a href ="AdminDelete?admin=${ad.getAccId()}&pg=${pg}"  class="btn btn-primary">Xóa</a>
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