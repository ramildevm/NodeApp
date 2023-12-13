package com.example.nodeapp.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Node",
    foreignKeys = [
        ForeignKey(
            entity = NodeEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("parentId"),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class NodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val parentId: Int? = null,
    val depth: Int = 0,
)
