<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post">
	<!-- action이 없뜸... action을 안쓰면 요청이 안가는뎅 -->
	<!-- action을 안쓰면 해당 화면을 띄우기 위한 요청을 다시 보내줌 즉, 마지막 요청 url과 action url이 똑같을 때 가능 (method는 달라야함) -->
	<!-- action과 마지막 요청 url 같으면 action 안적어도 됨 -->
      <p>
         # ID: <input type="text" name="userId" size="10"> <br>
         # 비밀번호: <input type="password" name="userPw" size="10"> <br>
         # 비밀번호 확인: <input type="password" name="userPwChk" size="10"> <br>
         <input type="submit" value="로그인">
      </p>
   </form>
   
   
   <p style="color : blue;">
   		${msg }
   		<!-- 모델에 메세지를 담아서 redirect 진행했을 때 메시지 -->
   </p>
   
</body>
</html>