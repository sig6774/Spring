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
	<form action="<c:url value ='/score/selectOne'/>">
	<!-- /score/selectOne 로 값을 보냄  -->
	<p>
		# 조회할 학번 : <input type = "text" name="stuNum" size = "5">
		<input type="submit" value="확인">
	</p>
	</form>
	
	<!-- 조회할 학번이 없다면 보이는 정보 -->
	<h3 style = "color:blue;">
		${message }
	</h3>
</body>
</html>