$(document).ready(function() {
    $('#registrationForm').submit(function(e) {
        e.preventDefault();
        var username = $('#username').val();
        var password = $('#password').val();
        var confirmPassword = $('#confirmPassword').val();

        if (password !== confirmPassword) {
            alert('パスワードが一致しません');
            return;
        }

        fetch('http://localhost:8080/usercreate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password)
        })
        .then(function(response) {
            if (response.ok) {
                alert('登録が完了しました');
                window.location.href = 'http://127.0.0.1:5500/ui/login/login.html';
            } else {
                alert('登録中にエラーが発生しました');
            }
        })
        .catch(function(error) {
            console.error('通信エラー:', error);
        });
    });
});