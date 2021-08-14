package com.ruchij.services.todolist

import com.ruchij.daos.todolistitem.TodoListItemDao
import com.ruchij.daos.todolistitem.models.TodoListItem
import com.ruchij.daos.todolistitem.models.TodoListItemStatus
import com.ruchij.exceptions.ResourceNotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.IdGenerator
import java.time.Clock

@Service
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

    override fun getById(id: String): TodoListItem =
        todoListItemDao.findById(id).orElseThrow {
            ResourceNotFoundException("Unable to find todo list item with ID = $id")
        }

    override fun updateById(
        id: String,
        title: String?,
        description: String?,
        status: TodoListItemStatus?
    ): TodoListItem {
        val existingItem = getById(id)
        val updatedDescription = description?.let { value -> value.ifEmpty { null } } ?: existingItem.description

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

}