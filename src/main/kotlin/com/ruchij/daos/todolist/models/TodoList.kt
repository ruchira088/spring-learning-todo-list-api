package com.ruchij.daos.todolist.models

import com.ruchij.daos.todolistitem.models.TodoListItem
import java.time.Instant
import javax.persistence.*

@Entity(name = "todo_list")
data class TodoList(
    @Id val id: String,
    val createdAt: Instant,
    val title: String,
    val description: String?,
    @ManyToMany
    @JoinTable(
        name = "todo_list_item_mappings",
        joinColumns = [JoinColumn(name = "todo_list_id")],
        inverseJoinColumns = [JoinColumn(name = "todo_list_item_id")]
    )
    val items: Set<TodoListItem>
)
