<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>Thông tin cá nhân</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
    <link rel="stylesheet" href="Template/css/font-awesome.min.css">
</head>

<body>	
    <div class="user-body container-fluid">
        <div class="row">
            <jsp:include page="_left-col.jsp"></jsp:include>
            <div class="col-9 right-col">
                <h4> Cập nhật thông tin cá nhân</h4>
                <form action="AdminInfo" method="POST">
                	<div class="form-group">
                      <label>Mã tài khoản</label>
                      <input type="text" readonly class="form-control" id="userId" value="${admin.getAccId()}">
                    </div>
                    <div class="form-group">
                        <label>Tên đăng nhập</label>
                        <input type="text" readonly class="form-control" id="user" value="${admin.getUserName()}">
                    </div>
                    <div class="form-group">
                      <label>Họ tên</label>
                      <input type="text" class="form-control" id="name" name="name" value="${admin.getName()}">
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" readonly class="form-control" id="phone" value="${admin.getPhone()}">
                    </div>  
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="${admin.getEmail()}">
                    </div>
                    <div class="form-group">
                        <label>Thời gian tạo</label>
                        <input type="email" readonly class="form-control" id="createdAt" value="${admin.getCreatedAt()}">
                    </div>
                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                 </form>
                 <a href="ChangePassword?type=1" type="button" style="color:white;" class="btn btn-primary">Đổi mật khẩu</a>
            </div>
        </div>
    </div>

</body>

</html>