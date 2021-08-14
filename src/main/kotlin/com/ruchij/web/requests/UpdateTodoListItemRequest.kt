package com.ruchij.web.requests

import com.ruchij.daos.todolistitem.models.TodoListItemStatus

data class UpdateTodoListItemRequest(val title: String?, val status: TodoListItemStatus?, val description: String?)
