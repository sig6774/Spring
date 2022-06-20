<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
    
    <%@ include file="../include/header.jsp" %>

    <section>
       <div class="container">
            <div class="row">
                <div class="col-xs-12 content-wrap">
                    <div class="titlebox">
                        <p>자유게시판</p>
                    </div>
                    
                    <form action="<c:url value='/freeBoard/freeRegist' />" method="post" name="registForm">
	                    <table class="table">
	                    <!-- input태그에 name이 없으면 커맨드객체로 가져올 수 없으므로 name 속성 지정  -->
	                        <tbody class="t-control">
	                            <tr>
	                                <td class="t-title">NAME</td>
	                                <td><input class="form-control input-sm" name ="writer" id = "newName"></td>
	                            </tr>
	                            <tr>
	                                <td class="t-title">TITLE</td>
	                                <td><input class="form-control input-sm" name="title" id= "newTitle"></td>
	                            </tr>
	                            <tr>
	                                <td class="t-title">CONTENT</td>
	                                <td>
	                                <textarea class="form-control" rows="7" name="content" id="newComment"></textarea>
	                                </td>                 
	                            </tr>
	                        </tbody>
	                    </table>
	                    </form>
	                 
	                    <div class="titlefoot">
	                        <button class="btn" id="registBtn" >등록</button>
	                        <button class="btn" id="listBtn" >목록</button>
	                    </div>
                    
                </div>
            </div>    
       </div>
    </section>
    
    <%@ include file="../include/footer.jsp" %>
    
   <script>
    
    $(function() {
    	$('#registBtn').click(function(){
    		
    		if ($('input[name=title]').val() === ''){
    			alert('제목은 필수 항목입니다.');
    			$('input[name=title]').focus();
    			return;
    		} else if($('textarea[name=content]').val() === ''){
    			alert('내용은 필수 항목입니다.');
    			$('textarea[name=content]').focus();
    			return;
    		} else{
    			document.registForm.submit();
    			// form 태그로 되어 있으므로 form의 id를 지목해서 submit하게 되면 데이터가 보내짐
    		}
    		
    	$('#listBtn').click(function() {
    		if(confirm('목록으로 돌아가시겠습니까?')){
    			location.href = '<c:url value="/freeBoard/freeList" />'; 
    		}else {
    			return;
    		}
    		});
    	
    	
    	
/*     		
    		const name = $('#newName').val();
    		const title = $('#newTitle').val();
    		const content = $('#newContent').val();
    		
    		const board = {
    				'name' : name,
    				'title' : title,
    				'content' : content
    		};
    		
    		$.ajax({
    			type:'POST',
    			url : '/freeBoard/freeRegist',
    			header : {
    				'Content-Type' : 'application/json'
    			},
    			
    			
    			dataType : 'text',
    			data : JSON.stringify(board),
    			
    			success :function(result){
    				alert('글 등록 완료');
    				location.href= '/freeBoard/freeList';
    			},
    			error : function(){
    				alert('글 등록 실패');
    			}
    		}); */

    });
});
    
    </script> 
    
  