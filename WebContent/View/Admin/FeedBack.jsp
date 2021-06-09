<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<title>Thông tin phản hồi</title>

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
                <h4>Danh sách các phản hồi</h4>
                <c:if test="${not empty maxPage}">
	                <div class="float-right">
	  					<ul class="pagination"><li>
	  					<c:forEach var = "i" begin = "1" end = "${maxPage}">
	    					<c:if test="${pg!=i}">
	    						<li class="page-item"><a class="page-link" href="AdminFeedBack?page=${i}">${i}</a></li><li>
	    					</c:if>
	    					<c:if test="${pg==i}">
	    						<li class="page-item active"><a class="page-link" href="AdminFeedBack?page=${i}">${i}</a></li>
	    					</c:if>
	    				</c:forEach>
	  					</li></ul>
					</div>
				</c:if>
                <table class="table table-bordered" style="font-size: 13px;">
                    <thead>
                      <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Tên người gửi</th>
                        <th scope="col">SĐT</th>
                        <th scope="col">Nội dung</th>
                        <th scope="col">Trạng thái</th>
                        <th scope="col">Thời gian</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="fb" items="${list}">
	                      <tr>
	                        <th scope="row">${fb.getFbId()}</th>
	                        <td>${fb.getName()}</td>
	                        <td>${fb.getPhone()}</td>
	                        <td>${fb.getContent()}</td>
	                        <c:if test="${fb.isSolve()==false}">
	                        	<td class="text-warning">Chưa xử lý</td>
	                        </c:if>
	                       	<c:if test="${fb.isSolve()==true}">
	                        	<td class="text-success">Đã xử lý</td>
	                        </c:if>
	                        <td>${fb.getCreatedAt()}</td>
	                        
	                        <td>
	                        	<c:if test="${fb.isSolve()==false}">
	                        	<a href="" data-toggle="modal" data-target="#solve${fb.getFbId()}">Xử lý</a>							
								<div class="modal" id="solve${fb.getFbId()}">
								    <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Bạn xác nhận đã xử lý phản hồi này?</h5>
								          </div>
					
								          <div class="modal-footer">
								            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
								            <a href ="AdminFeedBack?solve=${fb.getFbId()}&page=${pg}"  class="btn btn-primary">Xác nhận</a>
								          </div>
								        </div>
								    </div>
								</div>
								</c:if>
	                        </td>
	                        
	                        <td>
	                        	<a href="" data-toggle="modal" data-target="#delete${fb.getFbId()}">Xóa</a>							
								<div class="modal" id="delete${fb.getFbId()}">
								    <div class="modal-dialog modal-dialog-centered">
								      	<div class="modal-content">
								          <div class="modal-header">
								            <h5 class="modal-title">Bạn muốn xóa phản hồi này?</h5>
								          </div>
					
								          <div class="modal-footer">
								            <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
								            <a href ="AdminFeedBack?delete=${fb.getFbId()}&page=${pg}"  class="btn btn-primary">Xóa</a>
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