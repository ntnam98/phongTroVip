<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="nav-menu">
		<ul class="container nav nav-justified">
			<li class="nav-item <c:if test="${menu == 0}"> active</c:if>">
			  	<a class="nav-link" href="Home">Trang chủ</a>
			</li>
			<li class="nav-item <c:if test="${menu == 1}"> active</c:if>">
			  	<a class="nav-link" href="Search?menu=1">Cho thuê phòng trọ</a>
			</li>
			<li class="nav-item <c:if test="${menu == 2}"> active</c:if>">
			  	<a class="nav-link " href="Search?menu=2">Nhà cho thuê</a>
			</li>
			<li class="nav-item <c:if test="${menu == 3}"> active</c:if>">
			  	<a class="nav-link" href="Search?menu=3">Tìm người ở ghép</a>
			</li>
			<li class="nav-item <c:if test="${menu == 4}"> active</c:if>">
				<a class="nav-link" href="Search?menu=4">Cho thuê mặt bằng</a>
		  	</li>
		  	<li class="nav-item <c:if test="${menu == 5}"> active</c:if>">
				<a class="nav-link" href="Search?menu=5">Tôi cần thuê</a>
		  	</li>
			<li class="nav-item last <c:if test="${menu == 6}"> active</c:if>">
				<a class="nav-link" href="Instruction">Hướng dẫn</a>
			</li>
		  </ul>
	</div>