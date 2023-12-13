package com.example.nodeapp.domain.usecase

import com.example.nodeapp.domain.repository.SessionStateRepository
import javax.inject.Inject

class GetLastSavedNodeIdUseCase @Inject constructor(
    private val repository: SessionStateRepository,
) {
    operator fun invoke() = repository.getLastSavedNodeId()
}
