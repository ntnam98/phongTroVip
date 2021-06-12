<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div class="container select-form">    
        <form action="Search" method ="POST">
        	<div class="row">
	            <select class="col form-control" name="type" id="type" required>
	                <option value="1" <c:if test="${menu==1}">selected</c:if>>Phòng trọ, nhà trọ</option>
	                <option value="2" <c:if test="${menu==2}">selected</c:if>>Nhà cho thuê</option>
	                <option value="3" <c:if test="${menu==3}">selected</c:if>>Tìm người ở ghép</option>
	                <option value="4" <c:if test="${menu==4}">selected</c:if>>Thuê mặt bằng</option>
	                <option value="5" <c:if test="${menu==5}">selected</c:if>>Cần tìm thuê</option>
	            </select>
	            <select class="col form-control" name="city" id="city" required>
	                <option value="%">Chọn tỉnh thành</option>
	            </select>
	            <select class="col form-control" name="district" id="district" required>
	                <option value="%">Chọn quận huyện</option>
	            </select>
	            <select class="col form-control" name="ward" id="ward" required>
	                <option value="%">Chọn phường xã</option>
	            </select>
	            <select class="col form-control" name="price" id="price" required>
	                <option value="0">Chọn giá</option>
	                <option value="1">Dưới 1 triệu</option>
	                <option value="2">Từ 1 - 2 triệu</option>
	                <option value="3">Từ 2 - 5 triệu</option>
	                <option value="4">Trên 5 triệu</option>
	            </select>
	            <select class="col form-control" name="area" id="area" required>
	                <option value="0" selected>Chọn diện tích</option>
	                <option value="1">Dưới 20 m2</option>
	                <option value="2" >Từ 20-40 m2</option>
	                <option value="3">Từ 40-80 m2</option>
	                <option value="4">Trên 80 m2</option>
	            </select>
	            <div class="col">
	                <button type="submit" class="btn">Tìm kiếm</button>
	            </div>
	        </div>
        </form>
    </div>
    