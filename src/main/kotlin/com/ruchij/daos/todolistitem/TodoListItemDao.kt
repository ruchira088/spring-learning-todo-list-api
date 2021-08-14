package com.ruchij.daos.todolistitem

import com.ruchij.daos.todolistitem.models.TodoListItem
import org.springframework.data.repository.CrudRepository

interface TodoListItemDao: CrudRepository<TodoListItem, String>