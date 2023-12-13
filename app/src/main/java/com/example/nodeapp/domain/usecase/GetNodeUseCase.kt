package com.example.nodeapp.domain.usecase

import com.example.nodeapp.domain.repository.NodeRepository
import javax.inject.Inject

class GetNodeUseCase @Inject constructor(
    private val repository: NodeRepository,
) {
    operator fun invoke(id: Int?) = repository.getNodeById(id)
}
