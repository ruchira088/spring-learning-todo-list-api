package com.ruchij.web.controllers

import com.ruchij.services.todolist.TodoListService
import com.ruchij.web.requests.CreateTodoListItemRequest
import com.ruchij.web.responses.TodoListItemResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping

@Controller("/todo-list")
class TodoListController(private val todoListService: TodoListService) {

    @PostMapping
    fun create(createTodoListItemRequest: CreateTodoListItemRequest): TodoListItemResponse {
        val todoListItem =
            todoListService.create(createTodoListItemRequest.title, createTodoListItemRequest.description)

        return TodoListItemResponse.from(todoListItem)
    }

}