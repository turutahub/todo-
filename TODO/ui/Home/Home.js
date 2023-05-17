/*DOMが読み込まれた後、fetchOrders関数を呼び出す
  ボタンをクリックしなくても表示される */
document.addEventListener("DOMContentLoaded", fetchOrders());

//データベース接続確認
async function fetchOrders() {
	const orders = await fetch("http://localhost:8080/todo");
	if (!orders.ok) {
	  throw new Error("Could not fetch orders");
	}
	const ordersJson = await orders.json();
	console.log(ordersJson);
	renderOrders(ordersJson);
  }
  

var tasks = [];
//↑の処理（最終的にデータベースへ）
function renderOrders(orders) {
    const taskList = document.getElementById("taskList");

    // 既存コンテンツの整理
    taskList.innerHTML = "";

    // 各タスクをレンダリングする
    orders.forEach(order => {
        const row = document.createElement("tr");

        //テーブルセルを作成し、データをセットする
        const idCell = document.createElement("td");
        idCell.innerText = order.id;
        row.appendChild(idCell);

        const titleCell = document.createElement("td");
        titleCell.innerText = order.title;
        row.appendChild(titleCell);

        const startCell = document.createElement("td");
        startCell.innerText = order.start;
        row.appendChild(startCell);

        const endCell = document.createElement("td");
        endCell.innerText = order.end;
        row.appendChild(endCell);

        const completedCell = document.createElement("td");
        completedCell.innerText = order.completed;
        row.appendChild(completedCell);

        const createdAtCell = document.createElement("td");
        createdAtCell.innerText = order.created_at;
        row.appendChild(createdAtCell);

        const updatedAtCell = document.createElement("td");
        updatedAtCell.innerText = order.updated;
        row.appendChild(updatedAtCell);

        //taskListテーブルに行を追加する
        taskList.appendChild(row);
    });
}
/*タスク追加
function addTask() {
	var taskName = document.getElementById("taskname").value;
	var taskPriority = document.getElementById("taskPriority").value;
	var taskDueDate = document.getElementById("taskDueDate").value;

	var task = {
		name: taskName,
		priority: taskPriority,
		dueDate: taskDueDate
	};

	tasks.push(task);

	showTasks();
}

//日付・時計機能の実装
function displayClock() {
	const now = new Date();
	const hour = now.getHours();
	const minute = now.getMinutes();
	const second = now.getSeconds();
	const clock = document.querySelector('#clock');
	clock.textContent = `${hour}:${minute}:${second}`;
  }
  
  function displayDate() {
	const now = new Date();
	const year = now.getFullYear();
	const month = now.getMonth() + 1;
	const date = now.getDate();
	const day = ['日', '月', '火', '水', '木', '金', '土'][now.getDay()];
	const dateElement = document.querySelector('#date');
	dateElement.textContent = `${year}/${month}/${date}(${day})`;
  }
  
  setInterval(displayClock, 1000);
  setInterval(displayDate, 1000);*/