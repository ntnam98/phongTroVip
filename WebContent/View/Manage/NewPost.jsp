<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>Đăng tin mới</title>

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
                <h4>Đăng tin mới</h4>
                <div class="note">
                    <a>Chúng tôi biết bạn có rất nhiều sự lựa chọn. Nhưng cảm ơn vì đã lựa chọn PhongtroVIP.</a>
                </div>
                <br><h5><b>Địa chỉ cho thuê</b></h5>
                <form action="NewPost" method="POST" enctype="multipart/form-data">   
                    <div class="row">
                        <select class="col form-control sl" name="city" id="city" required>
                            <option value="" selected>Chọn tỉnh thành</option>
                        </select>
                        <select class="col form-control sl" name="district"id="district" required>
                            <option value="" selected>Chọn quận huyện</option>
                        </select>
                        <select class="col form-control sl" name="ward" id="ward">
                            <option value="" selected>Chọn phường xã</option>
                        </select>
                    </div>
                    <label class="lb">Địa chỉ (số nhà, ngõ, tên đường)</label>
                    <input type="text" name="address" class="form-control" id = "address" required>
                    <br><h5><b>Thông tin mô tả</b></h5>
                    <label class="lb">Loại chuyên mục</label>
                    <select class="col form-control tn" name="category"	 id="category">
                        <option value="1" selected>Phòng trọ, nhà trọ</option>
                        <option value="2" >Nhà thuê nguyên căn</option>
                        <option value="3" >Tìm người ở ghép</option>
                        <option value="4" >Cho thuê mặt bằng</option>
                        <option value="5" >Tôi cần thuê</option>
                    </select>

                    <label class="lb">Tiêu đề</label>
                    <input type="text" name="title" class="form-control" id = "title" required>

                    <label class="lb">Thông tin mô tả</label>
                    <textarea name="content" id="content" class="form-control" required rows="6"></textarea>


                    <label class="lb">Giá cho thuê</label>
                    <div class="form-group tn">
                        <input type="number" name="price" class="form-control"  id = "price" required>
                        <span class="input-group-append">
                            <span class="input-group-text">Đồng/Tháng</span>
                        </span>
                    </div>

                    <label class="lb">Diện tích</label>
                    <div class="form-group tn">
                        <input type="number" name="area" class="form-control" id = "area" required>
                        <span class="input-group-append">
                            <span class="input-group-text">m2</span>
                        </span>
                    </div>
                    <br><h5><b>Hình ảnh</b></h5>          
                    <div>
                        <input type="file" name="image" accept='image/*' multiple/>
                    </div>
                    <button type="submit" class="btn btn-primary">Đăng tin ngay</button>
                </form>
            </div>
        </div>
    </div>
	<script src="Template/js/axios.min.js"></script>
	<script src="Template/js/main.js"></script>
</body>

</html>