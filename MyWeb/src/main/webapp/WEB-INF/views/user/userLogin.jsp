<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- el 태그르 사용하기 위해서 사용 -->

<%@ include file="../include/header.jsp"%>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7 col-xs-10 login-form">
                    <div class="titlebox">
                        로그인
                    </div>
                    <form id = "formObj" action="<c:url value='/user/userLogin' />" method="post">
                        <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">아이디</label>
                            <input type="text" class="form-control" id="id" name="id" placeholder="아이디">
                         </div>
                         <div class="form-group"><!--사용자클래스선언-->
                            <label for="pw">비밀번호</label>
                            <input type="password" class="form-control" id="pw" name ="pw" placeholder="비밀번호">
                         </div>
                         <div class="form-group">
                            <button type="button" id = "loginBtn" class="btn btn-info btn-block">로그인</button>
                            <button type="button" id = "registBtn" class="btn btn-primary btn-block">회원가입</button>
                         </div>
                         
                    </form>                
                </div>
            </div>
        </div>
    </section>
    
<%@ include file="../include/header.jsp"%>

<script>

	const msg = '$(msg)';
	if (msg === 'joinSuccess'){
		alert('회원 가입 정상 처리 되었습니다.');
	} else if(msg ==="loginFail"){
		alert('로그인 실패입니다.');
		
	}

	$(function(){
		$('#loginBtn').click(function() {
			if ($('#id').val() === '' || $('#pw').val() === ''){
				alert('아이디 혹은 비밀번호는 필수 값입니다.');
				return;
			}
			$('#formObj').submit();
			
		}); // 로그인 전송 끄읏~
		
		$('#registBtn').click(function() {
			location.href='<c:url value="/user/userJoin" />';
		});
	
	}); // end jquery
	
	

</script>

