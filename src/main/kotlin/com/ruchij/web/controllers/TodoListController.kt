package com.ruchij.web.controllers

import com.ruchij.services.todolist.TodoListService
import com.ruchij.web.requests.CreateTodoListItemRequest
import com.ruchij.web.requests.CreateTodoListRequest
import com.ruchij.web.requests.UpdateTodoListItemRequest
import com.ruchij.web.responses.TodoListItemResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo-list")
class TodoListController(private val todoListService: TodoListService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/list")
    fun createTodoList(createTodoListRequest: CreateTodoListRequest) {}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/item")
    fun createTodoListItem(@RequestBody createTodoListItemRequest: CreateTodoListItemRequest): TodoListItemResponse {
        val todoListItem =
            todoListService.createTodoListItem(createTodoListItemRequest.title, createTodoListItemRequest.description)

        return TodoListItemResponse.from(todoListItem)
    }

    @GetMapping("/item/{item-id}")
    fun getByTodoListItemId(@PathVariable("item-id") todoListItemId: String): TodoListItemResponse =
        TodoListItemResponse.from(todoListService.getTodoListItemById(todoListItemId))

    @PutMapping("/item/{item-id}")
    fun updateByTodoListItemId(
        @PathVariable("item-id") todoListItemId: String,
        @RequestBody updateTodoListItemRequest: UpdateTodoListItemRequest
    ): TodoListItemResponse =
        TodoListItemResponse.from(
            todoListService.updateTodoListItemById(
                todoListItemId,
                updateTodoListItemRequest.title,
                updateTodoListItemRequest.description,
                updateTodoListItemRequest.status
            )
        )

}