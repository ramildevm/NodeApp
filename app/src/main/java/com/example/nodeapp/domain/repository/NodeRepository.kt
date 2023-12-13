package com.example.nodeapp.domain.repository

import com.example.nodeapp.presentation.models.Node
import kotlinx.coroutines.flow.Flow

interface NodeRepository {
    fun getNodeById(id: Int?): Node
    fun getChildNodes(id: Int?): Flow<List<Node>>
    suspend fun insertNode(parentId: Int?)
    suspend fun deleteNode(id: Int)
}