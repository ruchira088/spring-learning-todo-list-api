package com.ruchij.services.todolist

import com.ruchij.daos.todolist.models.TodoList
import com.ruchij.daos.todolistitem.models.TodoListItem
import com.ruchij.daos.todolistitem.models.TodoListItemStatus

interface TodoListService {
    fun createTodoList(title: String, description: String?): TodoList

    fun getTodoListById(listId: String): TodoList

    fun updateTodoListById(listId: String, title: String?, description: String?): TodoList

    fun addTodoItemToList(listId: String, itemId: String): TodoList

    fun removeTodoItemFromList(listId: String, itemId: String): TodoList

    fun createTodoListItem(title: String, description: String?): TodoListItem

    fun getTodoListItemById(itemId: String): TodoListItem

    fun updateTodoListItemById(itemId: String, title: String?, description: String?, status: TodoListItemStatus?): TodoListItem

    fun deleteTodoListItemById(itemId: String): TodoListItem
}