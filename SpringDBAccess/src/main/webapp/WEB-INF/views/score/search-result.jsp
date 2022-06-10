<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>
	이름 : ${score.stuName }<br>
	국어 점수 : ${score.kor } <br>
	수학 점수 : ${score.math } <br>
	영어 점수 : ${score.eng } <br>
	종합 점수 : ${score.total } <br>
	<strong>평균 점수 : ${score.average }</strong>
	
</p>

</body>
</html>