// login-form要素を取得
const loginForm = document.getElementById("login-form");

// フォームの送信イベントを監視
loginForm.addEventListener("submit", function(event) {
  // デフォルトの送信処理をキャンセル
  event.preventDefault();

  // フォームの入力値を取得
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  // ユーザー名とパスワードをリクエストボディに設定
  const data = { username: username, password: password };

  // ログインAPIにリクエストを送信
  fetch("/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  })
    .then(response => response.text())
    .then(result => {
      console.log(result); // ログイン結果を表示
      if (result === "ログインに成功しました") {
        // ログインに成功した場合の処理
        document.getElementById("login-form").style.display = "none";
        document.getElementById("content").style.display = "block";
        location.href = "http://127.0.0.1:5500/ui/Home/Home.html";
      }
    })
    .catch(error => {
      console.error("エラーが発生しました", error);
    });
});
