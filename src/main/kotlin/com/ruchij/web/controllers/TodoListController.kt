package com.ruchij.web.controllers

import com.ruchij.services.todolist.TodoListService
import com.ruchij.web.requests.CreateTodoListItemRequest
import com.ruchij.web.requests.UpdateTodoListItemRequest
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

    @GetMapping("/{item-id}")
    fun getById(@PathVariable("item-id") todoListItemId: String): TodoListItemResponse =
        TodoListItemResponse.from(todoListService.getById(todoListItemId))

    @PutMapping("/{item-id}")
    fun updateById(
        @PathVariable("item-id") todoListItemId: String,
        @RequestBody updateTodoListItemRequest: UpdateTodoListItemRequest
    ): TodoListItemResponse =
        TodoListItemResponse.from(
            todoListService.updateById(
                todoListItemId,
                updateTodoListItemRequest.title,
                updateTodoListItemRequest.description,
                updateTodoListItemRequest.status
            )
        )

}