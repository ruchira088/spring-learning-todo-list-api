package com.ruchij.daos.todolist

import com.ruchij.daos.todolist.models.TodoList
import org.springframework.data.repository.CrudRepository

interface TodoListDao: CrudRepository<TodoList, String>