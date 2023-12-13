package com.example.nodeapp.domain.usecase

import com.example.nodeapp.domain.repository.SessionStateRepository
import javax.inject.Inject

class SaveCurrentNodeIdUseCase @Inject constructor(
    private val repository: SessionStateRepository,
) {
    suspend operator fun invoke(id: Int?) = repository.saveCurrentNodeId(id)
}
