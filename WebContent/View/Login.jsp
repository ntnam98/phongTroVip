<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Đăng nhập</title>

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
                        	<h3>Đăng nhập vào tài khoản của bạn</h3>                  	
                        	<form action="<%=request.getContextPath() %>/Login" method="POST" class="needs-validation" novalidate>
                           		<div class="form-group">
                                	<input type="text" name="userName" class="input-text form-control" id = "user" placeholder="Tên đăng nhập" required>
        							<div class="invalid-feedback text-left">Vui lòng nhập tên đăng nhập</div>
                           		</div>
                            	<div class="form-group">
                                	<input type="password" name="password" class="input-text form-control" id = "pw" placeholder="Mật khẩu" required>
        							<div class="invalid-feedback text-left">Vui lòng nhập mật khẩu</div>
                            	</div>
                            	<c:if test="${error == 2}">
									<p style='margin-top:-10px;color:red'>Sai tên đăng nhập hoặc mật khẩu </p>
								</c:if>
                            	<c:if test="${error == 1}">
									<p style='margin-top:-10px;color:red'>Tài khoản của bạn đã bị khóa</p>
								</c:if>
                            	
                            	<div class="submit-btn">
                                	<button type="submit" class="btn">Đăng nhập</button>
                            	</div>
                        	</form>
                    	</div>
                    	<div class="footer">
                        	<span>Chưa có tài khoản? <a href="Signup">Đăng ký</a></span>
                    	</div>
                	</div>
            	</div>
        	</div>
		</div>
	</div>

</body>

</html>