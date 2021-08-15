package com.ruchij.services.todolist

import com.ruchij.daos.todolist.TodoListDao
import com.ruchij.daos.todolist.models.TodoList
import com.ruchij.daos.todolistitem.TodoListItemDao
import com.ruchij.daos.todolistitem.models.TodoListItem
import com.ruchij.daos.todolistitem.models.TodoListItemStatus
import com.ruchij.exceptions.ResourceNotFoundException
import com.ruchij.extensions.OptionalExtensions.fold
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.util.IdGenerator
import java.time.Clock

@Service
class TodoListServiceImpl(
    private val todoListItemDao: TodoListItemDao,
    private val todoListDao: TodoListDao,
    private val clock: Clock,
    private val idGenerator: IdGenerator
) : TodoListService {

    override fun createTodoList(title: String, description: String?): TodoList {
        val id = idGenerator.generateId()
        val timestamp = clock.instant()

        return todoListDao.save(TodoList(id.toString(), timestamp, title, description, setOf()))
    }

    override fun getTodoListById(listId: String): TodoList =
        todoListDao.findByIdOrNull(listId) ?: throw ResourceNotFoundException("Unable to find todo list with ID: $listId")

    override fun updateTodoListById(listId: String, title: String?, description: String?): TodoList {
        val todoList = getTodoListById(listId)

        val updatedDescription = description.fold({ todoList.description }) { it.ifEmpty { null }}
        val updatedTodoList = todoList.copy(title = title ?: todoList.title, description = updatedDescription)

        return todoListDao.save(updatedTodoList)
    }

    override fun addTodoItemToList(listId: String, itemId: String): TodoList {
        val todoList = getTodoListById(listId)
        val todoItem = getTodoListItemById(itemId)

        val updatedTodoList = todoList.copy(items = todoList.items.plus(todoItem))

        return todoListDao.save(updatedTodoList)
    }

    override fun removeTodoItemFromList(listId: String, itemId: String): TodoList {
        val todoList = getTodoListById(listId)

        val updatedTodoList = todoList.copy(items = todoList.items.filter { it.id != itemId }.toSet())

        return todoListDao.save(updatedTodoList)
    }

    override fun createTodoListItem(title: String, description: String?): TodoListItem {
        val id = idGenerator.generateId()
        val timestamp = clock.instant()

        val todoListItem =
            TodoListItem(id.toString(), timestamp, TodoListItemStatus.Created, title, description, null)

        todoListItemDao.save(todoListItem)

        return todoListItem
    }

    override fun getTodoListItemById(itemId: String): TodoListItem =
        todoListItemDao.findByIdOrNull(itemId) ?: throw ResourceNotFoundException("Unable to find todo list item with ID = $itemId")

    override fun updateTodoListItemById(
        itemId: String,
        title: String?,
        description: String?,
        status: TodoListItemStatus?
    ): TodoListItem {
        val existingItem = getTodoListItemById(itemId)
        val updatedDescription = description.fold({ existingItem.description }) { it.ifEmpty { null } }

        val completedAt =
            if (existingItem.status == TodoListItemStatus.Completed) existingItem.completedAt
            else if (status == TodoListItemStatus.Completed) clock.instant()
            else null

        val updatedItem =
            existingItem.copy(
                title = title ?: existingItem.title,
                status = status ?: existingItem.status,
                description = updatedDescription,
                completedAt = completedAt
            )

        return todoListItemDao.save(updatedItem)
    }

    override fun deleteTodoListItemById(itemId: String): TodoListItem {
        val todoListItem = getTodoListItemById(itemId)

        todoListItemDao.deleteById(itemId)

        return todoListItem
    }

}