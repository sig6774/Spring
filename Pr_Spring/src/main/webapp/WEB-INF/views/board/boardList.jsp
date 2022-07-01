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
								<td><a href="<c:url value='/board/boardDetail/${b.BNum }'/>">${b.BTitle}</a></td>
								<!-- 제목을 클릭하게 된다면 해당 게시물의 번호도 같이 서버에 전송되도록 진행 -->
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
					-->
					<button class="btn btn-info pull-right" id="regiBtn">글쓰기</button>
					<!-- 글쓰기 클릭하게 된다면 요청 보내기  -->
				</div> 

			</div>
		<!-- </div> -->
	</section>
	
	<%@ include file="../includes/footer.jsp" %>
	<script>
	
		$(function() {
			const msg = '${msg}';
			if (msg !== ''){
				alert(msg);
				}
			
			$('#regiBtn').click(function() {
				console.log('글쓰기 버튼 클릭 ');
				if (confirm('게시글을 작성하시겠습니까?')){
					location.href='<c:url value="/board/boardRegist" />';
				}
				else {
					return;
				}
			});
		});
		
	</script>
	
	
</body>
</html>