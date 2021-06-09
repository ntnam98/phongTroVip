<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Đổi mật khẩu</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
    <link rel="stylesheet" href="Template/css/font-awesome.min.css">
</head>

<body>	
    <jsp:include page="_user-menu.jsp"></jsp:include>
    
    <div class="user-body container-fluid">
        <div class="row">
            <jsp:include page="_left-col.jsp"></jsp:include>
            <div class="col-9 right-col">
                <h5>Đổi mật khẩu</h5>
                <form action="ChangePassword" method="POST">
                    <div class="form-group">
                      	<label for="name">Mật khẩu cũ</label>
                      	<input type="password" class="form-control" name="pw" id="pw">
                    </div>
                    <div class="form-group">
                        <label for="user">Mật khẩu mới</label>
                        <input type="password" class="form-control"  name="pw2" id="pw2">
                    </div>
                    <c:if test="${result == 1}">
						<a style='margin-top:-10px;color:green'>Đổi mật khẩu thành công</a>
					</c:if>
                    <c:if test="${result == 0}">
						<a style='margin-top:-10px;color:red'>Mật khẩu chưa chính xác</a>
					</c:if>
                    <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
                  </form>
            </div>
        </div>
    </div>

</body>

</html>