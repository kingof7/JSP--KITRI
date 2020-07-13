<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:set var="root" value="${ pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./../css/board/board.css">
<script type="text/javascript" src="${root}/javascript/board/board.js">
</script>
<script type="text/javascript">
		function fileDel(){
			fileUpdate.fileDelete.value = 1; // fileDelete 파라미터의 1값을 넣어줌			
			alert("삭제 되었습니다.");
			var header = document.querySelector(".test");	//제거하고자 하는 엘리먼트
			header.parentNode.removeChild(header);
		}
</script>
</head>
<body>
	<div id="power">

		<form action="${root}/fileBoard/updateOk.do" method="post" name="fileUpdate" onsubmit="return boardCheck(this)" enctype="multipart/form-data">
			<input type="hidden" name="boardNumber" value="${boardDto.boardNumber}" />
			<input type="hidden" name="pageNumber" value="${pageNumber}" />
			<!-- 파일 삭제 유무 확인 값 -->
			<input type="hidden" name="fileDelete" value="0" />
			
			<div>
				<label class="six">작성자</label>
				<p>${boardDto.writer}</p>
			</div>
			<div>
				<label class="six">제목</label> <input type="text" name="subject"
					value="${boardDto.subject}">
			</div>
			<div>
				<label class="six" style="height: 155px;">내용</label>
				<textarea name="content" rows="10" cols="50">${boardDto.content}</textarea>
			</div>
			<div>
				<label class="six">비밀번호</label> <input type="password"
					name="password" placeholder="수정할 비밀번호 입력">
			</div>
			<c:if test="${boardDto.fileSize != 0}">
				<div class="test">
					<label class="six" style="height: 24px;">파일</label>
					<fmt:formatNumber var="fileSize" value="${boardDto.fileSize / 1000}" pattern="###.#" />
					<span>${boardDto.fileName}(${fileSize}kb)</span>
					<button type="button" name="idChk" onclick="fileDel()">파일삭제</button>
				</div>
			</c:if>
			<div>
				<label class="six">파일수정</label> <input type="file" size="40"
					name="file" value="파일 수정">
			</div>
			<div class="bottom">
				<input type="submit" value="글수정">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" onclick="location.href='${root}/fileBoard/list.do?pageNumber=${pageNumber}'">
			</div>
		</form>
	</div>
</body>
</html>