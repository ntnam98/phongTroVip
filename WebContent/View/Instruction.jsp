<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>Hướng dẫn</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
	<link rel="stylesheet" href="Template/css/font-awesome.min.css">
</head>

<body class="home">	
	<jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>

	<div class="container body">
		<div class="left-col" style="padding:20px;">
			<h4>Hướng dẫn đăng tin</h4>
			<a>Chào bạn, sau đây là hướng dẫn sử dụng cho thành viên website phongtroVIP</a><br><br>
			<a>Nếu bạn chưa có tài khoản, hãy đăng ký <a href="Signup" class="text-danger"><b>TẠI ĐÂY</b></a> trước khi bắt đầu đăng tin mới.</a><br><br>
			<a>Nếu đã có tài khoản, sau khi đăng nhập vào website, bạn bấm vào nút <a href="NewPost" class="text-primary"><b>ĐĂNG TIN MỚI</b></a> để bắt đầu.</a><br><br>
			<a>Nếu bạn có thắc mắc hay phải hồi, hãy liên lạc với chúng tôi qua số điện thoại, email hoặc fax. Hoặc bạn có thể gửi 
			<a href="FeedBack?fb=1" class="text-success"><b>PHẢN HỒI</b></a> trực tiếp cho chúng tôi.</a><br><br>
			<a>Khi đăng tin các bạn đọc kỹ mô tả từng bước, nhập đầy đủ và chính xác nội dung cho tin đăng, đặc biệt là mục Giá 
				và Diện tích. Những tin có nội dung hình ảnh rõ ràng, đầy đủ sẽ có tỉ lệ xem cao hơn 50%.</a><br><br>
			<a>Lưu ý khi đăng tin:</a><br><br>
			<a>+ Điền đầy đủ các thông tin bắt buộc vào các ô nhập liệu trong phần đăng tin.</a><br><br>

			<a>+ Phần giá cho thuê, vui lòng nhập chính xác 1 giá duy nhất (Không nhập giá từ ....đến ....) và chọn đúng 
				đơn vị giá là triệu/tháng hoặc nghìn/tháng. Ví dụ bạn cho thuê 3 triệu/tháng thì bạn nhập đủ như sau 
				3000000 (1 số 3 và 6 số 0)</a><br><br>
			<a>+ Diện tích nhập đúng 1 diện tích duy nhất (Không nhập diện tích từ ....đến ....)</a><br><br>
			<a>+ Sau khi nhập đầy đủ các thông tin, bấm ĐĂNG TIN NGAY và chờ vài giây để tin bạn hiển thị trên website, 
				nếu đăng tin thành công hệ thống sẽ báo bạn đã đăng tin thành công, nếu hệ thống cảnh báo màu đỏ, các ô 
				chọn màu bị sai, vui lòng nhập lại cho chính xác và bấm ĐĂNG TIN NGAY lại.</a><br><br><br>
			<h4>Hướng dẫn quản lý tin rao</h4>
			<a>Đăng nhập tài khoản, sau đó nhấp vào phần Quản lý tài khoản, chọn Quản lý tin đăng để quản lý các tin đã đăng trên hệ thống.</a><br><br>
			<a>+ Sửa: có nghĩa là bạn có thể sửa lại tin bạn đã đăng như nội dung, tiêu đề, hoặc giá vvv...</a><br><br>
			<a>+ Đã cho thuê: chức năng này rất hay, khi bấm vào tin đăng của bạn sẽ ko còn hiện trên mục tìm kiếm, người khác sẽ không 
				thấy tin đăng của bạn, nhưng tin vẫn còn lưu trên website, khi nào bạn cần đăng lại thì có thể  nhấp vào để tin hiển thị lại.</a><br><br>
			<a>+ Xoá: có nghĩa là bạn sẽ xoá bỏ vĩnh viễn tin đăng của mình.</a><br><br>	
		</div>

	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>

</html>