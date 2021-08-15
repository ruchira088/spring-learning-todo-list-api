package com.ruchij.web.controllers

import com.ruchij.services.todolist.TodoListService
import com.ruchij.web.requests.CreateTodoListItemRequest
import com.ruchij.web.requests.CreateTodoListRequest
import com.ruchij.web.requests.UpdateTodoListItemRequest
import com.ruchij.web.responses.TodoListItemResponse
import com.ruchij.web.responses.TodoListResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo-list")
class TodoListController(private val todoListService: TodoListService) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/list")
    fun createTodoList(@RequestBody createTodoListRequest: CreateTodoListRequest): TodoListResponse =
        TodoListResponse.from(todoListService.createTodoList(createTodoListRequest.title, createTodoListRequest.description))

    @GetMapping("/list/{list-id}")
    fun getTodoListById(@PathVariable("list-id") listId: String): TodoListResponse =
        TodoListResponse.from(todoListService.getTodoListById(listId))

    @PutMapping("/list/{list-id}/items/{item-id}")
    fun addTodoItemToList(@PathVariable("list-id") listId: String, @PathVariable("item-id") itemId: String): TodoListResponse =
        TodoListResponse.from(todoListService.addTodoItemToList(listId, itemId))

    @DeleteMapping("/list/{list-id}/items/{item-id}")
    fun deleteTodoItemFromList(@PathVariable("list-id") listId: String, @PathVariable("item-id") itemId: String): TodoListResponse =
        TodoListResponse.from(todoListService.removeTodoItemFromList(listId, itemId))

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/item")
    fun createTodoListItem(@RequestBody createTodoListItemRequest: CreateTodoListItemRequest): TodoListItemResponse {
        val todoListItem =
            todoListService.createTodoListItem(createTodoListItemRequest.title, createTodoListItemRequest.description)

        return TodoListItemResponse.from(todoListItem)
    }

    @GetMapping("/item/{item-id}")
    fun getTodoListItemById(@PathVariable("item-id") itemId: String): TodoListItemResponse =
        TodoListItemResponse.from(todoListService.getTodoListItemById(itemId))

    @PutMapping("/item/{item-id}")
    fun updateTodoListItemById(
        @PathVariable("item-id") itemId: String,
        @RequestBody updateTodoListItemRequest: UpdateTodoListItemRequest
    ): TodoListItemResponse =
        TodoListItemResponse.from(
            todoListService.updateTodoListItemById(
                itemId,
                updateTodoListItemRequest.title,
                updateTodoListItemRequest.description,
                updateTodoListItemRequest.status
            )
        )

}