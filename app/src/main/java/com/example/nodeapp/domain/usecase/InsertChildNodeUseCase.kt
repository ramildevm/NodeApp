package com.example.nodeapp.domain.usecase

import com.example.nodeapp.domain.repository.NodeRepository
import javax.inject.Inject

class InsertChildNodeUseCase @Inject constructor(
    private val repository: NodeRepository,
) {
    suspend operator fun invoke(parentId: Int?) = repository.insertNode(parentId)
}