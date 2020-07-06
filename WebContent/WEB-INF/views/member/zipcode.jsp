<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${ pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${root}/javascript/member/register.js"></script>
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
						<input type="submit" name="검색"/>
					</td>
				</tr>
			</table>
		</div>
	</form>
	
	<div align="center">
		<table>
			<c:choose>
				<c:when test="${zipcodeList.size() == 0}">
					<tr>
						<td>검색된 결과가 없습니다.</td>					
					</tr>
				</c:when>
				
				<c:when test="${zipcodeList.size() > 0}">
					<tr>
						<td>아래 우편번호를 클릭하세요</td>					
					</tr>
					<c:forEach var="zipDto" items="${zipcodeList}">
						<tr>
							<td>
								<a href="javascript:sendAddress('${zipDto.zipcode}','${zipDto.sido}','${zipDto.gugun}','${zipDto.dong}','${zipDto.ri}','${zipDto.bunji}')" style="text-decoration: none;">
									${zipDto.zipcode}
									${zipDto.sido}
									${zipDto.gugun}
									${zipDto.dong}
									${zipDto.ri}
									${zipDto.bunji}
								</a>
							</td>						
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>	
		</table>
	</div>
		
</body>
</html>