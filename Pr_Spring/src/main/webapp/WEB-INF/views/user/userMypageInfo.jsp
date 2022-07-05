<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body>


	<section>
		<%@ include file="../includes/header.jsp"%>

		<div class="container">
			<div class="row join-wrap">
				<!--join-form을 적용한다 float해제 margin:0 auto-->
				<div class="col-xs-12 col-md-9 join-form">

					<div class="titlebox">${user.userName }님의 정보</div>

					<p>*표시는 필수 입력 표시입니다</p>
					<table class="table">
						<tbody class="m-control">
							<tr>
								<td class="m-title">*ID</td>
								<td><input class="form-control input-sm" value="${user.userId }" name = "userId"></td>
							</tr>
							<tr>
								<td class="m-title">*이름</td>
								<td><input class="form-control input-sm" value="${user.userName }" name="userName"></td>
							</tr>
							<tr>
								<td class="m-title">*비밀번호</td>
								<td><input class="form-control input-sm" name="userPw"></td>
							</tr>
							<tr>
								<td class="m-title">*비밀번호확인</td>
								<td><input class="form-control input-sm"></td>
							</tr>
							<tr>
								<td class="m-title">*E-mail</td>
								<td><input class="form-control input-sm" value="${user.totalUserEmail }" name="totalUserEmail">
									<button class="btn btn-info">중복확인</button></td>
							</tr>
							<tr>
								<td class="m-title">*휴대폰</td>
								<td><input class="form-control input-sm sel" value="${user.totalUserTel }" name="totalUserTel"></td>
							</tr>
							<tr>
								<td class="m-title">*주소</td>
								<td><input class="form-control input-sm add" value="${user.totalUserAddr }" name="totalUserAddr"></td>
							</tr>
						</tbody>
					</table>

					<div class="titlefoot">
						<button class="btn">수정</button>
						<button class="btn">목록</button>
					</div>

				</div>


			</div>

		</div>

	</section>
	<%@ include file="../includes/footer.jsp"%>


</body>
</html>