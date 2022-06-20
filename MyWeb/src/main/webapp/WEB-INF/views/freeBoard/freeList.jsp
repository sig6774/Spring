<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
    
    <%@ include file="../include/header.jsp" %>
    
    
    <section>
        <div class="container-fluid">
            <div class="row">
                <!--lg에서 9그리드, xs에서 전체그리드-->   
                <div class="col-lg-9 col-xs-12 board-table">
                    <div class="titlebox">
                        <p>자유게시판</p>
                    </div>
                    <hr>
                    
                    <!--form select를 가져온다 -->
            <form action="<c:url value='/freeBoard/freeList'/> ">
		    		<div class="search-wrap">
                       <button type="submit" class="btn btn-info search-btn">검색</button>
                       <input type="text" class="form-control search-input" name ="keyword" value="${pcv.paging.keyword }">
                       <select class="form-control search-select" name="condition">
                       <!-- 속성인 name은 객체의 변수명과 똑같이 작성하면 커맨드 객체 사용가능-->
                            <option value="title" ${pcv.paging.condition == 'title' ? 'selected' : ''}>제목</option>
                            <option value="content" ${pcv.paging.condition == 'content' ? 'selected' : ''}>내용</option>
                            <option value="writer" ${pcv.paging.condition == 'writer' ? 'selected' : ''}>작성자</option>
                            <option value="titleContent" ${pcv.paging.condition == 'titleContent' ? 'selected' : ''}>제목+내용</option>
                            <!-- codition을 고정하기 위해 3항 연산자 사용 -->
                            
                       </select>
                    </div>
		    </form>
                   
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th class="board-title">제목</th>
                                <th>작성자</th>
                                <th>등록일</th>
                                <th>수정일</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="vo" items="${boardList}">
	                            <tr>
	                                <td>${vo.bno}</td>
	                                <td>
	                                	<a href="<c:url value='/freeBoard/freeDetail/${vo.bno }/${pcv.makeURI(pcv.paging.pageNum)}' />">${vo.title}</a>
	                                	&nbsp;&nbsp;&nbsp;
	                                	<c:if test="${vo.newMark }">
											<img alt="newMark" src="<c:url value ='/img/newMark.gif' />">
	                                		<!-- servelt-con -->
                                		</c:if>
	                                </td>
	                                <td>${vo.writer}</td>
	                                <!-- fmt 태그를 활용해서 날짜 맵핑 진행  -->
	                                <td><fmt:formatDate value="${vo.regDate }" pattern="yyyy-MM-dd HH:mm" /></td>
	                                <td><fmt:formatDate value="${vo.updateDate }" pattern="yyyy-MM-dd HH:mm" /></td>
	                            </tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>


                    <!--페이지 네이션을 가져옴-->
		    <form action="<c:url value= '/freeBoard/freeList'/>" name="pageForm">
                    <div class="text-center">
                    <hr>
                    <ul id = "pagination" class="pagination pagination-sm">
	                    <c:if test="${pcv.prev }">
	                        <li>
	                        <a href="#" data-pagenum="${pcv.beginPage-1 }">이전</a>
		                        </li>				                    
						</c:if>
	                    
	                    <c:forEach var = "page" begin="${pcv.beginPage }" end = "${pcv.endPage }">
	                        <li  class="${pcv.paging.pageNum == page ? 'active' : '' }"><a href="#" data-pagenum="${page}">${page}</a></li>
	                        
	                        <!-- <li><a href="#">2</a></li>
	                        <li><a href="#">3</a></li>
	                        <li><a href="#">4</a></li>
	                        <li><a href="#">5</a></li> -->
	                        
	                    </c:forEach>
	                    
	                    <c:if test="${pcv.next}">
	                        <li><a href="#" data-pagenum="${pcv.endPage+1 }">다음</a></li>
	                    </c:if>
                    </ul>
                    <button type="button" class="btn btn-info" onclick="location.href='<c:url value="/freeBoard/freeRegist" />'">글쓰기</button>
                    </div>
                    
                    <!-- 페이지 관련 버튼(이전, 다음 , 페이지 번호) 클릭 시 같이 숨겨서 보내줄 공통 값 -->
                    <input type="hidden" name="pageNum" value="${pcv.paging.pageNum }">
                    <input type="hidden" name="cpp" value="${pcv.paging.cpp }">
                    <input type="hidden" name="condition" value="${pcv.paging.condition }">
                    <input type="hidden" name="keyword" value="${pcv.paging.keyword }">
		    </form>

                </div>
            </div>
        </div>
	</section>
	
	<%@ include file="../include/footer.jsp" %>
<script>
	$(function() {
		const msg = '${msg}';
		if (msg !== ''){
			alert(msg);
			}
		
		
	
		// ${'.search-btn'}.click(function() {
			/* const keyword = $('.search-input').val();
			const condition = $('.search-select').val();
			consoloe.log(keyword);
			consoloe.log(condition);
			location.href='${pageContext.request.contextPath}/freeBiard/freeList?keyword=' + keyword + '&condition=' + condition;	 */
			
			// 사용자가 페이지 관련 버튼을 클릭했을 때 기존에는 각각의 a태그의 href에다가 
			// 각각 다른 url을 작성해서 요청을 보내줬음
			// 이번에는 클릭한 버튼이 무엇인지 확인해서 해당 버튼에 맞는 페이지 정보를 JS로 끌고 와서 요청을 보내는 방법
			$('#pagination').on('click', 'a', function(event){
				// pagination이라는 아이디의 자식인 a태그에 클릭이 발생한다면 함수 실행
				event.preventDefault(); 
				// a태그의 고유 기능 중지 
				
				// 현재 이벤트가 발생한 요소(버튼)의 data-pageNum의 값을 얻어서 변수에 저장
				// const value = event.target.dataset.pageNum;
				const value = $(event.target).data('pagenum');
				console.log(value);
				
				// 페이지 버튼들을 감싸고 있는 form태그를 name으로 지목해서 
				// 그 안에 숨겨져 있는 pageNum이라는 input 태그의 value에 위에서 얻은 
				// data-pageNum의 값을 삽입한 후 submit
				document.pageForm.pageNum.value = value;
				document.pageForm.submit();
			});
		});

	
</script>


