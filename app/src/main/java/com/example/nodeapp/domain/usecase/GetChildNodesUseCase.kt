package com.example.nodeapp.domain.usecase

import com.example.nodeapp.domain.repository.NodeRepository
import javax.inject.Inject

class GetChildNodesUseCase @Inject constructor(
    private val repository: NodeRepository,
) {
    operator fun invoke(id: Int?) = repository.getChildNodes(id)
}