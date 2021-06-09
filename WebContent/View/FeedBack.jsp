<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Gửi phản hồi</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
	<link rel="stylesheet" href="Template/css/font-awesome.min.css">
</head>

<body class="home">	
	<jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>

	<div class="container body">
		<div class="left-col" style="padding:20px;">
			<h4>Liên hệ với chúng tôi</h4>
                <div class="note">Chúng tôi biết bạn có rất nhiều sự lựa chọn. Nhưng cảm ơn vì đã lựa chọn PhongtroVIP</div><br>
                <a><b>Điện thoại:</b> 0969951716</a><br><br>
                <div><b>Mail:</b> cskh.phongtroVIP@gmail.com</div><br>
                <div><b>Zalo:</b> 0959961715</div><br>
                <div><b>Facebook:</b> https://www.facebook.com/phongtroVIP/</div><br>
                <div><b>Địa chỉ:</b> Số 23 Đường Giải phóng, P. Bách Khoa, Q. Hai Bà Trưng, Hà Nội</div><br>
			<h5><b>Liên hệ trực tuyến</b></h5>
                <a>Chúng tôi sẽ phản hồi liên hệ với bạn sớm nhất</a><br><br>
                <form action="FeedBack?fb=1" method="POST">
                	<div class="form-group">
                        <label for="name">Tên</label>
                        <input type="text" class="form-control" name="name" id="name" value="${user.getName()}">
                    </div>
                    <div class="form-group">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" class="form-control" name="phone" id="phone" value="${user.getPhone()}">
                    </div>  
                    <div class="form-group">
                        <label for="content">Nội dung</label>
                    	<textarea name="content" id="" class="input-text form-control" required rows="5"></textarea>
                    </div>
                    <c:if test="${not empty rs}">
                    	<p class="text-success">Gửi phản hồi thành công</p>
                    </c:if>
                    <button type="submit" class="btn btn-primary">Gửi phản hồi</button>
                </form>
		</div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>

</html>