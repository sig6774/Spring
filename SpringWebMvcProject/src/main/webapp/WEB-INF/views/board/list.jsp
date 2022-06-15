<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp" />
<style>
header.masthead {
	
	display: none;
}	
.btn-orange {
	background-color: orange;
	color: white;
}
.btn-cpp {
	background-color: #643691;
	color: white;
}
.page-active{
	background:"orange";
}
</style>

<br><br> 
 
    <!-- Begin Page Content -->
	

	<div class="container">
		<div class="row">
			<div class="col-lg-2">
			</div>
			<div class="col-lg-8">
				<div class="panel-body">
				<h2 class="page-header"><span style="color: #643691;">Spring</span> 자유 게시판
					<span id="count-per-page" style="float: right;">
	                     <input class="btn btn-cpp" type="button" value="10">  
	                     <input class="btn btn-cpp" type="button" value="20">   
	                     <input class="btn btn-cpp" type="button" value="30">
	                     <!-- 부모에 event를 걸어서 이벤트로 표시 -->
                     </span>
					
				</h2>
					<table class="table table-bordered table-hover">
						<thead>
							<tr style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
								<th>#번호</th>
								<th>작성자</th>
								<th>제목</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>

						<!-- 게시물이 들어갈 공간 -->
						<!-- Controller에서 return된 값(articles)을 받음 -->
						<c:forEach var="board" items="${articles }" >
							<tr style="color: #643691;">
								<td>${board.boardNo }</td>
								<td>${board.writer }</td>

								<td>
<%-- 									<a style="margin-top: 0; height: 40px; color: orange;" href="<c:url value='/board/content?boardNo=${board.boardNo}' />">
 --%>									
 									<a style="margin-top: 0; height: 40px; color: orange;" href="<c:url value='/board/content/${board.boardNo} ' />">
 																																			
 										${board.title }	
									</a>
									
									<c:if test="${board.newMark }">
										<!-- boardVO객체의 setnewmark가 true라면 (즉 하루가 지나지 않았으면 발동) -->
										<img alt="newMark" src="<c:url value ='/img/newMark.gif' />">
										<!-- 조건문이 true이면 해당 이미지 넣어줌 -->
									</c:if>
								</td>

								<td>
									<fmt:formatDate value="${b.regDate }" pattern="yyyy년 MM월 dd일 HH:mm" />
								</td>
								<td>${board.viewCnt }</td>
							</tr>
						
						</c:forEach>
						<!-- 반복문으로 값들을 화면에 뿌림 -->
					</table>
					
					<!-- 페이징 처리 부분  -->
					<ul class="pagination justify-content-center">
						
						<!-- 이전 버튼 -->
						<c:if test="${pc.prev }">
	                       	<li class="page-item">
								<a class="page-link" href="<c:url value='/board/list?page=${pc.makeURI(pc.beginPage-1) }'/>" 
								style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">이전</a>
							</li>
						</c:if>
						
						<!-- 페이지 버튼 -->	
						<c:forEach var = "page" begin="${pc.beginPage }" end="${pc.endPage }">
							<li class="page-item">
							   <a href="<c:url value='/board/list${pc.makeURI(page) }'/>" class="page-link ${pc.paging.page == page ? 'page-active' : '' }" style="margin-top: 0; height: 40px; color: pink; border: 1px solid #643691;">${page }</a>
							   <!-- 현재 페이지를 표시할 수 있는 style지정 -->
							   <!-- pageCreator에 있는 uri를 가져옴(uri지저분하게 안적어도됨) -->
							</li>
					   </c:forEach>
					   
					   <!-- 다음 버튼 -->
					   <c:if test="${pc.next }">
						    <li class="page-item">
						      <a class="page-link" href="<c:url value='/board/list${pc.makeURI(pc.endPage+1) }'/>" 
						      style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">다음</a>
						    </li>
					    </c:if>
				    </ul>
					<!-- 페이징 처리 끝 -->
					</div>
				</div>
			</div>
					<!-- 검색 버튼 -->
					<div class="row">
						<div class="col-sm-2"></div>
	                    <div class="form-group col-sm-2">
	                        <select id="condition" class="form-control" name="condition">                            	
	                            <option value="title ${param.condition == 'title'? 'selected' : '' }">제목</option>
	                            <option value="content ${param.condition == 'content'? 'selected' : '' }">내용</option>
	                            <option value="writer ${param.condition == 'writer'? 'selected' : '' }">작성자</option>
	                            <option value="titleContent ${param.condition == 'titleContent'? 'selected' : '' }">제목+내용</option>
	                        </select>
	                    </div>
	                    <div class="form-group col-sm-4">
	                        <div class="input-group">
	                            <input type="text" class="form-control" name="keyword" id="keywordInput" placeholder="검색어" value="${param.keyword }">
	                            <span class="input-group-btn">
	                                <input type="button" value="검색" class="btn btn-cpp btn-flat" id="searchBtn">                                       
	                            </span>
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
							<a href= "<c:url value='/board/write' />" class="btn btn-cpp float-right">글쓰기</a>
							<!-- /board/write 로 controller에 요청 -->
						</div>
						<div class="col-sm-2"></div>
					</div>	
	</div>
	

	
	
	
<jsp:include page="../include/footer.jsp" />

	<script>
		const msg = '${msg}';
		if (msg === 'deleteSuccess'){
			alert('삭제가 완료되었습니다.');
		} else if (msg==='regSuccess'){
			alert('등록이 완료되었습니다.');
		}
		
		
		//start jQuery
		$(function() {
			// 한 페이지당 보여줄 게시물 개수가 변동하는 이벤트 처리
			$('#count-per-page .btn-cpp').on('click', 'input', function(){
				const count = $(this).val();
				/* 현재 이벤트가 발생한 요소의 value */
				location.href='/board/list?page=1&cpp=' + count;
			});
			
			// 검색 버튼 이벤트 처리 
			$('#searchBtn').click(function() {
				const keyword = $('#keywordInput').val();
				// 사용자가 검색하기 위해 입력한 값을 가져옴 
				const condition = $('#condition').val();
				location.href="/board/list?keyword=" + keyword + "&condition=" + condition;
				// get방식으로 전송 list에 전송
			});
			
			// 검색창에서 엔터키 입력 시 이벤트 처리
			$('#keywordInput').keydown(function(event) {
				if(event.keyCode===13){
					// keyCode13은 enter를 의미
					$('#searchBtn').click();
				}
			});
		});
	</script>

















