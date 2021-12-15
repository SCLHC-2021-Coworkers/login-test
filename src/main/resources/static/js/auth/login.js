function setLoginFormEvent() {
    var login_form = $('#login_form');

    if (!login_form) {
        return;
    }

    login_form.submit(function (event) {
        event.preventDefault();

        var username = nullify($('#username').val());
        var password = nullify($('#password').val());

        $.ajax({
            url: '/auth/login',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                username: username,
                password: password,
            }),
            success: function (result, status, xhr) {
                window.location = '/';
            },
            error: function (xhr, status, error) {
                alert(xhr.responseJSON.message);
            },
        });
    });
}


setLoginFormEvent();
