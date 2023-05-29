//DOMが読み込まれた後、fetchOrders関数を呼び出す
//ボタンをクリックしなくても表示される 
document.addEventListener("DOMContentLoaded", fetchOrders);

// データベースからデータを取得
async function fetchOrders() {
    const todoResponse = await fetch("http://localhost:8080/todo");
    if (!todoResponse.ok) {
        throw new Error("Could not fetch todo");
    }
    console.log(todoResponse); // レスポンスオブジェクトを表示

    const todoJson = await todoResponse.json();
    console.log(Array.isArray(todoJson)); // 配列かどうかをtrue falseで確認する
    console.log(todoJson);
    console.log([todoJson]);
    console.log(todoJson[0]);
}
var array = [];

//データを表示する
function renderTodoList(todoJson) {
    const taskList = document.getElementById("taskList");

    // 各タスクをレンダリングする
    todoJson.List.forEach((todo) => {
		console.log(todo)//todoデータが取得できているか
        const row = document.createElement("tr");

        const idCell = document.createElement("td");
        idCell.innerText = todo.id;
        row.appendChild(idCell);

        const titleCell = document.createElement("td");
        titleCell.innerText = todo.title;
        row.appendChild(titleCell);

        const startCell = document.createElement("td");
        startCell.innerText = todo.start;
        row.appendChild(startCell);

        const endCell = document.createElement("td");
        endCell.innerText = todo.end;
        row.appendChild(endCell);

        const completedCell = document.createElement("td");
        completedCell.innerText = todo.completed;
        row.appendChild(completedCell);

        const createdAtCell = document.createElement("td");
        createdAtCell.innerText = todo.created_at;
        row.appendChild(createdAtCell);

        taskList.appendChild(row);
    });
}

// タスクを追加する
async function addTask() {
    const taskTitle = document.getElementById("taskTitle").value;
    const taskStart = document.getElementById("taskStart").value;
    const taskEnd = document.getElementById("taskEnd").value;
    const taskCompleted = document.getElementById("taskCompleted").checked;

    console.log(taskTitle, taskStart, taskEnd, taskCompleted);//デバッグ用

    const task = {
        title: taskTitle,
        start: taskStart,
        end: taskEnd,
        completed: taskCompleted,
        created_at: new Date().toISOString() // Add creation timestamp
    };

    const addTaskForm = document.getElementById("addTaskForm");
    addTaskForm.reset();

    try {
        const response = await fetch("http://localhost:8080/todo", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        });

        if (!response.ok) {
            throw new Error("Could not add task");
        }

        // タスク追加後にタスクリストを再取得して表示する
        fetchOrders();
    } catch (error) {
        console.error(error);
    }
}

//タスクを更新する
async function updateTask(taskId) {
    const taskTitle = document.getElementById("taskTitle").value;
    const taskStart = document.getElementById("taskStart").value;
    const taskEnd = document.getElementById("taskEnd").value;
    const taskCompleted = document.getElementById("taskCompleted").checked;
    
    const task = {
        id: taskId,
        title: taskTitle,
        start: taskStart,
        end: taskEnd,
        completed: taskCompleted
    };
    
    try {
        const response = await fetch(`http://localhost:8080/todo/${taskId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        });
        
        if (!response.ok) {
            throw new Error("Could not update task");
        }
        
        fetchOrders(); // タスク更新後にタスクリストを再取得して表示する
    } catch (error) {
        console.error(error);
    }
}

//タスクを削除する
async function deleteTask(taskId) {
    try {
        const response = await fetch(`http://localhost:8080/todo/${taskId}`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("Could not delete task");
        }

        fetchOrders(); // タスク削除後にタスクリストを再取得して表示する
    } catch (error) {
        console.error(error);
    }
}

/*ソート機能の実装
// ソートの基準となる要素と昇順・降順を選択するUI要素を取得
const sortSelect = document.getElementById("sort-select");
const orderCheckbox = document.getElementById("order-checkbox");

// ソートの基準と昇順・降順が変更されたときのイベントリスナーを設定
sortSelect.addEventListener("change", applySorting);
orderCheckbox.addEventListener("change", applySorting);

// ソートを適用する関数
function applySorting() {
  const sortBy = sortSelect.value;
  const ascending = orderCheckbox.checked;

  // サーバーにソートのリクエストを送信し、ソートされた結果を取得
  fetch("/todos/sort", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ sortBy, ascending }),
  })
    .then((response) => response.json())
    .then((data) => {
      // ソートされた結果を表示
      displayTodos(data);
    })
    .catch((error) => {
      console.error("ソートのリクエストに失敗しました", error);
    });
}

// ToDoリストの表示を更新する関数
function displayTodos(todos) {
  // ToDoリストの表示をクリア

  // ソートされたToDoリストを表示
}*/

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
  setInterval(displayDate, 1000);
