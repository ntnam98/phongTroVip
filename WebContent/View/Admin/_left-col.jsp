<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-3 left-col">
                <div class="row">
                    <div class="float-left avatar">
                        <img  src="Template/img/user.png">
                    </div>
                    <div class="float-left">
                        <b>${admin.getName()}</b>
                        <p>${admin.getPhone()}</p>
                    </div>
                </div>
                <div class="list-group">
                    <a class="list-group-item <c:if test="${menu==1}">font-weight-bold</c:if>" href="AdminAdmin"><i class="fa fa-address-card-o fa-fw"></i> Tài khoản Admin</a>
                    <a class="list-group-item <c:if test="${menu==2}">font-weight-bold</c:if>" href="AdminUser"><i class="fa fa-users fa-fw"></i> Tài khoản Người dùng</a>
                    <a class="list-group-item <c:if test="${menu==3}">font-weight-bold</c:if>" href="AdminPost"><i class="fa fa-book fa-fw"></i> Quản lí bài đăng</a>
                    <a class="list-group-item <c:if test="${menu==4}">font-weight-bold</c:if>" href="AdminInfo"><i class="fa fa-user-circle-o fa-fw"></i> Tài khoản cá nhân</a>
                    <a class="list-group-item <c:if test="${menu==5}">font-weight-bold</c:if>" href="AdminFeedBack"><i class="fa fa-comment-o fa-fw"></i> Thông tin phản hồi</a>
                    <a class="list-group-item <c:if test="${menu==6}">font-weight-bold</c:if>" href="AdminReport"><i class="fa fa-flag fa-fw"></i> Thông tin báo xấu</a>
                    <a class="list-group-item" href="LogOut?admin=1"><i class="fa fa-sign-out fa-fw"></i> Thoát</a>
                  </div>      
            </div>