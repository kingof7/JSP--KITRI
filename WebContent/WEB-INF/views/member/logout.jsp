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
	<c:remove var="id" scope="session"/>
	<c:remove var="memberLevel" scope="session"/>
	<script type="text/javascript">
		alert("로그아웃 되었습니다.");
		location.href="${root}/member/login.do";
	</script>
</body>
</html>