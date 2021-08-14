package com.ruchij.services.todolist

import com.ruchij.daos.todolistitem.models.TodoListItem
import com.ruchij.daos.todolistitem.models.TodoListItemStatus

interface TodoListService {
    fun create(title: String, description: String?): TodoListItem

    fun getById(id: String): TodoListItem

    fun updateById(id: String, title: String?, description: String?, status: TodoListItemStatus?): TodoListItem
}