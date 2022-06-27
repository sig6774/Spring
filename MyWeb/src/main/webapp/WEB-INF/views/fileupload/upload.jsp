<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 파일 업로드에서는 enctype(인코딩 타입)을 "multipart/form-data"로 반드시 지정  -->
	<form action="upload_ok" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" name="file"><br>
		<input type="submit" value="전송"> 
	</form>
	
	<hr>
	
	<!-- 복수 파일 전송 방법 1 -->
	<form action="upload_ok2" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" multiple = "multiple" name="files"><br>
		<!-- 다중 파일 선택  -->
		<input type="submit" value="전송"> 
	</form>
	
	<hr>
	
	<!-- 복수 파일 전송 방법 2 -->
	<form action="upload_ok" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" name="file"><br>
		파일 선택 : <input type="file" name="file"><br>
		파일 선택 : <input type="file" name="file"><br>
		<!-- 각 파일을 하나씩 받아서 Controller에 전송  -->
		<input type="submit" value="전송"> 
	</form>
	
	<hr>
	
	<form action="upload_ok4" method="post" enctype="multipart/form-data">
	
		원하시는 파일 명 : <input type="text" name="fileList[0].name"><br>
		파일 선택 : <input type="file" name = "fileList[0].file"><br>
		
		
		원하시는 파일 명 : <input type="text" name="fileList[1].name"><br>
		파일 선택 : <input type="file" name = "fileList[1].file"><br>
		
		원하시는 파일 명 : <input type="text" name="fileList[2].name"><br>
		파일 선택 : <input type="file" name = "fileList[2].file"><br>
		
		<input type="submit" value="전송"> 
		
	</form>
</body>
</html>