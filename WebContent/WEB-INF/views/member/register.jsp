<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${ pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./../css/member/registerStyle.css">
<script type="text/javascript" src="${root}/javascript/member/register.js"></script>
</head>
<body>	
	<div id=memberform>
	<span style="display:block;text-align:center;">회원가입( * 필수입력사항입니다.)</span>
	<form name="createChkForm" action="${root}/member/registerOk.do" method="post" class="form" onsubmit="return createForm(this)">
		<div class="label">
			<label>아이디</label>
			*<input type="text" name="id"/>			
			<button type="button" onclick="idCheck(createChkForm, '${root}')">아이디중복확인</button>
		</div>
		<div class="label">
			<label>비밀번호</label>
			*<input type="password" name="password"/>
		</div>
		<div class="label">
			<label>비밀번호확인</label>
			*<input type="password" name="passwordCheck"/>
		</div>
		<div class="label">
			<label>이름</label>
			*<input type="text" name="name"/>
		</div>
		<div class="label">
			<label>주민번호</label>
			*<input type="text" name="jumin1"/> -
			<input type="text" name="jumin2" style="margin-left:0px;"/>
		</div>
		<div class="label mar">
			<label>이메일</label>
			<input type="text" name="email"/>
		</div>
		<div class="label mar">
			<label>우편번호</label>
			<input type="text" name="zipcode"/>
			<button type="button" onclick="zipcode1('${root}')">우편번호검색</button>
		</div>
		<div class="label mar">
			<label>주소</label>
			<input type="text" name="address"/>
		</div>
		<div class="label">
			<label>직업</label>
			<select name="job">
				<option value="판사" selected>판사</option>
			    <option value="의사">의사</option>
			    <option value="변호사">변호사</option>
			    <option value="회계사">회계사</option>
			</select>
		</div>
		<div class="label">
			<label>메일수신</label>
			<input type="checkbox" name="mailing" value="Yes">Yes
		    <input type="checkbox" name="mailing" value="No">No
		</div>
		<div class="label">
			<label>관심분야</label>
			<input type="checkbox" name="interest" value="경제">경제
		    <input type="checkbox" name="interest" value="IT">IT
		    <input type="checkbox" name="interest" value="음악">음악
		    <input type="checkbox" name="interest" value="미술">미술
		    <input type="hidden" name="resultInterest"/> 
		</div>
		<div class="label" style="border-bottom: solid 0px; text-align: center;">
			<input type="submit" value="가입"/>
			<input type="reset" value="취소"/>
		</div>
	</form>
	</div>
</body>
</html>