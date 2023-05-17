package com.example.demo.controller;

import com.example.demo.controller.response.TodoResponse;
import com.example.demo.model.TodoModel;
import com.example.demo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService service;

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponse getAllTodo() {
        List<TodoModel> List = service.getAllTodo();
        return new TodoResponse(List);
    }

    public TodoController(TodoService service) {
        this.service = service;
    }
}

    /*新規入力フォームの表示
    @GetMapping("/form")
    public String form(@ModelAttribute TestForm testForm) {
        return "sample/form";
    }

    //新規入力データの保存
    @PostMapping("/form")
    public String create(TestForm testForm) {
        String sql = "INSERT INTO test_table(name, old) VALUES(?, ?);";
        jdbcTemplate.update(sql, testForm.getName(), testForm.getOld());
        return "redirect:/sample";
    }

    //編集フォームの表示
    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute TestForm testForm, @PathVariable int id) {
        String sql = "SELECT * FROM test_table WHERE id = " + id;
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        testForm.setId((int)map.get("id"));
        testForm.setName((String)map.get("name"));
        testForm.setOld((int)map.get("old"));
        return "sample/edit";
    }

    //編集データの保存
    @PostMapping("/edit/{id}")
    public String update(TestForm testForm, @PathVariable int id) {
        String sql = "UPDATE test_table SET name = ?, old = ? WHERE id = " + id;
        jdbcTemplate.update(sql, testForm.getName(), testForm.getOld());
        return "redirect:/sample";
    }

    //データの削除
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        String sql = "DELETE from test_table WHERE id = " + id;
        jdbcTemplate.update(sql);
        return "redirect:/sample";
    }
}*/