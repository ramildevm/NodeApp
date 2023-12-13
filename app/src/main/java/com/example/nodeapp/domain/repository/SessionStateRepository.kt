package com.example.nodeapp.domain.repository

interface SessionStateRepository {
    fun getLastSavedNodeId(): Int
    suspend fun saveCurrentNodeId(id: Int?)
}