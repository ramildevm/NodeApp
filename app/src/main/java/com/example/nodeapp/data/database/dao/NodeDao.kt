package com.example.nodeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.nodeapp.data.database.entities.NodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {
    @Query("SELECT * FROM Node WHERE id=:id LIMIT 1")
    fun getNodeById(id: Int): NodeEntity

    @Query("SELECT * FROM Node ")
    fun getNodes(): List<NodeEntity>

    @Query("SELECT MAX(id) FROM Node")
    fun getNextNodeId(): Int

    @Query("SELECT * FROM Node WHERE parentId=:parentId")
    fun getNodesByParentId(parentId: Int): Flow<List<NodeEntity>>

    @Upsert
    suspend fun insertNode(node: NodeEntity)

    @Delete
    suspend fun deleteNode(node: NodeEntity)
}
