<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class= "header clearfix">
		<div class="container">
			<div class="row">
				<a href="Home" class="col-4">	
					<img src="Template/img/logo.png">
				</a>
				<div class="col-8">	
					<c:if test="${empty user}">
						<a href="Signup" class="button bt">Đăng ký</a>
						<a href="Login" class="button bt">Đăng nhập</a>
					</c:if>
					<c:if test="${not empty user}">
						<a href="LogOut" class="button bt" >Đăng xuất</a>
						<a href="NewPost" class="button bt" style="color: red;">Đăng tin mới <i class="fa fa-plus-circle" aria-hidden="true"></i></a>
						<a href="UserInfo" class="button bt" style="color: blue;">Quản lí tài khoản</a>
						<a style="float:right; margin-top:20px;">Xin chào, <b>${user.getName()}</b></a>
					</c:if>
				</div>
			</div>
		</div>
	</div>