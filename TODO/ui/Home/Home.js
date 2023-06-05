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
  
    renderTodoList(todoJson)
}
var array = [];

// データを表示する
function renderTodoList(todoJson) {
    const taskList = document.getElementById("taskList");
    $(".row").remove();
    // 各タスクをレンダリングする
    todoJson.todoList.forEach((todo) => {
        console.log(todo); // todoデータが取得できているか
        const row = document.createElement("tr");
        row.setAttribute("class", "row");

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
        endCell.innerText = todo.end_date;
        row.appendChild(endCell);

        const completedCell = document.createElement("td");
        completedCell.innerText = todo.completed;
        row.appendChild(completedCell);

        const createdAtCell = document.createElement("td");
        createdAtCell.innerText = todo.created_at;
        row.appendChild(createdAtCell);

        const actionsCell = document.createElement("td");
        const editButton = document.createElement("button");
        editButton.innerText = "編集";
        editButton.addEventListener("click", () => editTask(todo));
        actionsCell.appendChild(editButton);

        const deleteButton = document.createElement("button");
        deleteButton.innerText = "削除";
        deleteButton.addEventListener("click", () => deleteTask(todo.id));
        actionsCell.appendChild(deleteButton);

        row.appendChild(actionsCell);
        taskList.appendChild(row);
    });
}



// タスクを追加する
async function addTask() {
    const taskTitle = document.getElementById("taskTitle").value;
    const taskStart = document.getElementById("taskStart").value;
    const taskEnd = document.getElementById("taskEnd").value;
    const taskCompleted = document.getElementById("taskCompleted").checked;
    const createdAt = new Date(); // 現在の日時を取得

    const task = {
        title: taskTitle,
        start: taskStart,
        end_date: taskEnd,
        completed: taskCompleted,
        created_at: createdAt,
    };

    try {
        const response = await fetch("http://localhost:8080/todo", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(task),
        });

        if (!response.ok) {
            throw new Error("Could not add task");
        }

        fetchOrders(); // タスク追加後にタスクリストを再取得して表示する
        resetForm();
    } catch (error) {
        console.error(error);
    }
}

// タスクを編集する
function editTask(task) {
    const taskTitle = document.getElementById("taskTitle");
    const taskStart = document.getElementById("taskStart");
    const taskEnd = document.getElementById("taskEnd");
    const taskCompleted = document.getElementById("taskCompleted");

    taskTitle.value = task.title;
    taskStart.value = task.start;
    taskEnd.value = task.end_date;
    taskCompleted.checked = task.completed;

    const updateButton = document.getElementById("updateButton");
    if (updateButton) {
        updateButton.style.display = "inline";
        updateButton.addEventListener("click", () => updateTask(task.id));
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
        end_date: taskEnd,
        completed: taskCompleted,
    };

    try {
        const response = await fetch(`http://localhost:8080/todo/${taskId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(task),
        });

        if (!response.ok) {
            throw new Error("Could not update task");
        }

        fetchOrders(); // タスク更新後にタスクリストを再取得して表示する
        resetForm();
    } catch (error) {
        console.error(error);
    }
}
// フォームをリセットする
function resetForm() {
    const addTaskForm = document.getElementById("addTaskForm");
    if (addTaskForm) {
        addTaskForm.reset();
    }

    const updateButton = document.getElementById("updateButton");
    if (updateButton) {
        updateButton.style.display = "none";
    }
}

// タスクを削除する
async function deleteTask(taskId) {
    try {
        const response = await fetch(`http://localhost:8080/todo/${taskId}`, {
            method: "DELETE",
        });

        if (!response.ok) {
            throw new Error("Could not delete task");
        }

        fetchOrders(); // タスク削除後にタスクリストを再取得して表示する
    } catch (error) {
        console.error(error);
    }
}

// ソート機能の実装
function fetchAndSortTasks() {
    console.log("a")
    fetch("http://localhost:8080/todo")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Could not fetch todo");
            }
            return response.json();
        })
        .then((todoJson) => {
            sortTasksByDatetime(todoJson.todoList);
            renderTodoList(todoJson);
        })
        .catch((error) => {
            console.error(error);
        });
}

// タスクのリストを開始日時でソートする関数
function sortTasksByDatetime(tasks) {
    tasks.sort(function (task1, task2) {
        return new Date(task1.start) - new Date(task2.start);
    });
}


//日付・時計機能の実装
function displayClock() {
    const now = new Date();
    const hour = now.getHours();
    const minute = now.getMinutes();
    const second = now.getSeconds();
    const clock = document.querySelector("#clock");
    clock.textContent = `${hour}:${minute}:${second}`;
}

function displayDate() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const date = now.getDate();
    const day = ["日", "月", "火", "水", "木", "金", "土"][now.getDay()];
    const dateElement = document.querySelector("#date");
    dateElement.textContent = `${year}/${month}/${date}(${day})`;
}

setInterval(displayClock, 1000);
setInterval(displayDate, 1000);
