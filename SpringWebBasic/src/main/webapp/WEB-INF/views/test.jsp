<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
body {
	background: wheat;
}
h3 {
	color : pink;
}
p {
	color : skyblue;
	font-size: 30px;
	font-weight: 700;
}
</style>
</head>
<body>
	<h2>이것도 될까?</h2>
	<h3>requestcontroller에서 시작된 응답의 과정이 여기까지 왔습니다~~~</h3>
	<p>메소드의 리턴을 문자열로 받아서 디스패처에 가고 디스패처는 viewresolver에게 값을 보내줘서 완벽한 url을
		만든 후 다시 디스패처에게 보냄. 이후 디스패처는 view에 해당 파일이 있는지 확인한 후 있으면 그대로 응답해줌</p>

</body>
</html>