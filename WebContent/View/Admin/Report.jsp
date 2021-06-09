<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Báo xấu bài đăng</title>

	<link type="text/css" rel="stylesheet" href="Template/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="Template/css/style.css" />
    <link rel="stylesheet" href="Template/css/font-awesome.min.css">
    <script src="Template/js/jquery-3.2.1.slim.min.js"></script>
  	<script src="Template/js/bootstrap.min.js"></script>
</head>

<body>	
    <div class="user-body container-fluid">
        <div class="row">
            <jsp:include page="_left-col.jsp"></jsp:include>
            <div class="col-9 right-col">
                <h4>Danh sách các báo xấu</h4>
                <c:if test="${not empty maxPage}">
	                <div class="float-right">
	  					<ul class="pagination"><li>
	  					<c:forEach var = "i" begin = "1" end = "${maxPage}">
	    					<c:if test="${pg!=i}">
	    						<li class="page-item"><a class="page-link" href="AdminReport?page=${i}">${i}</a></li><li>
	    					</c:if>
	    					<c:if test="${pg==i}">
	    						<li class="page-item active"><a class="page-link" href="AdminReport?page=${i}">${i}</a></li>
	    					</c:if>
	    				</c:forEach>
	  					</li></ul>
					</div>
				</c:if>
                <table class="table table-bordered" style="font-size: 13px;">
                    <thead>
                      <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Loại báo xấu</th>
                        <th scope="col">Mã bài đăng</th>
                        <th scope="col">Người đăng tin</th>
                        <th scope="col">Ảnh</th>
                        <th scope="col">SĐT báo xấu</th>
                        <th scope="col">Nội dung báo xấu</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Thời gian</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="rp" items="${list}">
	                      <tr>
	                        <th scope="row">${rp.getRpId()}</th>
	                        <td>${rp.getType()}</td>
	                        <td>${rp.getPostId()}</td>
	                        <td>Id: ${rp.getAccId()}<br>${rp.getName()}</td>
                        	<td><c:if test="${not empty rp.url}"><a href="PostInfo?postId=${rp.getPostId()}"><img src="${rp.getUrl()}" style="width:100px;height: 100px;"></a></c:if></td>
	                        <td>${rp.getPhone()}</td>
	                        <td>${rp.getContent()}</td>
	                        <c:if test="${rp.isSolve()==false}">
	                        	<td class="text-warning">Chưa xử lý</td>
	                        </c:if>
	                       	<c:if test="${rp.isSolve()==true}">
	                        	<td class="text-success">Đã xử lý</td>
	                        </c:if>
	                        <td>${rp.getCreatedAt()}</td>
	                        
	                        <td>
	                        	<c:if test="${rp.isSolve()==false}">
	                        	<a href="" data-toggle="modal" data-target="#solve${rp.getRpId()}">Xử lý</a>							
								<div class="modal" id="solve${rp.getRpId()}">
								    <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Bạn xác nhận đã xử lý báo xấu này?</h5>
								          </div>
					
								          <div class="modal-footer">
								            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
								            <a href ="AdminReport?solve=${rp.getRpId()}&page=${pg}"  class="btn btn-primary">Xác nhận</a>
								          </div>
								        </div>
								    </div>
								</div>
								</c:if>
	                        </td>
	                        
	                        <td>
	                        	<a href="" data-toggle="modal" data-target="#delete${rp.getRpId()}">Xóa</a>							
								<div class="modal" id="delete${rp.getRpId()}">
								    <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Bạn muốn xóa báo xấu này?</h5>
								          </div>
					
								          <div class="modal-footer">
								            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
								            <a href ="AdminReport?delete=${rp.getRpId()}&page=${pg}"  class="btn btn-primary">Xóa</a>
								          </div>
								        </div>
								    </div>
								</div>
	                        </td>
	                      </tr>
                      </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>

</html>