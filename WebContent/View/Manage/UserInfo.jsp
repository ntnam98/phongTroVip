<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>Thông tin cá nhân</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
	<link rel="stylesheet" href="Template/css/font-awesome.min.css"></head>

<body>	
    <jsp:include page="_user-menu.jsp"></jsp:include>
    <div class="user-body container-fluid">
        <div class="row">
            <jsp:include page="_left-col.jsp"></jsp:include>
            <div class="col-9 right-col">
                <h4> Cập nhật thông tin cá nhân</h4>
                <form action="UserInfo" method="POST">
                	<div class="form-group">
                      <label for="userId">Mã tài khoản</label>
                      <input type="text" readonly class="form-control font-weight-bold" id="userId" value="${user.getAccId()}">
                    </div>
                    <div class="form-group">
                        <label for="user">Tên đăng nhập</label>
                        <input type="text" readonly class="form-control" id="user" value="${user.getUserName()}">
                    </div>
                    <div class="form-group">
                      <label for="name">Họ tên</label>
                      <input type="text" class="form-control" id="name" name="name" value="${user.getName()}">
                    </div>
                    <div class="form-group">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" readonly class="form-control" id="phone" value="${user.getPhone()}">
                    </div>  
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${user.email}">
                    </div>
                    <div class="form-group">
                        <label for="createdAt">Thời gian tạo</label>
                        <input type="email" readonly class="form-control" id="createdAt" value="${user.getCreatedAt()}">
                    </div>
                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                 </form>
            </div>
        </div>
    </div>

</body>

</html>