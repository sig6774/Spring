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
	<!-- Model 객체에 저장한 내용을 화면에 뿌려줌 -->
	<h2>${boardnum }번 게시물 내용</h2>
	<p>
		# 작성자 : ${selectboard.writer }<br>
		# 제목 : ${selectboard.title }<br>
		# 내용 <textarea rows ="5" readonly>${selectboard.content }</textarea>
	</p>
	
	<a href ="<c:url value='/board/list' />">글 목록 보기</a>
	<!-- 링크를 보낼 때 board번호를 같이 보내줌 -->
	<a href ="<c:url value='/board/modify?boardNum=${boardnum }'/>">글 수정 하기</a>
	<a href ="<c:url value='/board/delete?boardNum=${boardnum }' />">글 삭제 하기</a>

</body>
</html>