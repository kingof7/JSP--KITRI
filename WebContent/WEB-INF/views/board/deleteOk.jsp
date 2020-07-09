<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath }" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${check > 0}">
		<script type="text/javascript">
			alert("삭제되었습니다.");
			location.href="${root}/board/list.do?pageNumber=${pageNumber}";			
		</script>
	</c:if>
	
	<c:if test="${check == 0}">
		<script type="text/javascript">
			alert("삭제되지 않았습니다.");
			location.href="${root}/board/list.do?pageNumber=${pageNumber}";			
		</script>
	</c:if>
</body>
</html>