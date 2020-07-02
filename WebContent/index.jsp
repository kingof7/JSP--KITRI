<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  이 페이지의 컨텍스트(프로젝트) 이름을 root 변수에 담아준다. -->
	<c:set var="root" value="${ pageContext.request.contextPath }"/>
	<h3>${ root }</h3>
	
	<a href="${ root }/member/register.do">회원가입</a>
	
</body>
</html>