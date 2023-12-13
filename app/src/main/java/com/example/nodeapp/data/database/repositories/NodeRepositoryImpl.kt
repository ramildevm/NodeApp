package com.example.nodeapp.data.database.repositories

import com.example.nodeapp.data.database.dao.NodeDao
import com.example.nodeapp.data.database.entities.NodeEntity
import com.example.nodeapp.data.database.entities.mappers.toModel
import com.example.nodeapp.data.helpers.NodeHelper
import com.example.nodeapp.domain.repository.NodeRepository
import com.example.nodeapp.presentation.models.Node
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NodeRepositoryImpl @Inject constructor(
    private val nodeDao: NodeDao,
) : NodeRepository {
    override fun getNodeById(id: Int?): Node {
        val node = nodeDao.getNodeById(id ?: 1)
        return node.toModel()
    }

    override fun getChildNodes(id: Int?): Flow<List<Node>> {
        return nodeDao.getNodesByParentId(id ?: 1).map { list ->
            list.map {
                it.toModel()
            }
        }
    }

    override suspend fun insertNode(parentId: Int?) {
        val parentNode = nodeDao.getNodeById(parentId ?: 1)
        val nextId = nodeDao.getNextNodeId() + 1
        nodeDao.insertNode(
            NodeEntity(
                id = 0,
                name = NodeHelper.generateNodeName(parentNode, nextId),
                parentId = parentId,
                depth = parentNode.depth + 1,
            ),
        )
    }

    override suspend fun deleteNode(id: Int) {
        val node = nodeDao.getNodeById(id)
        nodeDao.deleteNode(node)
    }
}
