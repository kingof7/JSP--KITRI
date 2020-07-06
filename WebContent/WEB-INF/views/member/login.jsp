<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${ pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root}/javascript/member/rutil.js"></script>
</head>
<body>
	<jsp:include page="../../../index.jsp"/>
	<div align="center">
	<form action="${root}/member/loginOk.do" method="post" 
				onsubmit="return idCheckFun(this)">
		<table>
			<tr>
				<td>회원 ID</td>
				<td>
					<input type="text" name="id"/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="password"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인"/>
					<input type="reset" value="취소"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>