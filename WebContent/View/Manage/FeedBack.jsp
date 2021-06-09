<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Liên hệ</title>

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
                <h4>Liên hệ với chúng tôi</h4>
                <div class="note">Chúng tôi biết bạn có rất nhiều sự lựa chọn. Nhưng cảm ơn vì đã lựa chọn PhongtroVIP</div><br>
                <a><b>Điện thoại:</b> 0969951716</a><br><br>
                <div><b>Mail:</b> cskh.phongtroVIP@gmail.com</div><br>
                <div><b>Zalo:</b> 0959961715</div><br>
                <div><b>Facebook:</b> https://www.facebook.com/phongtroVIP/</div><br>
                <div><b>Địa chỉ:</b> Số 23 Đường Giải phóng, P. Bách Khoa, Q. Hai Bà Trưng, Hà Nội</div><br>
                <h5><b>Liên hệ trực tuyến</b></h5>
                <a>Chúng tôi sẽ phản hồi liên hệ với bạn sớm nhất</a>
                <form action="" method="POST">
                	<div class="form-group">
                        <label for="name">Tên</label>
                        <input type="text" class="form-control" id="name" value="${user.getUserName()}">
                    </div>
                    <div class="form-group">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" class="form-control" id="phone" value="${user.getPhone()}">
                    </div>  
                    <div class="form-group">
                        <label for="phone">Nội dung</label>
                    	<textarea name="" id="" class="input-text form-control" required rows="5"></textarea>
                    </div>
                    <c:if test="${not empty rs}">
                    	<p class="text-success">Gửi phản hồi thành công</p>
                    </c:if>
                    <button type="submit" class="btn btn-primary">Gửi phản hồi</button>
                </form>
            </div>
        </div>
    </div>

</body>

</html>