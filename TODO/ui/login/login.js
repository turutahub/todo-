// loginform要素を取得
const loginForm = document.getElementById("loginForm");
const backButton = document.getElementById("backButton");
// フォームの送信イベントを監視
loginForm.addEventListener("submit", login);

// ログイン処理
async function login(event) {
  event.preventDefault();

  // フォームの入力値を取得
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  // ユーザー名とパスワードをリクエストボディに設定
  const data = { username: username, password: password };

  try {
    // ログインAPIにリクエストを送信
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    });

    const result = await response.json();
    console.log(result); // ログイン結果を表示

    if (result === "ログイン成功") {
      // ログインに成功した場合の処理
      document.getElementById("loginForm").style.display = "none";
      document.getElementById("content").style.display = "block";
      window.location.href = "http://127.0.0.1:5500/ui/Home/Home.html";
    } else {
      // ログインに失敗した場合の処理
      alert("ログイン失敗");
    }
  } catch (error) {
    console.error("エラーが発生しました", error);
    // 戻るボタンのクリックイベントを監視
backButton.addEventListener("click", goBack);

// 戻る処理
function goBack() {
  loginForm.style.display = "block";
  backButton.style.display = "none";
    }
  }
}