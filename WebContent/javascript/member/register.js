/**
 * 
 */

function createForm(obj){
	if(obj.id.value == ""){
		alert("아이디를 반드시 입력하세요.");
		obj.id.focus();
		return false;
	}
	if(obj.password.value == ""){
		alert("비밀번호를 입력하세요");
		obj.password.focus();
		return false;
	}
	if(obj.password.value.length <= 7){
		alert("비밀번호를 7자 이상 입력하세요");
		obj.password.focus();
		return false;
	}
	if(obj.password.value != obj.passwordCheck.value){
		alert("비밀번호가 일치하지 않습니다.");
		obj.passwordCheck.focus();
		return false;
	}
	var str="";
	for (var i=0; i<obj.interest.length; i++){
		if(obj.interest[i].checked==true){
			str += obj.interest[i].value + ",";
		}
	}
	//alert(str);
	obj.resultInterest.value=str;
}

function idCheck(obj, root){
	if(obj.id.value == ""){
		alert("아이디를 입력하세요");
		obj.id.focus();
		return false;
	}
	
	var url = root + "/member/idCheck.do?id=" + obj.id.value;
	//console.log(url);
	window.open(url, "", "width=400, height=200");
}

function zipCode(root){
	var url = root + "/member/zipcode.do";
	//alert(url);
	window.open(url, "", "width=500, height=500, scrollbars=yes");
}

function sendAddress(zipcode, sido, gugun, dong, ri, bungi){
	var address = sido + " " + gugun + " " + dong + " " + ri + " " + bungi;
	//alert(zipcode + "\n" + address);
	opener.createChkForm.zipcode.value = zipcode;
	opener.createChkForm.address.value = address;
	close();
}
