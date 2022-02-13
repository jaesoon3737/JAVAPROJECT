<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>제주 프렌드</title>
<title>Insert title here</title>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script>
				function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
				            var addr = ''; // 주소 변수
				            var extraAddr = ''; // 참고항목 변수
				            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				              addr = data.roadAddress;
				            } else { // 사용자가 지번 주소를 선택했을 경우(J)
				              addr = data.jibunAddress;
				            }
				           // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				        if(data.userSelectedType === 'R'){
				           // 법정동명이 있을 경우 추가한다. (법정리는 제외)
				           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				         extraAddr += data.bname;
				        }
				        // 건물명이 있고, 공동주택일 경우 추가한다.
				        if(data.buildingName !== '' && data.apartment === 'Y'){
				         extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				        }
				        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				        if(extraAddr !== ''){
				         extraAddr = ' (' + extraAddr + ')';
				        }
				        // 조합된 참고항목을 해당 필드에 넣는다.
				         document.getElementById("sample6_extraAddress").value = extraAddr;
				        } else {
				         document.getElementById("sample6_extraAddress").value = '';
				        }
				         // 우편번호와 주소 정보를 해당 필드에 넣는다.
				         document.getElementById('sample6_postcode').value = data.zonecode;
		                 document.getElementById("sample6_address").value = addr;
				         // 커서를 상세주소 필드로 이동한다.
				         document.getElementById("sample6_detailAddress").focus();
			            }
			        }).open();
			}
	   </script>
</head>

<body>
<form name='f' method="post" action="../member/mController?message=signup">
		<input type="email"  id="email" name="email" placeholder="이메일">
		<input type='submit' id='checkEmailbutton' value='이메일 중복확인'>
		<div   id="messages"></div>
		<input type="password"  id="pwd" name="pwd" placeholder="비밀번호">
		<input type="password"  class="passwordCheck" id="pwdCheck" name="pwdCheck" placeholder="비밀번호확인"><br>
		<div   id='checkPw'></div>
		<input type="text"  id="name"  name="name"  placeholder="이름">		<br>
		<input type="date"  id="birth" name="birth" placeholder="생일">	<br>
		<input type="text"  id="nick"  name="nick"  placeholder="닉네임">	<br>
		<input type="tel"   id="phone" name="phone" placeholder="전화번호">	<br>
		<input type="date"  id="anni"  name="anni"  placeholder="기념일">	<br>
		<input type="text"  class="form-control"  id="sample6_postcode"  name = "postNumber" placeholder="우편번호">
		<input type="button"class="form-control"  onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text"  class="form-control"  id="sample6_address" name="Address" placeholder="주소"><br>
		<input type="text" 	class="form-control"  id="sample6_detailAddress" name="upAddress" placeholder="상세주소">
		<input type="text"  class="form-control"  id="sample6_extraAddress" placeholder="참고항목">
		<br>
		<input type="radio" id ="male" name="gender" value="0"  checked>
		<label for="male">남자</label>
		<input type="radio" id ="female" name="gender" value="1" >
		<label for="female">여자</label><br>
		<br>
		<input type="radio" id ="solo" name="couple" value="0" checked>
		<label for="solo">없음</label>
		<input type="radio" id ="coupleC" name="couple" value="1" >
		<label for="coupleC">커플</label><br>
		<br>
		<input type="radio" id ="defaultLicense" name="license" value="0" checked>
		<label for="defaultLicense">면허없음</label>
		<input type="radio" id ="licenseC" name="license" value="1" >
		<label for="licenseC">면허있음</label><br>
		<input type='submit' value='회원가입'>
</form>
</body>
	<script
		  src="https://code.jquery.com/jquery-3.5.1.min.js"
		  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		  crossorigin="anonymous">
	</script>
	<script>
		$('#checkEmailbutton').on("click" , (e) => {
			const email = $('#email').val();
			e.preventDefault();
			console.log('btn click');
			if(email.length == 0 || email == null ) return alert("이메일을 입력하세요.");
			$.ajax({
				type:'post',
				async:true,
				url:'http://localhost:8080/project/member/mController?message=emailCheck',
				dataType:'text',
				data:{email:email},
				success: function(data, textStatus) {
					if (data == 1)  {
						$('#messages').text('사용할 수 있는 이메일입니다.');
						$('#checkEmailbutton').prop('disabled , true');
					} else {
						$('#messages').text('이미 사용 중인 아이디입니다.');
					}
				},
				error:function(data, textStatus){
					$('#messages').text('error');
				}
			
			})
		} )
	</script>
	<script src="js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
			let pwdChecked = false;
			$(".passwordCheck").focusout(function(){
					pwdCheckedf($(this).val())
			})
			function pwdCheckedf(pw){
				if(pw==""){
					$("#email").text("");
					return;
				}
							
				if($('#pwd').val()!=$('#pwdCheck').val()){
					$("#checkPw").html("비밀번호가 일치하지 않습니다.");
					$("#checkPw").attr('color', 'red');
					$("#pwdCheck").val('');
					$("#pwdCheck").focus();
					pwdChecked = false;
				} else {
					$("#checkPw").html("비밀번호가 일치합니다.");
					$("#checkPw").attr('color' , 'blue');
					pwdChecked = true;
					}	
				}				
	</script>
</html>