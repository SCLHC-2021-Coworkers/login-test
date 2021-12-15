function setRegisterFormEvent() {
	var verif_email_btn = $('#verif_email_btn');
	var back_btn = $('#back_btn');
	var register_form = $('#register_form');

	if (!(register_form && verif_email_btn && back_btn)) {
		return;
	}

	verif_email_btn.click(function (event) {
		event.preventDefault();

		var email = nullify($('#email').val());

		if (!email || email.length === 0) {
			alert('이메일을 입력해주세요');
			return;
		}

		if (!register_form.get(0).checkValidity()) {
			$('#email').addClass('is-invalid');
			$('.valid-feedback').hide();
			$('.is-valid-feedback').show();
			return;
		}
	});

	back_btn.click(function (event) {
		event.preventDefault();
		window.location = '/login';
	});

	register_form.submit(function (event) {
		event.preventDefault();

		var username = nullify($('#username').val());
		var email = nullify($('#email').val());
		var password = nullify($('#password').val());
		var chkPassword = nullify($('#chkPassword').val());
		var name = nullify($('#name').val());
		var position = nullify($('#position').val());

		if (chkPassword === password) {
			$.ajax({
				url: '/auth/register',
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				data: JSON.stringify({
					username: username,
					email: email,
					password: password,
					name: name,
					position: position,
				}),
				success: function (result, status, xhr) {
					alert('회원가입이 완료되었습니다. 다시 로그인 해주세요.');
					window.location = '/login';
				},
				error: function (xhr, status, error) {
					console.log(JSON.stringify(xhr.status, null, 2));
					/*
					var contents = xhr.responseJSON.contents;
					var msg = '[' + xhr.responseJSON.message + ']' + '\n';
					switch (xhr.responseJSON.code){
						case 'EAUTH400-01':
							$.each(contents, function (i, item){
								msg += item.content + '\n\n';
							})
							alert(msg);
					}
					*/
				},
			});
		} else {
			alert('비밀번호가 서로 다릅니다. 다시 입력해주세요.');
		}
	});
}

setRegisterFormEvent();
