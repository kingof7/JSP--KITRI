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
	<jsp:include page="../../../index.jsp"/>
	<div id=memberform>
	<span style="display:block;text-align:center;">회원가입( * 필수입력사항입니다.)</span>
	<form action="${root}/member/updateOk.do" method="post" class="form"
			onsubmit="return createChkForm(this)" name="createChkForm">
			
		<input type="hidden" name="num" value="${memberDto.num }">
			
		<div class="label">
			<label>아이디</label>
			*<input type="text" name="id" value="${memberDto.id}" disabled="disabled"/>			
		</div>
		<div class="label">
			<label>비밀번호</label>
			*<input type="password" name="password" value="${memberDto.password }"/>
		</div>
		<div class="label">
			<label>비밀번호확인</label>
			*<input type="password" name="passwordCheck" value="${memberDto.password }"/>
		</div>
		<div class="label">
			<label>이름</label>
			*<input type="text" name="name" value="${memberDto.name }" disabled="disabled"/>
		</div>
		<div class="label">
			<label>주민번호</label>
			*<input type="text" name="jumin1" value="${memberDto.jumin1 }" disabled="disabled" /> -
			<input type="text" name="jumin2" style="margin-left:0px;" value="${memberDto.jumin2 }" disabled="disabled"/>
		</div>
		<div class="label mar">
			<label>이메일</label>
			<input type="text" name="email" value="${memberDto.email }"/>
		</div>
		<div class="label mar">
			<label>우편번호</label>
			<input type="text" name="zipcode" value="${memberDto.zipcode }"/>
			<button type="button" name="idChk" onclick="zipCode('${root}')">우편번호검색</button>
		</div>
		<div class="label mar">
			<label>주소</label>
			<input type="text" name="address" value="${memberDto.address }"/>
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
		
		<script type="text/javascript">
			createChkForm.job.value="${memberDto.job }"
		</script>
		
		<div class="label">
			<label>메일수신</label>
			<input type="checkbox" name="mailing" value="Yes">Yes
		    <input type="checkbox" name="mailing" value="No">No
		</div>
		<script type="text/javascript">
			for(var i=0;i<createChkForm.mailing.length;i++){
				if(createChkForm.mailing[i].value == "${memberDto.mailing}"){
					createChkForm.mailing[i].checked = true;
				}
			}
		</script>		
		
		<div class="label">
			<label>관심분야</label>
			<input type="checkbox" name="interest" value="경제">경제
		    <input type="checkbox" name="interest" value="IT">IT
		    <input type="checkbox" name="interest" value="음악">음악
		    <input type="checkbox" name="interest" value="미술">미술
		    <input type="hidden" name="resultInterest" />		    
		</div>
		
		
		<c:forTokens var="interest" items="${memberDto.interest}" delims=",">
			<script type="text/javascript">
				for(var i=0;i<createChkForm.interest.length;i++){
					if(createChkForm.interest[i].value == "${interest}"){
						createChkForm.interest[i].checked = true;
					}
				}
			</script>			
		</c:forTokens>
		
		<div class="label" style="border-bottom: solid 0px; text-align: center;">
			<input type="submit" value="수정"/>
			<input type="reset" value="취소"/>
		</div>
	</form>
	</div>
</body>
</html>