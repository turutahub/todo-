// login-form要素を取得
const loginForm = document.getElementById("login-form");

// フォームの送信イベントを監視
loginForm.addEventListener("submit", function(event) {
  // デフォルトの送信処理をキャンセル
  event.preventDefault();

  // フォームの入力値を取得
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  // ユーザー名とパスワードを使ってログイン処理を行う
  login(username, password);
});

// ログイン処理を行う関数
function login(username, password) {
  // ユーザー名とパスワードが正しい場合にログイン成功とする
  if (username === "user" && password === "password") {
    console.log("ログインに成功しました");
    // ログインフォームを非表示にする
    document.getElementById("login-form").style.display = "none";
    // ログイン後のコンテンツを表示する
    document.getElementById("content").style.display = "block";
  } else {
    console.log("ユーザー名またはパスワードが間違っています");
  }
}
