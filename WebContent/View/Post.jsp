<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Bài đăng</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
	<link rel="stylesheet" href="Template/css/font-awesome.min.css">
</head>

<body class="home">	
	<jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
    <jsp:include page="_search-form.jsp"></jsp:include>

	<div class="container body">
		<div class="row">
			<div class="col-8 left-col" style="padding: 10px 20px;">
				<h4 style="color: red;">${post.getTitle()}</h4>
				<p><i class="fa fa-map-marker text-primary" aria-hidden="true"></i> ${post.getAddress()}, ${post.getWard()}, ${post.getDistrict()}, ${post.getCity()}</p>
				<b class="text-success"> <i class="fa fa-money"></i> ${post.getPrice()} Đồng/Tháng</b>
				<b class="text-warning"><i class="fa fa-th-large" style="margin-left: 50px;"></i> ${post.getArea() }m2</b>
				<b class="text-info" ><i class="fa fa-phone" style="margin-left: 50px;"></i> ${user.getPhone() }</b>
				<b style="margin-left: 50px;">Mã tin: #${post.getPostId()}</b>
				<div style="margin-top: 10px;"><i class="fa fa-clock-o"></i> ${post.getCreatedAt()} </div><br>
				<h5>Thông tin mô tả</h5>
				<p>${post.getContent()}</p><br>
			
				<h5>Hình ảnh</h5>
				<c:if test="${empty url}"><p>Không có hình ảnh của bài đăng này.</p></c:if>
				<c:if test="${not empty url}">
					<div id="myCarousel" class="carousel slide border" data-ride="carousel">
				        <ol class="carousel-indicators">
				        	<c:forEach var="img" items="${url}">
				        	<c:if test="${img == url[0]}">
			            		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				            </c:if>
				            <c:if test="${img != url[0]}">
			            		<li data-target="#myCarousel" data-slide-to="0"></li>
				            </c:if>
				            </c:forEach>
				        </ol>
			            <div class="carousel-inner">
			            	<c:forEach var="img" items="${url}">
			            		<c:if test="${img == url[0]}">
			            			<div class="carousel-item text-center active">
				                		<img src="${img}">
				                	</div>
				                </c:if>
				                <c:if test="${img != url[0]}">
			            			<div class="carousel-item text-center">
				                		<img src="${img}">
				                	</div>
				                </c:if>
			                </c:forEach>
			            </div>
			            <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
			              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			              <span class="sr-only">Previous</span>
			            </a>
			            <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
			              <span class="carousel-control-next-icon" aria-hidden="true"></span>
			              <span class="sr-only">Next</span>
			            </a>
			        </div>
				</c:if>
				<br><h5>Thông tin liên hệ</h5>
				<a><i class="fa fa-user"></i> Liên hệ:</a> <b class="text-info"> ${poster.getName() }</b><br>
				<a><i class="fa fa-phone"></i> Số điện thoại:</a> <b class="text-info">${poster.getPhone()}</b><br><br>
				<a href="" class="btt text-danger" data-toggle="modal" data-target="#report"><i class="fa fa-flag"></i> <b><i>Báo xấu</i></b></a>							
				<div class="modal" id="report">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Bài đăng này có vấn đề gì?</h5>
								<button type="button" class="close" style="padding: 1rem 0 1rem 1rem" data-dismiss="modal" aria-label="Close">
                    				<span aria-hidden="true">&times;</span>
                				</button>
							</div>
							<div class="modal-body">
								<form action="AdminReport?post=${post.getPostId()}" method="POST">   		
									<div class="form-check">
									  <label class="form-check-label">
									    <input type="radio" class="form-check-input" name="type" value="Trùng lặp" required>Trùng lặp
									  </label>
									</div>
									<div class="form-check">
									  <label class="form-check-label">
									    <input type="radio" class="form-check-input" name="type" value="Lừa đảo" required>Lừa đảo
									  </label>
									</div>
									<div class="form-check">
									  <label class="form-check-label">
									    <input type="radio" class="form-check-input" name="type" value="Đã cho thuê" required>Đã cho thuê
									  </label>
									</div>
									<div class="form-check">
									  <label class="form-check-label">
									    <input type="radio" class="form-check-input" name="type" value="Không liên lạc được" required>Không liên lạc được
									  </label>
									</div>
									<div class="form-check">
									  <label class="form-check-label">
									    <input type="radio" class="form-check-input" name="type" value="Thông tin không đúng thực tế" required>Thông tin không đúng thực tế
									  </label>
									</div>                   
							    	<label class="lb">Số điện thoại</label>
							    	<input type="text" name="phone" class="form-control" value="${user.getPhone()}">
							
							    	<label class="lb">Bạn hãy chia sẻ thêm</label>
							    	<textarea name="content" class="form-control" required rows="4"></textarea><br>
							    	<button type="submit" class="btn btn-primary" style="width:100%">Gửi</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<a href="StorePost?post=${post.getPostId()}&sp=1" class="btt"><i class="fa fa-star" aria-hidden="true"></i> <b><i>Lưu bài đăng</i></b></a>
			</div>
			<div class="col-4">
				<jsp:include page="_right-col.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="Template/js/bootstrap.min.js"></script>
	<script src="Template/js/axios.min.js"></script>
	<script src="Template/js/main.js"></script>
</body>

</html>