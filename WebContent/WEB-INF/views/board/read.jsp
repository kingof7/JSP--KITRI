<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${root}/css/board/board.css" />
<script type="text/javascript">
	function replyFun(root, boardNumber, groupNumber, sequenceNumber, sequenceLevel){
		var url = root + "/board/write.do?boardNumber=" + boardNumber;
		url += "&groupNumber=" + groupNumber;
		url += "&sequenceNumber=" + sequenceNumber;
		url += "&sequenceLevel=" + sequenceLevel;
		
		//alert(url);
		
		location.href = url;
	}
	
	function upFun(root, boardNumber, pageNumber){
		var url = root + "/board/update.do?boardNumber=" + boardNumber;
		url += "&pageNumber=" + pageNumber;
		
		location.href = url;
	}
</script>
</head>
<body>

	<div id="power">

		<div>
	         <label class="six">글번호</label>
	         <label> ${boardDto.boardNumber}
         	</label>

	      </div>
	      <div>
	         <label class="six">작성자</label>
	         <label> ${boardDto.writer}
         	 </label>
	      </div>
	      <div>
	         <label class="six">조회수</label>
	         <label> ${boardDto.readCount}
         	 </label>
	      </div>
	      <div>
	         <label class="six" style="height: 164px;">제목</label>
	         <label> ${boardDto.subject}
         	 </label>
	      </div>
	      <div>
	         <label class="six">작성일</label>
	         <label>
	       		  <fmt:formatDate value="${boardDto.writeDate}" pattern="yyyy-MM-dd HH:mm:ss" />
         	 </label>
	      </div>
	      <div>
	         <label class="six">내용</label>
	         <label>	       		             
	         	${boardDto.content}
         	 </label>
	      </div>	    
		<div class="bottom">
			<input type="button" value="글수정" onclick="upFun('${root}','${boardDto.boardNumber}','${boardDto.pageNumber}')"/>
			<input type="button" value="글삭제" onclick=""/>
			<input type="button" value="답글" onclick="replyFun('${root}','${boardDto.boardNumber}','${boardDto.groupNumber}','${boardDto.sequenceNumber}','${boardDto.sequenceLevel}')"/>
			<input type="button" value="글목록" onclick=""/>
		</div>

	</div>
</body>
</html>