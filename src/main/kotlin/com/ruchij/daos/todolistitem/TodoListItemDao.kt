package com.ruchij.daos.todolistitem

import com.ruchij.daos.todolistitem.models.TodoListItem
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoListItemDao: CrudRepository<TodoListItem, String>