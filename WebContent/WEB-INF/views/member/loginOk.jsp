<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${ pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${memberLevel != null}">
		<c:set var="id" value="${id}" scope="session"/>
		<c:set var="memberLevel" value="${memberLevel}" scope="session"/>
		
		<script type="text/javascript">
			alert("로그인 성공");
			location.href="${root}/member/main.do";
		</script>
	</c:if>
	<c:if test="${memberLevel == null}">
		<script type="text/javascript">
			alert("아이디 비밀번호를 확인하세요.");
			location.href="${root}/member/login.do";
		</script>
	</c:if>
</body>
</html>