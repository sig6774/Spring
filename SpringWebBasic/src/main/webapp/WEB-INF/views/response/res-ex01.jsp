<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Model 객체</h2>
	<a href = "/basic/response/test?age=30">test1 페이지로</a>
	<!-- test로 요청하면서 값도 보내줌 -->
	<a href="/basic/response/test3">test3 페이지로</a>
	<a href="/basic/response/res-quiz01">퀴즈 페이지로</a>
	
	<hr>
	
	<form action="/basic/response/test2">
	<!-- 위의 경로로 값을 보내줌 -->
		ID : <input type = "text" name="userId"> <br>
		Name : <input type = "text" name="userName"> <br>
		<input type="submit" value="확인">
	</form>
</body>
</html>