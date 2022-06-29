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
				
				<!-- 서버에서 받은 데이터를 화면에 뿌려줌  -->
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
					<button type="button" class="btn btn-primary" onclick="location.href = '<c:url value="/freeBoard/freeModify?bno=${board.bno}&writer=${board.writer }" />'">변경</button>
					<!-- 변경을 하기 위해서는 게시물 번호와 작성자가 필요함으로 요청을 보낼 때 같이 보내줌 -->
					<button type="button" class="btn btn-dark" onclick="location.href = '${pageContext.request.contextPath}/freeBoard/freeList?pageNum=${p.pageNum }&cpp=${p.cpp }&condition=${p.condition }&keyword=${p.keyword }'">목록</button>
					<!-- 목록을 요청할 때 필요한 값이 있으므로 해당 값들을 요청을 보낼 때 같이 보내줌 -->
																														
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
				
				<!-- JS에서 반복문을 이용해서 댓글의 개수만큼 반복 표현
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
					</div> -->
				</div>
				
				<button class="form-control" id = "moreList">
					더보기(페이징)
				</button>
				
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
			// 등록하기 버튼을 누르게 된다면 

	         const content = $('#reply').val();
			 const name = $('#replyId').val();
			 const pw = $('#replyPw').val();
			 const bno = '${board.bno}';
			 // 게시물 번호도 받아와야함 
			 // 어느 게시물의 댓글인지 알 수 있어야하기 때문

			 
			 if (content === '' || name === '' || pw === ''){
				 alert('필수 입력값(내용, 아이디, 비밀번호)의 내용이 비어있습니다. 확인해주세요.');
				 return;
				 // 이벤트 종료 
			 } 
				 
		
				  // 객체로 만들어줌 
				 console.log(content);
				  $.ajax({
					  type : 'POST',
					  url:'<c:url value ="/reply/replyRegist" />',
					  // 서버에 요청을 보냄 
					  data : JSON.stringify({
						
							'bno' : bno,
							'reply' : content,
							'replyId' : name,
							'replyPw' : pw
						// 객체 형식의 JSON으로 만들어줌 
					  }),
					  dataType : 'text', // 서버로 부터 어떤 형식으로 받을지
					  contentType: 'application/json',

					  success: function(result){
						  // 통신에 성공했을 때 서버로부터 받음 
						  console.log('통신 성공' + result);
						  
						  alert('댓글 등록이 완료되었습니다.');
						  $('#reply').val('');
						  $('#replyId').val('');
						  $('#replyPw').val('');
						  // 등록이 완료되었다면 다시 등록할 수 있도록 값을 비워줌 
						  
						  // 등록 완료 후 댓글 목록 함수를 호출해서 비동기식으로 목록 표현
						  
						  getList(1, true);
						  
					  },
					  error : function(){
						  alert('댓글 등록 실패');
					  }
				  }); // end ajax
				 
			 
		}); // 댓글 등록 이벤트 끝
		
		// 더보기 버튼 클릭 처리 
		// 클릭 시 전역 변수인 page에 +1 한 값 전달 
		$('#moreList').click(function() {
			// 더보기 버튼 클릭 했다면
			
			getList(++page, false);
			// page값을 올려주고 reset을 false로 해줌 
			// 왜 false나면 더보기는 댓글을 더 보기 위해서는 누적으로 봐야함
			// 1페이지 댓글 내용에다가 추가로 페이지를 추가해서 댓글 내용을 보여줌
			
		});
		
		
		
		
		// 목록 요청 
		let page = 1; // 페이지 번호 
		let strAdd = ''; // 화면에 그려넣을 태그를 문자열 형태로 추가할 변수 
		
		getList(1, true);
		// 상세보기 화면에 처음 진입했을 때 댓글 리스트 불러옴  
		
		
		// 목록을 불러오는 list
		// getList의 매개값으로 요청된 페이지 번호와 화면을 리셋할 것인지 여부를 bool 타입의 reset이름의 변수로 받음
		// 페이지 이동은 없고 댓글은 밑에 계속 쌓이므로 상황에 따라 페이지를 리셋해서 새롭게 가져올지 누적해서 쌓을 것인지 여부 확인 
		function getList(pageNum, reset) {
			
			// 글번호를 알아야 댓글이 몇개인지 알 수 있음 
			const bno = '${board.bno}';
			
			// 비동기 방식에서 사용하는 함수 
			// getJSON()를 통해서 JSON형식의 파일을 읽어올 수 있음 
			// get방식의 요청을 통해 서버로부터 받은 JSON 데이터를 가져옴 
			// $.getJSON(요청보낼 url, 서버로 받은 데이터, 통신 성공 여부)
			$.getJSON(
					
				"<c:url value='/reply/getList/' />" + bno + '/' + pageNum,
				// 서버에 데이터와 함께 요청을 보냄 
				
				function(data){
					// 요청에 대한 응답의 데이터 
					// 서버가 전송한 데이터는 data에 저장
					
					// data에는 서버가 보낸 댓글들과 전체 댓글 개수가 담아져 있음 
					console.log(data);
					
					// data에는 replyList와 total이라는 이름의 데이터가 존재 
					let total = data.total;
					let replyList = data.replyList;
					console.log(replyList);
					
					// insert, update, delete 작업 후에는 댓글을 누적하고 있는 strAdd 변수를 
					// 초기화를 해서 화면이 리셋된 것처럼 보여줘야 함
					if (reset === true){
						strAdd = '';
						// 값을 초기화 
						page = 1;						
					}
					
					// 페이지 번호 * 데이터 수 보다 전체 댓글 개수가 작으면 더보기 버튼을 없앰 (굳이 보여줄 필요가 없음)
					console.log('현재 페이지 : ' + page);
					if (total <= page * 5){
						// 전체 댓글 개수가 페이지 * 한화면에 보여줄 댓글 수보다 작으면 굳이 더보기 화면을 보여줄 필요가 없음
						$('#moreList').css('display', 'none');
					} else{
						$('#moreList').css('display', 'block');
					}
					
					// 응답 데이터의 길이가 0과 같거나 더 작으면 함수를 종료 
					if(replyList.length <= 0){
						return;
						// 댓글이 없기 때문에 밑에 있는 반복문 종료 
					}
					
					// 댓글의 개수가 몇개인지 알 수 없으므로 반복문 진행
					// ``을 이용해서 쉽게 삽입 진행
					// controller에서 받은 데이터인 댓글이 들어있는 리스트 데이터 활용
					for (let i =0; i<replyList.length; i++){
						strAdd += 
						`<div class='reply-wrap'>
							<div class='reply-image'>
								<img src='${pageContext.request.contextPath}/img/profile.png'>
							</div>
							<div class='reply-content'>
								<div class='reply-group'>
									<strong class='left'>`+ replyList[i].replyId + `</strong> <small class='left'>` + timeStamp(replyList[i].replyDate) + `</small>
									<a href='` + replyList[i].rno + `' class='right replyDelete'><span class='glyphicon glyphicon-remove'></span>삭제</a>
									<a href='` + replyList[i].rno + `' class='right replyModify'><span class='glyphicon glyphicon-pencil'></span>수정</a> 
								</div>
								<p class='clearfix'>` + replyList[i].reply + `</p>
							</div>
						</div>`;
						// ``을 통해서 한번에 넣어줌
						// 댓글 영역의 html내용을 가지고 와서 반복문과 ``을 활용해서 필요한 곳에 값을 넣어줌
						// 반복문을 통해 서버에서 받아온 데이터를 화면에 뿌려줌 
					} // for 문 끝
					$('#replyList').html(strAdd);
					// 반복문에서 작성한 html을 품을 수 있는 노드를 찾아서 문자열 형식으로 넣어줌
					// 해당 글에 있는 모든 댓글 내용을 추가 
					
				}
				);// end getJSON
				
		}// end getList() 
		
		// 수정, 삭제 
		/*
		$('.replyModify').click(function(event) {
			event.preventDefault();
			// a태그의 기본 기능 삭제 
			console.log('수정 이벤트 발생');	
		});
		// 동작 안함
		
		ajax함수의 실행이 더 늦게 완료되기 때문에 실제 이벤트 선언이 먼저 실행됨 
		이런 상황에서는 화면에 댓글 관련 창은 아무것도 등록 되어 있지 않은 형태임으로 
		.click함수가 동작하지 않음
		이미 존재하는 #replyList에 이벤트를 등록하고 이벤트를 자식에게 전파시켜 사용하는 
		제이쿼리 이벤트 위임 함수를 사용 
		
		.replyModify는 html로 구현된게 아니고 getList함수가 실행되면 나타나는 것이므로 
		거의 마지막에 진행되기 때문에 이벤트가 발생한 시점에는 .replyModify요소가 없으므로 실행이 안되는 것
		그래서 이벤트는 실제 html로 구현된 곳에 걸어서 이벤트 전파방식으로 구현해야 됨
		
		*/
		
		$('#replyList').on('click', 'a', function(event){
			// id가 replyList인 요소의 a태그에 클릭이 발생하면 모두 event 생김
			event.preventDefault();
			// 태그의 고유 기능 중지 
			
			// console.log('이벤트 함수 동작');
			// 이벤트 전파로는 먹히네 
			
			
			// a태그가 두개 (수정, 삭제)이므로 버튼부터 확인 
			
			
			// 수정, 삭제가 발생하는 댓글 번호가 몇 번인지도 확인
			const rno = $(this).attr('href');			
			// 이벤트가 발생한 곳의 href라는 속성의 값을 가지고 옴
			
			$('#modalRno').val(rno);
			// 모달 내부의 숨겨진 input 태그에 댓글 번호를 담아서 전송
			
			// 모달 창 하나를 이용해서 상황에 따라 수정 / 삭제 모달을 구분하기 위해 조건문 작성
			// 수정과 삭제를 하나의 모달창으로 진행
			if($(event.target).hasClass('replyModify')){
				// replyModify라는 클래스 이름이 존재하는지(true, false)
				// 수정 버튼을 눌렀다는 뜻
				// 수정 모달 형식으로 진행 
				$('.modal-title').html('댓글 수정');
				$('#modalReply').css('display', 'inline');
				$('#modalModBtn').css('display', 'inline');
				
				$('#modalDelBtn').css('display', 'none');
				// 수정을 클릭했으면 삭제 버튼이 있어야 할 필요가 없음
				
				$('#replyModal').modal('show');
				// modal 창을 열기 위해서는 modal('show')를 사용
				// modal 창을 닫기 위해서는 modal('hide')를 사용 
				
			}
			else {
				// 삭제 버튼을 눌렀음으로 삭제 모달 형식으로 변경 
				$('.modal-title').html('댓글 삭제');
				$('#modalReply').css('display', 'none');
				$('#modalModBtn').css('display', 'none');
				// 수정 버튼은 삭제 작업에 필요없음으로 
				
				$('#modalDelBtn').css('display', 'inline');
				
				$('#replyModal').modal('show');
			}
		}); // 수정 or 삭제 버튼 클릭 이벤트 처리 끝
		
		// 수정 처리 함수 
		// 수정 모달을 열어서 주어 내용을 작성후 수정 내용 작성
		$('#modalModBtn').click(function() {
	         
	         // 수정에 필요한 값들을 모두 가져옴 
	         const rno = $('#modalRno').val();
			 // console.log('bno값 :' + bno);
			 const replyModi = $('#modalReply').val();
			 const replyPw = $('#modalPw').val();
			 // console.log(replyModi + replyPw);
			 // 수정을 하기 위한 값을 가져와서 저장 
			 
			 
			 if (replyModi === '' || replyPw === ''){
				 // 값들이 비어있다면 함수 종료 
				 alert('내용, 비밀번호를 확인하세요.');
				 return;
			 }
			 
			 // ajax 실행 
			 // 비동기방식으로 controller에 요청을 보내고 객체형식으로 값을 담아서 보냄 
			 $.ajax({
				 type:'POST',
				 url : '<c:url value ="/reply/update" />',
				 data : JSON.stringify({
					 'rno' : rno,
					 'reply' : replyModi,
					 'replyPw' : replyPw
					 // js의 객체 형식으로 JSON으로 바꿔서 서버에 전송 
				 }),
				 dataType:'text',
				 contentType : 'application/json',
				 
				 success:function(data){
					 // 통신이 성공해서 controller로부터 데이터를 받아왔을 때 실행 
					 console.log('통신성공' + data);
					 
					 if (data === 'modSuccess'){
						 alert('댓글 수정 성공 ');
						 // 값 초기화
						 
						 $('#modalReply').val('');
						 $('#modalPw').val('');		
						 // 수정이 완료되었으니 사용자가 작성한 값 비우기
						 
						 $('#replyModal').modal('hide');
						 // 모달 창 숨기기
						 
						 getList(1, true);
						 // 수정을 성공했으니 다시 새롭게 댓글 불러옴 
					 }
				
					 else{
						 
						 alert('비밀번호가 틀렸습니다.');
						 
						 $('#modalPw').val('');
						 // 비밀번호만 지움
						 
						 $('#modalPw').focus();
						 // 비밀번호 부분에 집중
					 }
				 },
				 error : function(){
					 // 통신에 실패했을 때 
					 alert("수정에 실패했습니다. 관리자에게 문의해주세요.");
				 }
			 }); // end ajax
			 
		}); // 수정 처리 이벤트 끄읏
		
		// 삭제 함수 
		$('#modalDelBtn').click(function() {
	         
	         // 삭제 처리를 하기 위해 값을 가져옴 
	         const rno = $('#modalRno').val();
			 // console.log('bno값 :' + bno);
			 const replyPw = $('#modalPw').val();
			 
			 if (replyPw === ''){
				 alert('비밀번호를 입력해주세요.');
				 return;
			 }
			 
			 // ajax함수를 활용해서 비동기 통신 방식으로 controller에 데이터 전달 
			 $.ajax({
				 type:'POST',
				 url : '<c:url value="/reply/delete" />',
				 data : JSON.stringify({
					 'rno' : rno,
					 'replyPw' : replyPw
					 // js의 객체형태로 JSON형식으로 서버에 데이터를 보냄 
				 }),
				 dataType:'text',
				 contentType : 'application/json',
				 
				 // 통신에 성공해서 controller에서 값을 다시 줬을 때 
				 success:function(result){
					 console.log('통신 성공' + result);
					 if (result === 'delSuccess'){
						 alert('댓글 삭제 성공');
						 
						 // 사용자가 작성한 값 비우기 
						 $('#modalReply').val('');
						 $('#modalPw').val('');
						 
						 // 모달 창 숨기기 
						 $('#replyModal').modal('hide');
						 
						 getList(1,true);
						 // 댓글창 다시 불러오기
					 }
					 else{
						 alert('비밀번호가 틀립니다.');
						 $('#modalPw').val('');
						 // 비밀번호 지움 
						 
						 $('#modalPw').focus();
						 // 비밀번호 부분 집중
					 }
				 },
				 // controller에 값을 받아오지 못했을 때 
				 error : function(){
					 alert('삭제에 실패했습니다. 관리자에게 문의해주세요.');
				 }
			 }); // end ajax
		}); // 삭제 처리 이벤트 끄읏 
		
		
		
		// 날짜 처리 함수 
		function timeStamp(millis) {
			
			const date = new Date();
			// 현재 날짜를 밀리초로 변환 - 등록일 밀리초 -> 시간 차 
			const gap = date.getTime() - millis;
		
			
			let time;
			if(gap < 60 * 60 * 24 * 1000){
				// 1일 미만일 경우 
				if(gap < 60 * 60 * 1000){
					// 1시간 미만일 경우
					time = '방금 전';
				}
				else{
					time = parseInt(gap / (1000 * 60 * 60 )) + '시간  ';
				}
			}
			else {
				// 1일 이상인 경우 
				const today = new Date(millis);
				// millis매개 변수를 Date객체에 넣어서 날짜로 변환
				
				const year = today.getFullYear(); 
				const month = today.getMonth() + 1;
				const day = today.getDate();
				time = year + '년' + month + '월' + day + '일';
			}
			return time;
		}
		
		
	}); // end jQuery
</script>


