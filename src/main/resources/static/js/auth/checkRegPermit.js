function setCheckRegPermitTxtEvent() {
	var chk_reg_permit_txt = $('#chk_reg_permit_txt');
	var permitRegOn = $('#permit-register-on');
	var permitRegOff = $('#permit-register-off');

	$.ajax({
		url: '/auth/registration-permission',
		type: 'GET',
		dataType: 'json',
		success: function (result, status, xhr) {
			var isRegPermitted = result.contents[0].content === 'on';

			if (chk_reg_permit_txt) {
				chk_reg_permit_txt.text(isRegPermitted ? '허가' : '불허');
			}
			if(!isRegPermitted){
				$('#to_register_btn').click(function(){
					alert("계정 생성 권한이 없습니다.");
				});
			}else{
				setToRegisterBtnEvent();
			}
			if (permitRegOn && permitRegOff) {
				if (isRegPermitted) {
					permitRegOn.attr('checked', true);
				} else {
					permitRegOff.attr('checked', true);
				}
			}
		},
		error: function (xhr, status, error) {
			alert(xhr.responseJSON.message);
		}
	});
}
function setToRegisterBtnEvent() {
	var to_register_btn = $('#to_register_btn');

	if (!to_register_btn) {
		return;
	}

	to_register_btn.click(function () {
		window.location = '/register';
	});
}

setCheckRegPermitTxtEvent();
