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
        /*
        1. 폼 데이터가 공백인지 확인 처리
        2. 공백이 없으면 Controller에 freeUpdate 요청으로 데이터를 전송
        3. 컨트롤러에서 처리가 완료된 후에 "게시글 수정이 정상 처리되었습니다."라는 알림창이
        	글 상세보기 페이지에서 처리될 수 있도록 해 주세요.
        */
        	$('#modifyBtn').click(function() {
        		if ($('input[name=writer]').val() === '' || $('input[name=title]').val() === '' || $('textarea[name=content]').val() === ''){
        			alert('수정하신 값 중 입력하지 않은 곳이 있습니다.')	
        			return;
        		}else{
        			alert("게시글 수정이 정상 처리되었습니다.");
        			document.modifyForm.submit();
        		}
        	});
        	
        	$('#deleteBtn').click(function() {
        		if (confirm('정말 삭제하시겠습니까?')){
        			// document.updateForm.setAttribute('action', '<c:url value="/freeBoard/freeDelete" />');
        			
        			$('form[name=modifyForm]').attr('action', '<c:url value="/freeBoard/freeModify?writer=${board.writer}" />');
        			// attr함수를 통해서 form 태그의 action을 바꿔줌
        			document.modifyForm.submit();
        		}
        	});	
        });
        
        
        
        //삭제 이벤트 처리
         /*
                  삭제같은 경우에는 번호가 노출되면 안되기 때문에 
         form태그를 이용해서 데이터를 전송시키세요. (post)
         action 속성을 delete에 맞게 바꿔서 전송하시면 됩니다.
         */
         
         
        </script>
      