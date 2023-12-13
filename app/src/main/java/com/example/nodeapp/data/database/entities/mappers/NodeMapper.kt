package com.example.nodeapp.data.database.entities.mappers

import com.example.nodeapp.data.database.entities.NodeEntity
import com.example.nodeapp.presentation.models.Node

fun NodeEntity.toModel(): Node {
    return Node(
        id = this.id,
        name = this.name,
        parentId = this.parentId,
        depth = this.depth,
    )
}
fun Node.toEntity(): NodeEntity {
    return NodeEntity(
        id = this.id,
        name = this.name,
        parentId = this.parentId,
        depth = this.depth,
    )
}
