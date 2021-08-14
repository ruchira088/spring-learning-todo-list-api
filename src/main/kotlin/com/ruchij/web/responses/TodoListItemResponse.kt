package com.ruchij.web.responses

import com.ruchij.daos.todolistitem.models.TodoListItem
import com.ruchij.daos.todolistitem.models.TodoListItemStatus
import java.util.*

data class TodoListItemResponse(
    val id: String,
    val createdAt: Date,
    val status: TodoListItemStatus,
    val title: String,
    val description: String?,
    val completedAt: Date?
) {
    companion object {
        fun from(todoListItem: TodoListItem): TodoListItemResponse =
            TodoListItemResponse(
                todoListItem.id,
                Date(todoListItem.createdAt.toEpochMilli()),
                todoListItem.status,
                todoListItem.title,
                todoListItem.description,
                todoListItem.completedAt?.let { instant -> Date(instant.toEpochMilli())  }
            )
    }
}
