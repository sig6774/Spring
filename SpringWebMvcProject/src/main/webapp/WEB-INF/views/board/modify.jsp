﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp" />
<style>
header.masthead {
	display: none;
}
</style>
<br />
<br />
<div class="container">

	<div class="row">
		<div class="col-lg-12">
			<div class="card">
				<div class="card-header text-white"
					style="background-color: #643691;">${article.boardNo }번게시물 수정</div>
				<div class="card-body">

					<%--         <form role="form" action="<c:url value ='/board/content?boardNo=${article.boardNo }"'/> method="post">
 --%>
					<form role="form" action="#" method="post">
						<!-- db수정은 되는데 content로 안감 -->
						<div class="form-group">
							<label>작성자</label> <input type="text" class="form-control"
								name='writer' value="${article.writer }" readonly>
						</div>

						<div class="form-group">
							<label>제목</label> <input type="text" class="form-control"
								name='title' value="${article.title }">
						</div>

						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="5" name='content'>${article.content }</textarea>
						</div>


						<!--           <input class="btn" type="submit" value="수정" style="background-color: orange; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"/>
          <a class="btn" href="/board/list"
		style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">목록</a>&nbsp;&nbsp;
           -->

						<input class="btn" type="submit" value="수정"
							style="background-color: orange; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8" />
						<a class="btn" href="#"
							style="background-color: #643691; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">목록</a>&nbsp;&nbsp;

					</form>
				</div>
			</div>
		</div>

	</div>
</div>
<jsp:include page="../include/footer.jsp" />