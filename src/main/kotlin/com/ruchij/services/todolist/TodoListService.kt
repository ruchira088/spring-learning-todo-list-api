package com.ruchij.services.todolist

import com.ruchij.daos.todolistitem.models.TodoListItem
import org.springframework.stereotype.Service

interface TodoListService {
    fun create(title: String, description: String?): TodoListItem

    fun getById(id: String): TodoListItem
}