<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>@ModelAttribute로 여러개의 값을 처리하기 </h2>
	ID : ${info.userId }<br>
	NAME : ${info.userName }<br>
	<!-- test2.jsp로 보내는 method에서 UserVO 객체를 info라는 이름으로 저장 -->
	<!-- el을 활용해서 값을 view에 표시 -->

</body>
</html>