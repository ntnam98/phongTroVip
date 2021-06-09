<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div class="col-3 left-col">
                <div class="row">
                    <div>
                        <img class="float-left avatar" src="Template/img/user.png"> 
                        <div class="float-left">
                        	<div><b>${user.getName()}</b></div>
                       		<div>${user.getPhone()}</div>
                        </div>
                    </div>                  
                </div>  
                <div class="submit-btn" style="margin:20px 0 20px 10px">
                    <a href="NewPost" class="bn">Đăng tin mới <i class="fa fa-plus" aria-hidden="true"></i></a>
                    <a href="ChangePassword" class="bn" style="margin-left:10px;">Đổi mật khẩu</a>
                </div>
                
                <div class="list-group">
                    <a class="list-group-item <c:if test="${item == 1}"> font-weight-bold</c:if>" href="ManagePost"><i class="fa fa-book fa-fw"></i> Quản lí bài đăng</a>
                    <a class="list-group-item <c:if test="${item == 2}"> font-weight-bold</c:if>" href="UserInfo"><i class="fa fa-user-circle-o fa-fw"></i> Thông tin cá nhân</a>
                    <a class="list-group-item <c:if test="${item == 3}"> font-weight-bold</c:if>" href="FeedBack"><i class="fa fa-comment-o fa-fw"></i> Liên hệ</a>
                    <a class="list-group-item <c:if test="${item == 4}"> font-weight-bold</c:if>" href="StorePost"><i class="fa fa-star fa-fw"></i> Các bài đăng đã lưu</a>
                    <a class="list-group-item" href="LogOut"><i class="fa fa-sign-out fa-fw" aria-hidden="true"></i> Thoát</a>
                  </div>      
            </div>