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
				<form action="<c:url value='/user/userJoin' />" method="post" id = "formObj">
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
									class="btn btn-primary">이메일 인증</button>
							</div>
						</div>

						<div class="mail-check-box">
							<input type="text" class="form-control mail-check-input"
								placeholder="인증번호 6자리를 입력하세요." maxlength="6" disabled="disabled">
							<span id="mail-check-warn"></span>
							<!-- 인증번호 맞는지 틀린지 유무 표시하는 곳 -->
						</div>


					</div>
					<!--readonly 속성 추가시 자동으로 블락-->
					<div class="form-group">
						<label for="addr-num">주소</label>
						<div class="input-group">
							<input type="text" class="form-control" id="addrZipNum" name="addrZipNum"
								placeholder="우편번호" readonly>
							<div class="input-group-addon">
								<button type="button" class="btn btn-primary"
									onclick="searchAddress()">주소찾기</button>
									<!-- 주소찾기 버튼이 클릭이 되면 goPopup()함수 실행  -->
									<!-- 주소찾기 버튼이 클릭이 되면 searchAddress()함수 실행 -->
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addrBasic" name ="addrBasic"
							placeholder="기본주소">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addrDetail" name ="addrDetail"
							placeholder="상세주소">
					</div>

					<!--button탭에 들어가서 버튼종류를 확인한다-->
					<div class="form-group">
						<button type="button" id = "registBtn" class="btn btn-lg btn-success btn-block">회원가입</button>
					</div>

					<div class="form-group">
						<button type="button" id= "loginBtn" class="btn btn-lg btn-info btn-block">로그인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/footer.jsp"%>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
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

	// 중복 검사
	$(function() {

		let code = '';
		// 이메일 전송 인증번호 저장을 위한 변수 

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

		$('#mail-check-btn')
				.click(
						function() {
							const email = $('#userEmail1').val()
									+ $('#userEmail2').val();
							// 완성된 이메일 주소 
							console.log('완성된 이메일 : ' + email);
							$
									.ajax({
										type : 'get',
										url : '<c:url value="/user/mailCheck?email="/>'
												+ email,
										// 특정 경로로 서버에 요청을 보냄 

										success : function(result) {
											console.log('컨트롤러에서 전달받은 인증번호 : '
													+ result);

											$('.mail-check-input').attr(
													"disabled", false); // 비활성화된 인증번호 창 활성화
											// 속성 변경 

											// controller로 전달받은 값을 code에 넣어줌 
											// 나중에 인증번호 인증 여부 확인 값 
											code = result;

											alert('인증번호가 전송되었습니다. 확인 후 입력한에 정확하게 입력하세요.');
										},
										error : function() {

										}
									}); // end ajax(이메일 전송)

							// 인증 번호 비교 
							// blur -> focus가 벗어나는 경우 발생 
							$('.mail-check-input')
									.blur(
											function() {
												// 해당 입력창을 벗어나면 이벤트가 발생하게 만듬
												const inputCode = $(this).val();
												const $resultMsg = $('#mail-check-warn');

												if (inputCode === code) {
													// 사용자가 입력한 값과 위에서 저장한 인증번호가 같다면 
													$resultMsg
															.html('인증번호가 일치합니다.');
													$resultMsg.css('color',
															'green');

													$('#mail-check-btn').attr(
															'disabled', true);
													// 이메일 인증 버튼 비활성화(화면에 보이지 않도록)

													$('#userEmail1').attr(
															'readonly', true);
													// $('#userEmail2').attr('readonly', true);

													// select 태그라서 readonly 안먹음 
													$('#userEmail2')
															.attr('onFocus',
																	'this.initialSelect = this.selectedIndex');
													$('#userEmail2')
															.attr('onChange',
																	'this.selectedIndex = this.initialSelect');
													// 초기값을 사용자가 선택한 값으로 무조건 설정하는 방법(select에서 readonly 대용)
													// 2개 같이 작성해야 select 태그 변경 안됨
							
													
													// 인증번호 인증이 확인되면 해당 input 태그(인증버튼) 없앰
													$(this).css('display',
															'none');
													// 사용자가 더이상 변경하지 못하도록 

												} else {
													$resultMsg
															.html('인증번호를 다시 확인해 주세요.');
													$resultMsg.css('color',
															'red');
												}
											});
						});

		// 폼 데이터 검증 (회원가입 버튼 눌렀을 때)
		/*
			- 아이디 중복 체크 했는지 여부 (아이디 입력창이 readonly)
			- 비밀번호 확인란이 제대로 인식이 되었는지 여부 
			- 이름란이 공백이 아닌지 (이메일 인증, 주소는 필수값이 아니라서 확인 안해도 됨) 
			- 문제가 없다면 form 데이터 submit (비동기 X)
		*/
		
		$('#registBtn').click(function() {
			/* if($('#userId').attr('readonly') && document.getElementById("pwConfirm").value == document
					// readonly 속성의 값이 true, false이므로 
					.getElementById("userPw").value && $('#userName') !== ''){
				
				$('#formObj').submit();
				// 해당 form 데이터를 지정한 요소로 보냄 
				
			} */
			if (!$('#userId').attr('readonly')){
				alert('아이디 중복 체크는 필수 입니다.');
				return;
			}
			else if ($('#userPw').val === '' || $('#userPw').val() !== $('#pwConfirm').val()){
				alert('비밀번호 규칙을 확인하세요.');
				$('#userPw').focus();
				return;
			}
			else if ($('#userName').val === ''){
				alert('이름을 작성해주세요.');
				return;
			}
			else{
				// 해당 조건이 모두 아니라면 즉, 문제가 없다면 
				console.log($('#userId').attr('readonly'));
				console.log(document.getElementById("pwConfirm").value == document
						.getElementById("userPw").value);
				console.log($('#userName') !== '');
				
				$('#formObj').submit();
				// 해당 form 데이터를 지정한 요소로 보냄 
			}
		});

	}); // end jQuery
	
/* 	행정 안전부 주소 API

    function goPopup() {
		// 사용자가 주소 찾기 버튼을 누르면 절대경로로 팝업창을 오픈
		var pop = window.open("${pageContext.request.contextPath}/resources/popup/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
		// 
	}
		


	function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail,
			roadAddrPart2, engAddr, jibunAddr, zipNo, admCd, rnMgtSn,
			bdMgtSn, detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm,
			rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo) {
		// 콜백 방식으로 받아온 데이터를 가입 폼에 자동 완성 
		
		document.getElementById('addrBasic').value = roadAddrPart1;
		document.getElementById('addrDetail').value = addrDetail;
		document.getElementById('addrZipNum').value = zipNo;

	} */
	
	// 다음 주소 api 사용 

    function searchAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; 
                // 주소 변수
                var extraAddr = ''; 
                // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

/*                 // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                } 
                                안씀 그래서 주석 처리 
                */

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('addrZipNum').value = data.zonecode;
                document.getElementById('addrBasic').value = addr;
                // 커서를 상세주소 필드로 이동한다.
                
                // 내가 지정한 곳에 값을 넣어줌
                document.getElementById('addrDetail').focus();
            }
        }).open();
    }

</script>
