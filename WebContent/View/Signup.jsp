<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>Đăng ký</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
	<link rel="stylesheet" href="Template/css/font-awesome.min.css">
	<script src="Template/js/jquery.min.js"></script>
	<script src="Template/js/main.js"></script>

</head>

<body>	
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
	<div id="backgroud" class="section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="form-content-box">
                        <div class="details">
                            <h3>Tạo tài khoản</h3>
                            <form action="Signup" method="POST" class="needs-validation" novalidate>
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
                                    <button type="submit" class="btn">Đăng ký</button>
                                </div>
                            </form>
                        </div>
                        <div class="footer">
                            <span>Đã có tài khoản? <a href="Login">Đăng nhập</a></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</body>

</html>