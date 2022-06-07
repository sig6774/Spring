<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>test.jsp 페이지</h2>
	<p>
		Model 객체 테스트 <br> 
		지정된 별명은 <strong>${nick }</strong>입니다. 그리고 나이는 <strong>${age }</strong>세 입니다.
	</p>
	<!-- 모델 객체에 담은 값을 view인 jsp에서 el을 활용하여 값을 가져올 수 있음 -->
</body>
</html>