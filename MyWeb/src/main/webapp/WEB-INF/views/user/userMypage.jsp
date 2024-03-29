﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- el 태그르 사용하기 위해서 사용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/header.jsp"%>
	<section>
        <!--Toggleable / Dynamic Tabs긁어옴-->
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-10 col-lg-9 myInfo">
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    
                    <ul class="nav nav-tabs tabs-style">
                        <li class="active"><a data-toggle="tab" href="#info">내정보</a></li>
                        <li><a data-toggle="tab" href="#myBoard">내글</a></li>
                        <li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="info" class="tab-pane fade in active">
 
                            <p>*표시는 필수 입력 표시입니다</p>
                            <form action="<c:url value='/user/userUpdate'/>" method="post" id = "updateForm">
                            <table class="table">
                                <tbody class="m-control">
                                    <tr>
                                        <!-- name 속성은 커맨드 객체를 사용하기 위해 VO객체의 변수와 같도록 작성 
                                        	 value는 controller에서 보내준 값을 뿌려주는 속성  -->
                                        <td class="m-title">*ID</td>
                                        <td><input class="form-control input-sm" name = "userId" value ="${userInfo.userId }" id ="userId" readonly></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">*이름</td>
                                        <td><input class="form-control input-sm" name="userName" value="${userInfo.userName }" name="userName"></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">*비밀번호</td>
                                        <td><input type ="password" class="form-control input-sm" name = "userPw" id="userPw"></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">비밀번호확인</td>
                                        <td><input type ="password" class="form-control input-sm" id="userPwChk"></td>
                                    </tr> 
                                    <tr>
                                        <td class="m-title">E-mail</td>
                                        <td>
                                            <input class="form-control input-sm" name ="userEmail" value="${userInfo.userEmail }">@
                                            <select class="form-control input-sm sel" name="userEmail2">
                                                <option ${userInfo.userEmail2 == '@naver.com'? 'selected' : '' }>@naver.com</option>
                                                <option ${userInfo.userEmail2 == '@gmail.com'? 'selected' : '' }>@gmail.com</option>
                                                <option ${userInfo.userEmail2 == '@daum.com'? 'selected' : '' }>@daum.net</option>
                                                <!-- 사용자가 선택한 option을 작성하기 위해 삼항 연산자 사용  -->
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">휴대폰</td>
                                        <td>
                                            <select class="form-control input-sm sel" name="userPhone1">
                                                <option ${userInfo.userPhone1 == '010' ? 'selected' : '' }>010</option>
                                                <option ${userInfo.userPhone1 == '011' ? 'selected' : '' }>011</option>
                                                <option ${userInfo.userPhone1 == '017' ? 'selected' : '' }>017</option>
                                                <option ${userInfo.userPhone1 == '018' ? 'selected' : '' }>018</option>
                                                <!-- controller에서 뿌린 값이 특정 값과 같다면 selected로 지정  -->
                                            </select>
                                            <input class="form-control input-sm" name="userPhone2" value="${userInfo.userPhone2 }">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">우편번호</td>
                                        <td><input class="form-control input-sm" id = "addrZipNum" name="addrZipNum" value = "${userInfo.addrZipNum }" readonly>
                                        	<button type="button" class="btn btn-primary" id="addBtn">주소찾기</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">주소</td>
                                        <td><input class="form-control input-sm add" name ="addrBasic" id = "addrBasic" value="${userInfo.addrBasic }" readonly></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">상세주소</td>
                                        <td><input class="form-control input-sm add" name="addrDetail" id="addrDetail" value="${userInfo.addrDetail }"></td>
                                    </tr>
                                </tbody>
                            </table>
                            </form>

                            <div class="titlefoot">
                                <button class="btn" id = "updateBtn">수정</button>
                            </div>
                        </div>
                        <!-- 첫번째 토글 끝(내정보) -->
                        
                        <!-- 두번쨔 토글 시작-->
                        <div id="myBoard" class="tab-pane fade">
                            <p>내 게시글 관리</p>
                            <form>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>번호</td>
                                        <td>제목</td>
                                        <td>작성일</td>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="board" items="${userInfo.userBoardList }">
                                <!-- VO객체에 boardlist에 대한 변수도 추가했으므로 해당 변수를 통해 반복문을 진행하여 화면에 데이터를 뿌림 -->
                                    <tr>
                                        <td>${board.bno }</td>
                                        <td><a href="<c:url value='/freeBoard/freeDetail/${board.bno }' />">${board.title }</a></td>
                                        <!-- 클릭을 하게 되면 상세페이지로 이동하도록  -->
                                        <td><fmt:formatDate value="${board.regDate }" pattern="yyy년 MM월 DD일 HH:MM"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </form>
                        </div>
                        <!-- 두번째 토글 끝 -->
                        
                        
                        <div id="menu2" class="tab-pane fade">
                            <h3>Menu 2</h3>
                            <p>Some content in menu 2.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
<%@ include file="../include/footer.jsp"%>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>

	const msg = '${msg}';
	if (msg !== ''){
		alert(msg);
		
	}
	
	
	$(function() {

        
        // 비밀번호 검증 
        $('#updateBtn').click(function() {
            const regex = /^[A-Za-z0-9+]{8,16}$/;
			
            if ($('#userPw').val() === ''){
            	alert('비밀번호는 필수 입력입니다.');
            }
            else if(!regex.test($('#userPw').val())){
            	alert('비밀번호 형식과 맞지 않습니다.');
            }
            else if($('#userPw').val() !== $('#userPwChk').val()){
            	alert('비밀번호가 일치하지 않습니다. 확인란을 다시 작성해주세요.');
            	$('#userPwChk').focus();
            	return;
            }
            else if($('input[name=userName]').val() ===''){
            	alert('이름은 필수 입력입니다.');
            	$('input[name=userName]').focus();
            }
            else {
            	if (confirm('이대로 수정을 진행하시겠습니까? ')){
            		$('#updateForm').submit();
            	}
            	else {
            		return;
            	}
            }
        	
        }); // 데이터 검증 검증 끝
        
        // 다음 주소 api 
        $('#addBtn').click(function() {
            new daum.Postcode({
                oncomplete: function(data) {
                	console.log('클릭발생');
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

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('addrZipNum').value = data.zonecode;
                    document.getElementById('addrBasic').value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    
                    // 내가 지정한 곳에 값을 넣어줌
                    document.getElementById('addrDetail').focus();
                }
            }).open();
        })
        
        
        
        
        
        
	}); // end jQuery
	
</script>

