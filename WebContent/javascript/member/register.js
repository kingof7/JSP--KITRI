/**
 * 
 */

function createForm(obj) {
	if(obj.id.value==""){
		alert("아이디를 반드시 입력해주세요.");
		obj.id.focus();
		return false;		
	}
	
	if(obj.password.value == ""){
		alert("비밀번호를 반드시 입력하세요.");
		obj.password.focus();
		return false;
	}
	
	if(obj.password.value.length <= 7){
		alert("비밀번호를 8자리 이상으로 입력하세요");
		obj.password.focus();
		return false;
	}
	
	if(obj.password.value != obj.passwordCheck.value){
		alert("비밀번호가 맞는지 확인하세요.");
		obj.password.focus();
		return false;
	}
	
	var str = "";
	for (var i = 0; i < obj.interest.length; i++) {
		if (obj.interest[i].checked == true) {			

			str += obj.interest[i].value + ",";
		}

		// alert(str);
		// js에서 java로 데이터 넘겨주기 ->hidden
		obj.resultInterest.value = str;
	}
	
}
function idCheck(obj, root){
	//alert(obj.id.value);
	
	if(obj.id.value ==""){
		alert("아이디를 반드시 검색하세요.");
		obj.id.focus();
		return false;
	}
	
	var url = root + "/member/idCheck.do?id=" + obj.id.value;
	//alert(url);
	//location.href=url; //프로퍼티파일과관련
	window.open(url, "", "width=400, height=200");
}

//우편번호 가져오기
function zipcode1(url){
	url += "/member/zipcode.do";
	//alert(url);
	window.open(url,"","width=400, height=400, scrollbars=yes");
	
	
}

//우편번호 검색, 입력하기
function sendAddress(zipcode, sido, gugun, dong, ri, bunji){
	var address = sido + " " + gugun + " " + dong + " "+ ri + bunji;
	//alert(zipcode + "\n" + address);
	
	opener.createChkForm.zipcode.value=zipcode;
	opener.createChkForm.address.value=address;
	close();
	
	
}