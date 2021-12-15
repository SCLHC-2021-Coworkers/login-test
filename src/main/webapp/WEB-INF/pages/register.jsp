<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../templates/taglibs.jsp"%>
<link href="/css/register.css" rel="stylesheet">
<html>
<head>

<%@ include file="../templates/headprops.jsp"%>

<title>Registration</title>

</head>
<body>
	<div class="row">
	<div class="col-xl-4">
	</div>
		<div class="col-xl-4"  style="border: 1px #01a1b9 solid; margin-top:80px; padding: 20px 30px 20px 30px; ">
	<form id="register_form" class="needs-validation" novalidate>
		<div class="d-flex justify-content-center">
			<h3>관리자등록</h3>
		</div>
		<br />
		<br />
		<div class="form-group">
			<label>아이디</label> <input type="text" id="username" class="form-control" placeholder="특수문자 '_'만 입력 가능">
		</div>
		<div class="form-group">
			<label>이메일</label>
			<div class="d-flex justify-content-center" id="email_form">
				<div class="d-flex flex-column col-xl-11" style="margin-left:8px">
					<input type="email" id="email" class="form-control" placeholder="이메일 입력">
					<div class="valid-feedback">인증이 완료되었습니다.</div>
					<div class="invalid-feedback">잘못된 이메일 형식입니다</div>
				</div>
				<div class="d-flex flex-column justify-content-start" >
					<button id="verif_email_btn" class="btn btn-primary text-nowrap ml-1">인증하기</button>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label>비밀번호</label> <input type="password" id="password" placeholder="8자 이상 입력" class="form-control">
		</div>
		<div class="form-group">
			<label>비밀번호 확인</label> <input type="password" id="chkPassword" class="form-control">
		</div>
		<div class="form-group">
			<label>이름</label> <input type="text" id="name" class="form-control">
		</div>
		<div class="form-group">
			<label>직책</label>
			<select class="form-control" id="position">
				<option selected></option>
				<option value="계약직">계약직</option>
				<option value="사원">사원</option>
				<option value="주임">주임</option>
				<option value="대리">대리</option>
				<option value="과장">과장</option>
				<option value="차장">차장</option>
				<option value="부장">부장</option>
				<option value="이사">이사</option>
				<option value="상무">상무</option>
			</select>
			<!--<input type="text" id="position" class="form-control">-->
		</div>
		<br/>
		<div class="d-flex justify-content-center">
	<div class="form-group">
		<label>등록 허가 여부: </label> <strong id="chk_reg_permit_txt"></strong>
	</div>
	</div>
	
	<br />
	<div class="d-flex justify-content-between">
			<div>
			<button type="button" class="btn btn-secondary" id="back_btn">뒤로</button>
			</div>
			<div>
			<button type="submit" class="btn btn-info" id="regist_btn">회원가입</button>
			</div>
		</div>
	</form>	
	</div>
	</div>
	
	<%@ include file="../templates/footer.jsp"%>
	<script src="/js/auth/register.js"></script>
	<script src="/js/auth/checkRegPermit.js"></script>
</body>
</html>
