<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<c:set var="root" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${root}/css/board/board.css"/>
<script type="text/javascript" src="${root}/javascript/board/board.js"></script>
</head>
<body>

   <div id="power">
      <div class="title">
         <span><a href="#">글목록</a></span>
      </div>
      <form action="${root}/board/writeOk.do" method="post" onsubmit="return boardCheck(this)">
      
      	  <input type="hidden" name="boardNumber" value="${boardNumber }"/>
      	  <input type="hidden" name="groupNumber" value="${groupNumber }"/>
      	  <input type="hidden" name="sequenceNumber" value="${sequenceNumber }"/>
      	  <input type="hidden" name="sequenceLevel" value="${sequenceLevel }"/>
      	
	      <div>
	         <label class="six">작성자</label>
	         <input type="text" name="writer" style="vertical-align:middle;">
	      </div>
	      <div>
	         <label class="six">제목</label>
	         <input type="text" name="subject">
	      </div>
	      <div>
	         <label class="six">이메일</label>
	         <input type="text" name="email">
	      </div>
	      <div>
	         <label class="six" style="height: 164px;">내용</label>
	         <textarea name="content" rows="10" cols="58"></textarea>
	      </div>
	      <div>
	         <label class="six">비밀번호</label>
	         <input type="password" name="password">
	      </div>
	      <div class="bottom">
	         <input type="submit" name="write" value="글쓰기">
	         <input type="reset" value="다시작성">
	         <input type="button" name="list" value="목록보기">
	      </div>
      </form>
   </div>
</body>
</html>