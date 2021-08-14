package com.ruchij.web.controllers

import com.ruchij.services.todolist.TodoListService
import com.ruchij.web.requests.CreateTodoListItemRequest
import com.ruchij.web.responses.TodoListItemResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo-list")
class TodoListController(private val todoListService: TodoListService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody createTodoListItemRequest: CreateTodoListItemRequest): TodoListItemResponse {
        val todoListItem =
            todoListService.create(createTodoListItemRequest.title, createTodoListItemRequest.description)

        return TodoListItemResponse.from(todoListItem)
    }

    @GetMapping("/{todo-list-item-id}")
    fun getById(@PathVariable("todo-list-item-id") todoListItemId: String): TodoListItemResponse =
        TodoListItemResponse.from(todoListService.getById(todoListItemId))

}