package com.ruchij.web.responses

import com.ruchij.daos.todolist.models.TodoList
import java.util.*

data class TodoListResponse(
    val id: String,
    val createdAt: Date,
    val title: String,
    val description: String?,
    val todoItems: Set<TodoListItemResponse>
) {
    companion object {
        fun from(todoList: TodoList): TodoListResponse =
            TodoListResponse(
                todoList.id,
                Date(todoList.createdAt.toEpochMilli()),
                todoList.title,
                todoList.description,
                todoList.items.map { TodoListItemResponse.from(it) }.toSet()
            )
    }
}
