<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${check > 0 }">
		<script type="text/javascript">
			alert("글쓰기를 성공하셨습니다.");
			location.href="${root}/fileBoard/list.do";
		</script>
	</c:if>
	
	<c:if test="${check == 0 }">
		<script type="text/javascript">
			alert("글쓰기 실패");
			location.href="${root}/fileBoard/write.do";
		</script>		
	</c:if>

</body>
</html>