package com.ruchij.services.todolist

import com.ruchij.daos.todolistitem.TodoListItemDao
import com.ruchij.daos.todolistitem.models.TodoListItem
import com.ruchij.daos.todolistitem.models.TodoListItemStatus
import org.springframework.util.IdGenerator
import java.time.Clock

class TodoListServiceImpl(
    private val todoListItemDao: TodoListItemDao,
    private val clock: Clock,
    private val idGenerator: IdGenerator
) : TodoListService {

    override fun create(title: String, description: String?): TodoListItem {
        val id = idGenerator.generateId()
        val timestamp = clock.instant()

        val todoListItem =
            TodoListItem(id.toString(), timestamp, TodoListItemStatus.Created, title, description, null)

        todoListItemDao.save(todoListItem)

        return todoListItem
    }

}