<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>학생들의 전체 성적 조회</h2>
	<!-- varStatus : forEach 태그에서 반복문의 목록 수나 목록의 현재 index, count 등의 위치 값을 사용 -->
	<c:forEach var="stu" items="${sList }" varStatus="stuNum">
		<!--  -->
		<p>
			학번 : ${stuNum.index+1 }, 이름 : ${stu.stuName }, 국어 : ${stu.kor } 
			영어 : ${stu.eng }, 수학 : ${stu.math } <br>
			총점 : ${stu.total }, 평균 : ${stu.average } <br>
			<a href="<c:url value='/score/delete?stuNum=${stuNum.index+1 } '/>">삭제</a>
			<hr>
		</p>
	</c:forEach>
	
	<a href="<c:url value='/score/register' />">다른 점수 등록</a>
	
	<!-- 삭제 처리가 되고 redirect가 되면 표시해주는 것 -->
	<h3 style="color:blue;">
		${message }
	</h3>

</body>
</html>