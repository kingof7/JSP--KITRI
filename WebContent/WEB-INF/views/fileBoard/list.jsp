<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table>
			<tr>
				<td width="530">
					<a href="${root}/fileBoard/write.do">글쓰기</a>
				</td>
			</tr>
		</table>
	</div>
	
	<c:if test="${count == 0 || boardList.size() == 0 }">
		<div align="center">
			게시판에 저장 된 글이 없습니다.
		</div>
	</c:if>
	
	<c:if test="${count > 0 }">
		<div align="center">
			<table border="1">
				<tr>
					<td align="center" width="50">번호</td>
					<td align="center" width="30">제목</td>
					<td align="center" width="30">작성자</td>
					<td align="center" width="30">작성일</td>
					<td align="center" width="30">조회수</td>
				</tr>
				
				<c:forEach var="boardDto" items="${boardList}">
					<tr>
						<td align="center" width="50">${boardDto.boardNumber}</td>
						<td align="center" width="250">
							<c:if test="${boardDto.sequenceLevel > 0}">
								<c:forEach begin="0" end="${boardDto.sequenceLevel}">
									&nbsp;&nbsp;
								</c:forEach>
								[답글]
							</c:if>
							<a href="${root}/fileBoard/read.do?boardNumber=${boardDto.boardNumber}&pageNumber=${currentPage}" style="text-decoration: none;">${boardDto.subject}</a>
						</td>
						<td align="center" width="70">${boardDto.writer}</td>
						<td align="center" width="200">	
							<fmt:formatDate value="${boardDto.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td align="center" width="50">${boardDto.readCount}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	
	<div align="center">
		<%--
			1. 한 페이지 당 게시물 수 : 10
			2. 페이지번호 블럭 : 10
			3. 총 페이지 수 = 10page = 전체 레코드 수 100 / 한 페이지당 게시물 수 10
			
			if (전체 레코드수  % 한 페이지당 게시물수 == 0)
				총 페이지수 = 전체 레코드 수 / 한 페이지당 게시물 수;
			else if(전체 레코드수 % 한 페이지당 게시물수 != 0)
				총 페이지수 = (전체 레코드 수 / 한 페이지당 게시물 수) + 1;
				
			4. 페이지번호 블럭 : 10 [1][2][3][4][5]...[10]
							요청페이지번호 5이면, 화면에 시작번호 1, 끝번호 10
							pageBlock, currentPage : 시작번호, 끝번호 추출
							
							int startPage = ((currentPage-1)/pageBlock)*pageBlock + 1;											
							int endPage = startPage + pageBlock - 1;
											
			5. boardSize, currentPage, count : Command Data
				
			6. 		
				
		 --%>
		 
		 <fmt:parseNumber var="pageCount" value="${count/boardSize + (count % boardSize == 0 ? 0:1)}" integerOnly="true"/>
		 <c:set var="pageBlock" value="${5}"/>
		 <fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true"/>
		 <c:set var="startPage" value="${result*pageBlock+1}"/>
		 <c:set var="endPage" value="${startPage + pageBlock - 1 }"/>
		 
		 <c:if test="${pageCount < endPage}">
		 	<c:set var="endPage" value="${pageCount}"/>
		 </c:if> 		
		 	<%--${startPage}, ${endPage} --%>
		 <c:if test="${startPage > pageBlock}">
		 	<a href="${root}/fileBoard/list.do?pageNumber=${startPage - pageBlock}">[이전]</a>
		 </c:if>
		 	
		 <c:forEach var="i" begin="${startPage}" end="${endPage}">
		 	<a href="${root}/fileBoard/list.do?pageNumber=${i}">[${i}]</a>
		 </c:forEach>

		 
		 <c:if test="${endPage < pageCount}">
		 	<a href="${root}/fileBoard/list.do?pageNumber=${startPage + pageBlock}">[다음]</a>
		 </c:if>
	</div>
</body>
</html>