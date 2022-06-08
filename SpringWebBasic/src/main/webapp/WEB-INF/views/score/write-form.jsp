<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>시험 점수 등록</h2>
	
	<form method = "post">
	<!-- form에 action을 작성하지 않으면 마지막 요청 url에 다시 값을 보냄 
		 즉, /score/register에 값을 보냄 -->
		 <p>
		 	# 이름 : <input type = "text" name = "stuName"><br>
		 	# 국어 : <input type = "text" name = "kor"><br>
		 	# 영어 : <input type = "text" name = "eng"><br>
		 	# 수학 : <input type = "text" name = "math"><br>
		 	<input type="submit" value = "확인">
		 </p>
		
	</form>
</body>
</html>