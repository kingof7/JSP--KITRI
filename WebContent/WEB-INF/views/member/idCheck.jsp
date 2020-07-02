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
	<c:set var="root" value="${pageContext.request.contextPath }"/>
	<c:if test="${check == 1}">
		<div align = "center">
			이미 사용중 입니다.
			<form action = "${root}/member/idCheck.do" method="get">
				<input type="text" name="id">
				<input type="submit" value="확인"/>
			</form>
		</div>
	</c:if>
	
	<c:if test="${check == 0}">
		<div align = "center">
			사용가능한 아이디 입니다.
		</div>
		<script type="text/javascript">
			opener.createChkForm.id.value = "${id}"; //부모창을 의미
		</script>	
	</c:if>
	
	<div align="center">
		<a href="javascript:self.close()">닫기</a>
	</div>
	
</body>
</html>