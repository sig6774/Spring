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
                            <p>수정하기</p>
                        </div>
                        
                        <form action="<c:url value='/freeBoard/freeModify' /> " method="post" name="modifyForm">
                        <!-- 서버에 /freeBoard/freeModify로 요청을 보냄 -->
                        
                            <div>
                                <label>DATE</label>
                                <p>${board.regDate }</p>
                            </div>   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name='bno' value = "${board.bno }" id ="boardId" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name='writer' value= "${board.writer }" id="boardWriter">
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name='title' value="${board.title }">
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content'>
                                	${board.content }
                                </textarea>
                            </div>

                            <button type="button" class="btn btn-dark" id="listBtn">목록</button>    
                            <button type="button" class="btn btn-primary" id="modifyBtn">변경</button>
                            <button type="button" class="btn btn-info" id = "deleteBtn">삭제</button>
                    </form>
                                    
                </div>
            </div>
        </div>
        </section>
        
        <%@ include file="../include/footer.jsp"%>
        
        <script>
        
        
        
        $(function() {
        // 목록 이동 이벤트 처리
	        $('#listBtn').click(function() {
	        	location.href="<c:url value='/freeBoard/freeList' />"
	        });
        
      		//수정 버튼 이벤트 처리
        	$('#modifyBtn').click(function() {
        		if ($('input[name=writer]').val() === '' || $('input[name=title]').val() === '' || $('textarea[name=content]').val() === ''){
        			// 수정하기 위해서는 writer과 title, name이 필수값이므로 검증 진행  
        			alert('수정하신 값 중 입력하지 않은 곳이 있습니다.')	
        			return;
        		}else{
        			alert("게시글 수정이 정상 처리되었습니다.");
        			document.modifyForm.submit();
        			// 다시 서버에 데이터와 함께 요청을 보냄 
        		}
        	});
        	
        	$('#deleteBtn').click(function() {
        		if (confirm('정말 삭제하시겠습니까?')){
        			// document.updateForm.setAttribute('action', '<c:url value="/freeBoard/freeDelete" />');
        			
        			$('form[name=modifyForm]').attr('action', '<c:url value="/freeBoard/freeModify?writer=${board.writer}" />');
        			// attr함수를 통해서 form 태그의 action을 바꿔줌
        			// attr함수를 통해 지정된 값을 바꿔줌 
        			document.modifyForm.submit();
        		}
        	});	
        });
        
        
        

         
         
        </script>
      