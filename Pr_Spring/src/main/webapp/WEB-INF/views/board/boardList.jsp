<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>BBS Test</title>

<!--게시판만 적용되는 css-->
<style>
.table-striped>tbody>tr {
	background-color: rgba(255, 255, 255)
}

.row h2 {
	color: aliceblue;
}

.pagination-sm {
	margin: 0;
}
</style>
</head>
<body>
	
	<%@ include file="../includes/header.jsp" %>
	
	<section>

		<div class="container">
			<div class="row">

				<h2>게시판 목록</h2>
				<table class="table table-striped"
					style="text-align: center; border: 2px solid #737373">
					<thead>
						<tr>
							<th style="background-color: #9DCAFF; text-align: center;">번호</th>
							<th style="background-color: #9DCAFF; text-align: center;">제목</th>
							<th style="background-color: #9DCAFF; text-align: center;">작성자</th>
							<th style="background-color: #9DCAFF; text-align: center;">내용</th>
							<th style="background-color: #9DCAFF; text-align: center;">작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${bList}" var="b">	
							<tr>
								<%-- <td>${b }</td> --%>
								<!-- 변수는 bNum인데 get할 떄 b가 대문자 B가 됨  -->
								<td>${b.BNum}</td>
								<td><a>${b.BTitle}</a></td>
								<td>${b.BWriter}</td>
								<td>${b.BContent}</td>
								<td><fmt:formatDate value="${b.BDate }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

<!-- 				<div class="text-center">
					<ul class="pagination pagination-sm">
						<li><a href="#">이전</a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">다음</a></li>
					</ul>
					<button class="btn btn-info pull-right">글쓰기</button>
				</div> -->

			</div>
		</div>
	</section>
	
	<%@ include file="../includes/footer.jsp" %>
	
	
</body>
</html>