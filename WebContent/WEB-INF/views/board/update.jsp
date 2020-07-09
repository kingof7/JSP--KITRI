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
</head>
<body>
<div id="power">
      <div class="title">
         <span><a href="#">글목록</a></span>
      </div>
      <form action="${root}/board/updateOk.do" method="post" onsubmit="return boardCheck(this)">
      
      	  <input type="hidden" name="boardNumber" value="${boardNumber}"/>
      	  <input type="hidden" name="pageNumber" value="${pageNumber}"/>
      	  
	      <div>
	         <label class="six">작성자</label>
	         <input type="text" name="writer" style="vertical-align:middle;" value="${boardDto.writer}">
	      </div>
	      <div>
	         <label class="six">제목</label>
	         <input type="text" name="subject" value="${boardDto.subject}">
	      </div>
	      <div>
	         <label class="six">이메일</label>
	         <input type="text" name="email" value="${boardDto.writer}">
	      </div>
	      <div>
	         <label class="six" style="height: 164px;">내용</label>
	         <textarea name="content" rows="10" cols="58">${boardDto.content}</textarea>
	      </div>
	      <div>
	         <label class="six">비밀번호</label>
	         <input type="password" name="password">
	      </div>
	      <div class="bottom">
	         <input type="submit" name="update" value="수정하기">
	         <input type="reset" value="다시작성">
	         <input type="button" name="list" value="목록보기" onclick="location.href='${root}/board/list.do'"/>
	      </div>
      </form>
   </div>
</body>

</html>