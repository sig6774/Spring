<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- el 태그르 사용하기 위해서 사용 -->

<%@ include file="../include/header.jsp"%>
<section>
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-9 col-sm-12 join-form">
				<div class="titlebox">회원가입</div>
				<form action="<c:url value='/user/userJoin' />" method="post">
					<div class="form-group">
						<!--사용자클래스선언-->
						<label for="id">아이디</label>
						<div class="input-group">
							<!--input2탭의 input-addon을 가져온다 -->
							<input type="text" class="form-control" id="userId" name="userId"
								placeholder="아이디를 (영문포함 4~12자 이상)">
							<div class="input-group-addon">
								<button id="idCheckBtn" type="button" class="btn btn-primary">아이디중복체크</button>
							</div>
						</div>
						<span id="msgId">*필수 사항입니다.</span>
						<!--자바스크립트에서 추가-->
					</div>
					<div class="form-group">
						<!--기본 폼그룹을 가져온다-->
						<label for="password">비밀번호</label> <input type="password"
							class="form-control" id="userPw"
							placeholder="비밀번호 (영 대/소문자, 숫자 조합 8~16자 이상)" name="userPw">
						<span id="msgPw"></span>
						<!--자바스크립트에서 추가-->
					</div>
					<div class="form-group">
						<label for="password-confrim">비밀번호 확인</label> <input
							type="password" class="form-control" id="pwConfirm"
							placeholder="비밀번호를 확인해주세요."> <span id="msgPw-c"></span>
						<!--자바스크립트에서 추가-->
					</div>
					<div class="form-group">
						<label for="name">이름</label> <input type="text"
							class="form-control" id="userName" placeholder="이름을 입력하세요."
							name="userName">
					</div>
					<!--input2탭의 input-addon을 가져온다 -->
					<div class="form-group">
						<label for="hp">휴대폰번호</label>
						<div class="input-group">
							<select class="form-control phone1" id="userPhone1"
								name="userPhone1">
								<option>010</option>
								<option>011</option>
								<option>017</option>
								<option>018</option>
							</select> <input type="text" class="form-control phone2" id="userPhone2"
								name="userPhone2" placeholder="휴대폰번호를 입력하세요.">

						</div>
					</div>
					<div class="form-group email-form">
						<label for="email">이메일</label><br>
						<div class="input-group">
							<input type="text" class="form-control" id="userEmail1"
								placeholder="이메일" name="userEmail"> <select
								class="form-control" id="userEmail2" name="userEmail2">
								<option>@naver.com</option>
								<option>@daum.net</option>
								<option>@gmail.com</option>
								<option>@hanmail.com</option>
								<option>@yahoo.co.kr</option>
							</select>

							<div class="input-group-addon">
								<button type="button" id="mail-check-btn"
									class="btn btn-primary">본인인증</button>
							</div>
						</div>
					</div>
					<!--readonly 속성 추가시 자동으로 블락-->
					<div class="form-group">
						<label for="addr-num">주소</label>
						<div class="input-group">
							<input type="text" class="form-control" id="addrZipNum"
								placeholder="우편번호" readonly>
							<div class="input-group-addon">
								<button type="button" class="btn btn-primary">주소찾기</button>
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addrBasic"
							placeholder="기본주소">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addrDetail"
							placeholder="상세주소">
					</div>

					<!--button탭에 들어가서 버튼종류를 확인한다-->
					<div class="form-group">
						<button type="button" class="btn btn-lg btn-success btn-block">회원가입</button>
					</div>

					<div class="form-group">
						<button type="button" class="btn btn-lg btn-info btn-block">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp"%>


<script>
	// 중복 검사

	$(function() {
		$('#idCheckBtn').click(function() {

			console.log("중복 검사 이벤트 발생!");
			const id = $('#userId').val();
			// 비동기 방식으로 controller와 연동해서 중복 체크

			// id값이 비어있을 때 사용
			if (id === '') {
				alert('아이디는 필수값입니다.');
				return;
			}

			$.ajax({
				type : 'POST',
				url : '<c:url value ="/user/checkId" />',
				data : id,
				// 데이터가 하나일 때는 JSON.stringify() 안써도 됨

				dataType : 'text',
				contentType : 'application/json',

				success : function(result) {
					if (result === 'checkFail') {
						alert("중복된 아이디입니다. 다시 입력해주십시요.");
						$('#userId').val('');
						$('#userId').focus();
						// 다시 입력하도록 집중 
					} else {
						alert('사용가능한 아이디입니다. 회원가입을 진행할 수 있습니다.');
						$('#userId').attr('readonly', true);
						// 사용가능한 아이디면 더이상 입력할 수 없도록 readonly 추가
						$('#msgId').html('사용 가능한 아이디입니다.');
					}
				},
				error : function() {
					alert("오류입니다. 관리자에게 문의하세요");
				}

			}); // end ajax

		}); // 아이디 중복 체크 끄읏
		
		
		$('#mail-check-btn').click(function(){
			const email = $('#userEmail1').val() + $('#userEmail2').val();
			// 완성된 이메일 주소 
			console.log('완성된 이메일 : ' + email);
			$.ajax({
				type:'get',
				url:'<c:url value="/user/mailCheck?email="/>' + email,
				// 특정 경로로 서버에 요청을 보냄 
						
				success: function(result){
					
				}
				
				
			}); // end ajax(이메일 전송)
			
		});
		
	}); // end jQuery

	/*아이디 형식 검사 스크립트*/
	var id = document.getElementById("userId");
	// 이거 요소 다시 지정 

	id.onkeyup = function() {
		/*자바스크립트의 정규표현식 입니다*/
		/*test메서드를 통해 비교하며, 매칭되면 true, 아니면 false반*/
		var regex = /^[A-Za-z0-9+]{4,12}$/;
		if (regex.test(document.getElementById("userId").value)) {
			document.getElementById("userId").style.borderColor = "green";
			document.getElementById("msgId").innerHTML = "아이디중복체크는 필수 입니다";

		} else {
			document.getElementById("userId").style.borderColor = "red";
			document.getElementById("msgId").innerHTML = "";
		}
	} // 아이디 형식 검사 끄읏 

	/*비밀번호 형식 검사 스크립트*/
	var pw = document.getElementById("userPw");
	pw.onkeyup = function() {
		var regex = /^[A-Za-z0-9+]{8,16}$/;
		if (regex.test(document.getElementById("userPw").value)) {
			document.getElementById("userPw").style.borderColor = "green";
			document.getElementById("msgPw").innerHTML = "사용가능합니다";
		} else {
			document.getElementById("userPw").style.borderColor = "red";
			document.getElementById("msgPw").innerHTML = "";
		}
	}
	/*비밀번호 확인검사*/
	var pwConfirm = document.getElementById("pwConfirm");
	pwConfirm.onkeyup = function() {
		var regex = /^[A-Za-z0-9+]{8,16}$/;
		if (document.getElementById("pwConfirm").value == document
				.getElementById("userPw").value) {
			document.getElementById("pwConfirm").style.borderColor = "green";
			document.getElementById("msgPw-c").innerHTML = "비밀번호가 일치합니다";
		} else {
			document.getElementById("pwConfirm").style.borderColor = "red";
			document.getElementById("msgPw-c").innerHTML = "비밀번호 확인란을 확인하세요";
		}
	}
</script>
