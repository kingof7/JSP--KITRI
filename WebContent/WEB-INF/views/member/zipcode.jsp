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
	<c:set var="root" value="${pageContext.request.contextPath}"/>
	
	<form action="${root}/member/zipcode.do" method="post">
		<div align="center">
			<table>
				<tr>
					<td>우편번호를 검색하세요.</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="dong"/>
						<input type="submit" value="검색"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>