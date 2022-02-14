<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="Member.domain.Member"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
<form name='fs' method="post" action="../member/mController?message=memberUpdateUserAdmin">
		<input type="email"  id="email" name="emails" value = "${MemberInfoz.email}" disabled>
		<input type="hidden" name ="email" value = "${MemberInfoz.email}" >
		<input type="password"  id="pwd" name="pwd" value = "${MemberInfoz.pwd}" placeholder="비밀번호">
		<input type="text"  id="name"  name="name" value = "${MemberInfoz.memName}" disabled><br>
		<input type="date"  id="birth" name = "birth" value = "${MemberInfoz.birth}" disabled>	<br>
		<input type="text"  id="nick"  name = "nick" value = "${MemberInfoz.nick}"  placeholder="닉네임">	<br>
		<input type="tel"   id="phone" name = "phone" value = "${MemberInfoz.memPhone}" placeholder="전화번호">	<br>
		<input type="date"  id="anni"  value = "${MemberInfoz.anni}"  disabled>	<br>
		
		<p>변경 전 주소</p><input type="text"   style="ime-mode:disabled; margin: 0 0 0.5rem 0; width: 600px; height: 2rem; border:solid 1px black;"
           size="60" class="form-control" name = "address" value = "${MemberInfoz.memLoc}" placeholder="주소">
		<br>
		
		<input type="text"  class="form-control"  id="sample6_postcode"  name = "postNumber" value = "${MemberLocPostNumber} "placeholder="우편번호">
		<input type="button"class="form-control"  onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text"  class="form-control"  id="sample6_address" name="Address" value = "${MemberLocAddress}"placeholder="주소"><br>
		<input type="text" 	class="form-control"  id="sample6_detailAddress" name="upAddress"  value="${MemberLocUpAddress}" placeholder="상세주소">
		<input type="text"  class="form-control"  id="sample6_extraAddress" placeholder="참고항목">
		
		<input type="text"  class="form-control" name = "gender" value = "${MemberInfoz.gender}" placeholder="성별" disabled>
		<input type="radio" id ="solo" name="couple" value="0" checked>
		<label for="solo">없음</label>
		<input type="radio" id ="coupleC" name="couple" value="1" >
		<label for="coupleC">커플</label><br>
		<br>	
		<input type="radio" id ="defaultLicense" name="lisence" value="0" checked>
		<label for="defaultLicense">면허없음</label>
		<input type="radio" id ="licenseC" name="lisence" value="1" >
		<label for="licenseC">면허있음</label><br>
		<input type="submit" value="전송">
</form>
</body>
</html>