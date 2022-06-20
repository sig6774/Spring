<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- el 태그르 사용하기 위해서 사용 -->

<%@ include file="../include/header.jsp"%>

<section>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-9 write-wrap">
				<div class="titlebox">
					<p>상세보기</p>
				</div>

				<form id ="form-obj">
					<div>
						<label>DATE</label>
						<c:if test="${board.updateDate == '' }">
							<p>${board.regDate }</p>
						</c:if>
						<!-- 글이 수정되지 않았으면  -->
						
						<c:if test="${board.updateDate != '' }">
							<p>${board.updateDate }</p>
						</c:if>
					</div>
					<div class="form-group">
						<label>번호</label> <input class="form-control" name='bno'
							value="${board.bno }" readonly>
					</div>
					<div class="form-group">
						<label>작성자</label> <input class="form-control" name='writer'
							value="${board.writer }" readonly>
					</div>
					<div class="form-group">
						<label>제목</label> <input class="form-control" name='title'
							value="${board.title }" readonly>
					</div>

					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="10" name='content' readonly>${board.content }</textarea>
					</div>
<%-- 					<input type="hidden" name="boardNo" value="${board.bno}">
 --%>					
					<button type="button" class="btn btn-primary" onclick="location.href = '<c:url value="/freeBoard/freeModify?bno=${board.bno}" />'">변경</button>
					<button type="button" class="btn btn-dark" onclick="location.href = '${pageContext.request.contextPath}/freeBoard/freeList?pageNum=${p.pageNum }&cpp=${p.cpp }&condition=${p.condition }&keyword=${p.keyword }'">목록</button>
																														
				</form>
			</div>
			
		</div>
	</div>
</section>

<section style="margin-top: 80px;">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-9 write-wrap">
				<form class="reply-wrap">
					<div class="reply-image">
						<img src="<c:url value='/img/profile.png' />">
					</div>
					<!--form-control은 부트스트랩의 클래스입니다-->
					<div class="reply-content">
						<!-- 댓글 내용 작성  -->
						<textarea class="form-control" rows="3" id="reply"></textarea>
						<div class="reply-group">
							<div class="reply-input">
								<input type="text" class="form-control" id = "replyId" placeholder="이름">
								<input type="password" class="form-control" id = "replyPw" placeholder="비밀번호">
							</div>

							<button type="button" id ="replyRegist" class="right btn btn-info">등록하기</button>
						</div>

					</div>
				</form>

				<!--여기에접근 반복-->
				<div id="replyList">
					<div class='reply-wrap'>
						<div class='reply-image'>
							<img src='../resources/img/profile.png'>
						</div>
						<div class='reply-content'>
							<div class='reply-group'>
								<strong class='left'>honggildong</strong> <small class='left'>2019/12/10</small>
								<a href='#' class='right'><span
									class='glyphicon glyphicon-pencil'></span>수정</a> <a href='#'
									class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
							</div>
							<p class='clearfix'>여기는 댓글영역</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- 모달 -->
<div class="modal fade" id="replyModal" role="dialog">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn btn-default pull-right"
					data-dismiss="modal">닫기</button>
				<h4 class="modal-title">댓글수정</h4>
			</div>
			<div class="modal-body">
				<!-- 수정폼 id값을 확인하세요-->
				<div class="reply-content">
					<textarea class="form-control" rows="4" id="modalReply"
						placeholder="내용입력"></textarea>
					<div class="reply-group">
						<div class="reply-input">
							<input type="hidden" id="modalRno"> <input
								type="password" class="form-control" placeholder="비밀번호"
								id="modalPw">
						</div>
						<button class="right btn btn-info" id="modalModBtn">수정하기</button>
						<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
					</div>
				</div>
				<!-- 수정폼끝 -->
			</div>
		</div>
	</div>
</div>


<%@ include file="../include/footer.jsp"%>

<script>
	const msg = '${msg}';
	if (msg !== ''){
		alert(msg);
	}
	
	$(document).ready(function() {
		$('#replyRegist').click(function() {
			/*
		         댓글을 등록하려면 게시글 번호도 보내 주셔야 합니다.
		         댓글 내용, 작성자, 댓글 비밀번호, 게시글 번호를 
		         json 표기 방법으로 하나로 모아서 전달해 주시면 됩니다.
		         비동기 통신으로 댓글 삽입을 처리해 주시고,
		         console.log를 통해 '댓글 등록 완료!'를 확인하시고
		         실제 DB에 댓글이 추가되는지도 확인해 주세요.
		         전송방식: POST, url: /reply/replyRegist
	         */
	         const content = $('#reply').val();
			 const name = $('#replyId').val();
			 const pw = $('#replyPw').val();
			 
			 if (content === '' || name === '' || pw === ''){
				 alert('필수 입력값(내용, 아이디, 비밀번호)의 내용이 비어있습니다. 확인해주세요.');
				 return;
				 // 이벤트 종료 
			 } else{
				 const bno = $'{board.bno}';
				 // 게시물 번호도 받아와야함 
				 // 어느 게시물의 댓글인지 알 수 있어야하기 때문
				 
				 const reply = {
					'bno' : bno,
					'reply' : content,
					'replyId' : name,
					'replyPw' : pw
				 };
				  // 객체로 만들어줌 
				 console.log(content);
				  $.ajax({
					  type : 'POST',
					  url:'<c:url value ="/reply/regist" />',
					  headers : {
						  'Content-Type' : 'application/json'
					  },
					  dateType : 'text', // 서버로 부터 어떤 형식으로 받을지
					  data:JSON.stringify(reply),
					 
					  success: function(result){
						  // 컨트롤러에서 다시 받은 값 
						  console.log('통신 성공' + result);
						  
						  alert('댓글 등록이 완료되었습니다.');
						  $('#reply').val('');
						  $('#replyId').val('');
						  $('#replyPw').val('');
						  // 등록 완료 후 댓글 목록 함수를 호출해서 비동기식으로 목록 표현
						  
						  //location.href="/freeBoard/freeDetail/" +bno + "/";
					  },
					  error : function(){
						  alert('댓글 등록 실패');
					  }
				  }); // end ajax
				 
			 }
		});
		
	}); // end jQuery
</script>


