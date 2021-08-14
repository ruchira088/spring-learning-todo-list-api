package com.ruchij.daos.todolistitem.models

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class TodoListItem(
    @Id val id: String,
    val createdAt: Instant,
    val status: TodoListItemStatus,
    val title: String,
    val description: String?,
    val completedAt: Instant?
)
