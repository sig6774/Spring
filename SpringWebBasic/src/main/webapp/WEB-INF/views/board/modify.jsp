<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Model 객체에 저장한 내용을 화면에 뿌려줌 -->
	<h2>${boardnum }번 게시물 내용 수정</h2>
	
	<form method="post">
	<!-- 수정한 값은 다시 마지막 요청 url로 보낼 예정  -->
		<input type="hidden" name="boardNo" value="${boardnum }">
		<p>
			# 작성자 : <input type="text" name="writer" value="${selectboard.writer }"><br>
			# 제목 : <input type="text" name="title" value="${selectboard.title }"><br>
			# 내용 : <textarea rows="3" name="content">${selectboard.content }</textarea> <br>
			<input type="submit" value="수정">
		</p>
	</form>

</body>
</html>