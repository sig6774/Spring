<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>

<style type="text/css">
section {
	margin-top: 70px;
}

/*부트스트랩 li의 drophover클래스 호버시에 드롭다운 적용코드*/
ul.nav li.drophover:hover>ul.dropdown-menu {
	display: block;
	margin: 0;
}

.aside-inner {
	position: fixed;
	top: 70px;
	width: 180px;
}

.section-inner {
	border-right: 1px solid #ddd;
	border-left: 1px solid #ddd;
	background-color: white;
}

.section-inner p, .aside-inner p {
	margin: 0;
}

.title-inner {
	position: relative;
	padding: 15px 0;
}

.title-inner .profile {
	position: absolute; /*부모기준으로 위치지정 릴레이티브*/
	top: 15px;
	left: 0;
}

.title-inner .title {
	padding-left: 50px;
}
/*내용*/
.content-inner {
	padding: 10px 0;
}
/* 이미지영역  */
.image-inner img {
	width: 100%;
}
/*좋아요*/
.like-inner {
	padding: 10px 0;
	border-bottom: 1px solid #ddd;
	border-top: 1px solid #ddd;
	margin-top: 10px;
}

.like-inner img {
	width: 20px;
	height: 20px;
}

.link-inner {
	overflow: hidden;
	padding: 10px 0;
}

.link-inner a {
	float: left;
	width: 33.3333%;
	text-align: center;
	text-decoration: none;
	color: #333333;
}

.link-inner i {
	margin: 0 5px;
}

/*767미만 사이즈에서 해당 css가 적용됨*/
/*xs가 767사이즈   */
@media ( max-width :767px) {
	aside {
		display: none;
	}
}
/* 파일업로드 버튼 바꾸기 */
.filebox label {
	display: inline-block;
	padding: 6px 10px;
	color: #fff;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #5cb85c;
	cursor: pointer;
	border: 1px solid #4cae4c;
	border-radius: none;
	-webkit-transition: background-color 0.2s;
	transition: background-color 0.2s;
}

.filebox label:hover {
	background-color: #6ed36e;
}

.filebox label:active {
	background-color: #367c36;
}

.filebox input[type="file"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

/* sns파일 업로드시 미리보기  */
.fileDiv {
	height: 100px;
	width: 200px;
	display: none;
	margin-bottom: 10px;
}

.fileDiv img {
	width: 100%;
	height: 100%;
}
/* 모달창 조절 */
.modal-body {
	padding: 0px;
}

.modal-content>.row {
	margin: 0px;
}

.modal-body>.modal-img {
	padding: 0px;
}

.modal-body>.modal-con {
	padding: 15px;
}

.modal-inner {
	position: relative;
}

.modal-inner .profile {
	position: absolute; /*부모기준으로 위치지정 릴레이티브*/
	top: 0px;
	left: 0px;
}

.modal-inner .title {
	padding-left: 50px;
}

.modal-inner p {
	margin: 0px;
}
</style>

</head>
<body>
	<%@ include file="../include/header.jsp"%>
	<section>
		<div class="container">
			<div class="row">
				<aside class="col-sm-2">
					<div class="aside-inner">
						<div class="menu1">

							<c:choose>
								<c:when test="${login!= null }">
									<!-- 방문한 유저가 로그인 되어있다면  -->
									<!-- 비동기로 진행하면 좋을듯 -->
									<p>
										<img src="<c:url value='/img/profile.png'/>"> &nbsp;
										&nbsp; ${login.userName }님
									</p>
									<ul>
										<li>내정보</li>
										<li>내쿠폰</li>
										<li>내좋아요게시물</li>
									</ul>
								</c:when>

								<c:otherwise>
									<!-- 로그인 되어 있지 않은 사용자라면 로그인을 할 수 있도록 버튼 생성해서 페이지 이동  -->
									<button type="button" class="btn btn-info"
										onclick="location.href='<c:url value="/user/userLogin" />'">
										로그인</button>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="menu2">
							<p>둘러보기</p>
							<ul>
								<li>기부 캠페인</li>
								<li>페이지</li>
								<li>그룹</li>
								<li>이벤트</li>
								<li>친구리스트</li>
							</ul>
						</div>
					</div>
				</aside>
				<div class="col-xs-12 col-sm-8 section-inner">
					<h4>게시물 만들기</h4>
					<!-- 파일 업로드 폼입니다 -->
					<div class="fileDiv">
						<img id="fileImg" src="<c:url value='/img/img_ready.png'/>">
					</div>
					<!-- 사용자가 작성할 내용  -->
					<div class="reply-content">
						<textarea class="form-control" rows="3" name="content"
							id="content" placeholder="무슨 생각을 하고 계신가요?"></textarea>
						<div class="reply-group">
							<div class="filebox pull-left">
								<label for="file">이미지업로드</label> <input type="file" name="file"
									id="file">
							</div>
							<button type="button" class="right btn btn-info" id="uploadBtn">등록하기</button>
						</div>
					</div>
					<!-- 파일 업로드 폼 끝 -->

					<!-- 상세 보기 -->
					<div id="contentDiv">

						<!-- 비동기 방식으로 서버와 통신을 진행한 후 목록을 만들어서 붙일 영역  -->
					</div>
				</div>
				<!--우측 어사이드-->
				<aside class="col-sm-2">
					<div class="aside-inner">
						<div class="menu1">
							<p>목록</p>
							<ul>
								<li>목록1</li>
								<li>목록2</li>
								<li>목록3</li>
								<li>목록4</li>
								<li>목록5</li>
							</ul>
						</div>
					</div>
				</aside>
			</div>
		</div>
	</section>

	<%@ include file="../include/footer.jsp"%>
	<!-- 모달 -->
	<div class="modal fade" id="snsModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body row">
					<div class="modal-img col-sm-8 col-xs-6">
						<img src="../resources/img/img_ready.png" id="snsImg" width="100%">
					</div>
					<div class="modal-con col-sm-4 col-xs-6">
						<div class="modal-inner">
							<div class="profile">
								<img src="../resources/img/profile.png">
							</div>
							<div class="title">
								<p id="snsWriter">테스트</p>
								<small id="snsRegdate">21시간전</small>
							</div>
							<div class="content-inner">
								<p id="snsContent">삶이 우리를 끝없이 시험하기에 고어텍스는 한계를 테스트합니다</p>
							</div>
							<div class="link-inner">
								<a href="##"><i class="glyphicon glyphicon-thumbs-up"></i>좋아요</a>
								<a href="##"><i class="glyphicon glyphicon-comment"></i>댓글달기</a>
								<a href="##"><i class="glyphicon glyphicon-share-alt"></i>공유하기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<script>
	
	$(function(){
		// 등록하기 버튼 클릭 이벤트 
		$('#uploadBtn').click(function(){
			regist();
			// 등록 버튼을 클릭하면 regist() 실행  
		}); 
		
		// 등록 담당 함수 
		function regist(){
			// 세션에서 현재 로그인 중인 사용자 정보(아이디)를 얻어와야함 
			const user_id = '${sessionScope.login.userId}';
			// sessionScope를 통해서 login이라는 이름을 가진 세션의 userId 가져옴 
			
			// js 파일 확장자 체크 검색 
			let file = $('#file').val();
			// 아이디가 file이라는 곳의 value를 가져옴
			// console.log(user_id);
			// console.log(file);
			
			// .을 제거한 확장자만 얻어낸 후 그것을 소문자로 일괄 변경 
			file = file.slice(file.indexOf('.') + 1).toLowerCase();
			// .이 있는 곳의 인덱스 번호를 찾아서 +1하고 소문자로 변환 
			// 사용자가 업로드한 파일의 확장자만 추출할 수 있음
			// console.log(file);
			
			if (file !== 'jpg' && file !== 'png' && file !== 'jpeg' && file !== 'bmp'){
				alert("jpg, png, jpeg, bmp 확장자만 등록하실 수 있습니다.");
				$('#file').val('');
				// 사용자가 등록한 file의 value를 지움 
				return;
			}
			else if (user_id === ''){
				// 로그인을 안했다면 (세션 데이터가 존재하지 않음 )
				alert('로그인이 필요한 서비스 입니다.');
				return;
			}
			else{
				// 개발자가 원하는 파일 형식을 만족했으므로 비동기 방식으로 업로드 진행 
				// ajax 폼 전송의 핵심인 FormData 객체
				const formData = new FormData();
				const data = $('#file');
				
				console.log('폼 데이터 : ' + formData);
				console.log('data : ' + data );
				console.log(data[0]); 
				// 첫번째 데이터를 지정 (현재는 id로 지정했기 때문에 0번 인덱스만 존재)
				// <input type="file" name="file" id="file">이 출력 
				console.log(data[0].files);
				console.log(data[0].files[0]);
				// 하나의 파일 태그에 여러개의 파일이 들어가게 된다면 files[인덱스번호]를 통해서 각 파일을 가져올 수 있음
				// 사용자가 등록한 최종 파일 정보(현재는 한개의 파일만 등록했기 때문)
				
				/*
					data[index] -> 파일 업로드 버튼이 여러개 존재할 경우 요소의 인덱스를 지목해서 가져오는 법 
					요소를 id로 취득했기 때문에 하나만 찍히지만 ,class이름 같은 것으로 지정하면 여러개가 취득이 될 수 있음 
					data[index].files : 파일 태그에 담긴 파일 정보를 확인할 수 있는 키값.
					가져온 파일의 정보를 FormData객체에 넣어서 보내주기 위해 파일의 정보를 인덱스를 통해 가져옴 
				*/
				
				formData.append('file', data[0].files[0]);
				// 객체에 사용자가 업로드한 파일의 정보가 들어있는 객체 전달 
				// 만약 여러개의 파일을 등록했다면 파라미터 이름과 인덱스 번호를 다르게 해서 FormData객체에 저장
				
				// 글 내용 (content)
				const content = $('#content').val();
				formData.append('content', content);
				// 글 내용을 가져와서 FormData객체에 넣어줌 
				
				// 비동기 방식으로 파일 업로드 및 게시글 등록 
				$.ajax({
					url:'<c:url value="/snsBoard/upload" />',
					// 서버에 요청 
					type : 'post',
					data : formData,
					// 위에서 만든 폼 데이터 객체를 넘김 
					contentType:false,
					// ajax 방식에서 파일을 넘길 때 반드시 false로 처리 -> multipart/form-data로 처리가 됨  
					processData:false,
					// 폼 데이터를 &변수=값&변수=값... 형식으로 변경되는 것을 막음
					
					success: function(result){
						// 서버와 통신을 성공했다면 서버가 다시 주는 데이터  
						if (result === 'Success'){
							$('#file').val('');
							// 파일 선택지 비우기
							
							$('#content').val('');
							// 글 영역 비우기 
							
							$('.fileDiv').css('display','none'); 
							// 미리보기를 감추기
							getList(1, true);
							// 글 목록 함수 호출 
						}
						else{
							alert("업로드에 실패했습니다. 관리자에게 문의해주세요.");
						}
					},
					error : function(){
						// 서버와 통신을 실패했다면
						alert("업로드에 실패했습니다. 관리자에게 문의해주세요.");
						
					}
				});// end ajax
				
				
			} // end regist()
			
		}
			// 리스트 작업 
			let str ='';
			let page = 1;
			getList(1, true);
			
			function getList(page, reset){
				if (reset === true){
					// 화면 reset여부가 true라면 str 초기화
					str = '';
				}
				$.getJSON(
					'<c:url value="/snsBoard/getList?pageNum=' + page + '" />',
					function(boardList){
						// 서버가 전달해준 글목록
						console.log(boardList);
						
						for (let i=0; i<boardList.length; i++){
							str += 
							`<div class="title-inner">
								<!--제목영역-->
							<div class="profile">
									<img src="<c:url value='/img/profile.png'/>">
								</div>
								<div class="title">
									<p>` + boardList[i].writer + `</p>
									<small>` + timeStamp(boardList[i].regdate) + `</small> &nbsp; &nbsp;
									<a href=''>이미지 다운로드</a>
								</div>
							</div>
							
							<div class="content-inner">
								<!--내용영역-->
								<p>`+ (boardList[i].content === null ? '' : boardList[i].content) + `</p>
								<!-- 삼항 연산자로 content가 null일 때 대처방안 작성  -->
							</div>
							<div class="image-inner">
								<!-- 이미지영역 -->
								<a href="`+ boardList[i].bno + `">
								
								<img src="<c:url value='/snsBoard/display?fileLocation=` + boardList[i].fileloca + `&fileName=` + boardList[i].filename + `'/>">
								<!-- 요청 url을 작성해서 거기서 로컬에 있는 파일을 불러옴 
								 서버에 요청을 보내줌 -->
								</a>
							</div>
							<div class="like-inner">
								<!--좋아요-->
								<img src="../resources/img/icon.jpg"> <span>522</span>
							</div>
							<div class="link-inner">
								<a href="##"><i class="glyphicon glyphicon-thumbs-up"></i>좋아요</a>
								<a href="##"><i class="glyphicon glyphicon-comment"></i>댓글달기</a> 
								<a href="`+boardList[i].bno + `"><i class="glyphicon glyphicon-remove"></i>삭제하기</a>
							
							</div>`;
							$('#contentDiv').html(str);
							// 실제 dom에 위에서 작성한 내용을 넣음 
						} // end for 
					} 
				); // end getJSON
			} // end getList
			
			// 상세보기 처리 (모달창 열어줌)
			// 실제 이벤트가 발생하는 곳은 반복문에 의해 생성되는 곳임으로 이벤트가 먹히지 않을 수 있음 
			// 그렇기 때문에 실제 요소가 있는 곳에 이벤트를 걸어 이벤트 전파방식으로 진행 
			$('#contentDiv').on('click', '.image-inner a', function(event){
				// image-inner안의 a태그에 클릭이 발생하면 함수 발동 
				event.preventDefault();
				// a태그의 고유기능 중지
				
				// 글번호 얻어오기 
				const bno = $(this).attr('href');
				// 이벤트가 발생한 곳의 href 속성의 값을 가져옴 
				console.log(bno);
				
				$.getJSON(
					'<c:url value="/snsBoard/getDetail/" />' + bno,
					function(data){
						console.log(data);
						// 서버에서 준 데이터 
						
						// 미리 준비한 모달창에 뿌림  
						// 값을 위치에 뿌려주고 모달을 열어줌 
						$('#snsModal').modal('show');
						// 모달 열어줌 
						
						// console.log(data.writer);
						// 값 입력
						$('#snsWriter').html(data.writer);
						$('#snsRegdate').html(timeStamp(data.regdate));
						if (data.content !== null){
							$('#snsContent').text(data.content);							
						}
						else {
							$('#snsContent').text('');
						}
						const src ='<c:url value="/snsBoard/display?fileLocation=' + data.fileloca + '&fileName=' + data.filename + '"/>';
						// const src = "<c:url value='/snsBoard/display?fileLocation=` + data.fileloca + `&fileName=` + data.filename + `'/>";
						$('#snsImg').attr("src", src);

					}
				);
				
			}); // 상세보기 처리 끝
			
			//삭제 처리
		    //삭제하기 링크를 클릭했을 때 이벤트를 발생 시켜서
		    //비동기 방식으로 삭제를 진행해 주세요. (삭제 버튼은 한 화면에 여러개 겠죠?)
		    //서버쪽에서 권한을 확인 해 주세요. (작성자와 로그인 중인 사용자의 id를 비교해서)
		    //일치하지 않으면 문자열 "noAuth" 리턴, 성공하면 "Success" 리턴.
		    // url: /snsBoard/delete, method: post
   			$('#contentDiv').on('click', '.link-inner a', function(event){
   				// 이벤트가 발생한 곳을 지정해서 삭제 
				event.preventDefault();
   			 	const bno = $(this).attr('href');
   			 	// 글번호를 묻혀놓았으니 그것을 가져옴 
   			 	console.log(bno);

   			 	$.ajax({
  			 		type: 'post',
  			 		url : '<c:url value="/snsBoard/delete/" />' + bno,
  			 		data : bno,
  			 		dataType:'text',
  			 		contentType:'application/json',
  			 		success:function(result){
  			 			if (result === 'Success') {
  			 				alert("게시글이 정상적으로 삭제 완료되었습니다.");
  			 				getList(1, true);
  			 				// 삭제가 되었으니 값을 다시 불러옴
  			 			}
  			 			else if (result==='noAuth') {
  			 				alert("권한이 없습니다.");
  			 			}
  			 			else{
  			 				alert("파일 삭제에 실패했습니다.");
  			 			}
  			 		}, 
  			 		error:function(){
  			 			alert("삭제에 실패했습니다. 관리자에게 연락해주세요.");
  			 		}
  			 		}); // end ajax
   			}); // 삭제 처리 끝
   			
   			// 무한 스크롤
   			$(window).scroll(function() {
   				// device의 높이와 현재 스크롤 위치 값을 더한 뒤, 문서(content)의 높이와 같다면 
   				// 로직을 수행 
   				// 문서 높이 - 브라우저 창 높이 == 스크롤  창의 끝 높이와 같다면 -> 새로운 내용을 불러오자
   				
   				if (Math.round($(window).scrollTop()) === $(document).height() - $(window).height()){
   					// 스크롤이 끝까지 내려왔다면 
   					// 사용자의 스크롤이 바닥에 닿았을 때 페이지 변수의 값을 하나 올리고 reset여부는 false를 주어서 누적해서 계속 불러오면 됨 
   					// 게시글을 몇개씩 불러올지는 페이징 알로기즘에서 정해주면 됨
   					getList(++page, false);
   					// 값을 새롭게 불러줌 
   					// reset하면 안됨 (누적해서 불러와야 하기 때문)
   				}
   				
   				
   			}); // 무한 스크롤 끝 

	}); // end jQuery
	
	//날짜 처리 함수
    function timeStamp(millis) {
       const date = new Date(); //현재 날짜
       //현재 날짜를 밀리초로 변환 - 등록일 밀리초 -> 시간 차
       const gap = date.getTime() - millis;
       
       let time; //리턴할 시간
       if(gap < 60 * 60 * 24 * 1000) { //1일 미만일 경우
          if(gap < 60 * 60 * 1000) { //1시간 미만일 경우
             time = '방금 전';
          } else {
             time = parseInt(gap / (1000 * 60 * 60)) + '시간 전';
          }
       } else { //1일 이상일 경우
          const today = new Date(millis);
          const year = today.getFullYear(); //년
          const month = today.getMonth() + 1; //월
          const day = today.getDate(); //일
          
          time = year + '년 ' + month + '월 ' + day + '일';
       }
       
       return time;         
    }
	
	//자바 스크립트 파일 미리보기 보여주는 로직
	function readURL(input) {
       	if (input.files && input.files[0]) {
       		
           	var reader = new FileReader(); //비동기처리를 위한 파읽을 읽는 자바스크립트 객체
           	//readAsDataURL 메서드는 컨텐츠를 특정 Blob 이나 File에서 읽어 오는 역할 (MDN참조)
        	reader.readAsDataURL(input.files[0]); 
           	//파일업로드시 화면에 숨겨져있는 클래스fileDiv를 보이게한다
            $(".fileDiv").css("display", "block");
           	
           	reader.onload = function(event) { //읽기 동작이 성공적으로 완료 되었을 때 실행되는 익명함수
               	$('#fileImg').attr("src", event.target.result); 
               	console.log(event.target)//event.target은 이벤트로 선택된 요소를 의미
        	}
       	}
    }

	$("#file").change(function() {
		// id가 file인 것의 변화가 생기면 event가 발생해서 readURL함수가 실행 
	       readURL(this); //this는 #file자신 태그를 의미
	       
	   });
</script>



</body>
</html>