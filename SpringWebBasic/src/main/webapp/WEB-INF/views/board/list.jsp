<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>글 전체 목록</h2>

	<table border="3">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>내용</th>
		</tr>
		<c:forEach var = "board" items="${listBoard }" varStatus="Num">
			<tr>
				<td>${Num.index + 1 }</td>
				<td>${board.writer }</td>
				<td><a href="<c:url value='/board/content?boardNo=${Num.index+1 }'/>">${board.title }</a></td>
				<!-- 제목을 클릭하면 상세보기로 이동하도록  -->
				<td>${board.content }</td>
			</tr>
		
		</c:forEach>
		
		

	</table>
		<a href="<c:url value='/board/write' />">게시글 추가 작성</a>
</body>
</html>