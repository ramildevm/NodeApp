package com.example.nodeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nodeapp.domain.usecase.DeleteChildNodeUseCase
import com.example.nodeapp.domain.usecase.GetChildNodesUseCase
import com.example.nodeapp.domain.usecase.GetLastSavedNodeIdUseCase
import com.example.nodeapp.domain.usecase.GetNodeUseCase
import com.example.nodeapp.domain.usecase.InsertChildNodeUseCase
import com.example.nodeapp.domain.usecase.SaveCurrentNodeIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLastSavedNodeId: GetLastSavedNodeIdUseCase,
    private val getNode: GetNodeUseCase,
    private val getChildNodes: GetChildNodesUseCase,
    private val insertChildNode: InsertChildNodeUseCase,
    private val deleteChildNode: DeleteChildNodeUseCase,
    private val saveCurrentNodeId: SaveCurrentNodeIdUseCase,
) : ViewModel() {
    private val _node = MutableStateFlow(getNode(getLastSavedNodeId()))

    @OptIn(ExperimentalCoroutinesApi::class)
    private val children = _node.flatMapLatest {
        getChildNodes(it.id)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())
    private val _state = MutableStateFlow(MainScreenState())
    val state = combine(_state, _node, children) { state, node, children ->
        state.copy(
            childNodes = children,
            currentNode = node,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), MainScreenState())

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            MainScreenEvent.Add -> {
                viewModelScope.launch {
                    insertChildNode(state.value.currentNode?.id)
                }
            }
            MainScreenEvent.GoBack -> {
                _node.value = getNode(state.value.currentNode?.parentId).also {
                    viewModelScope.launch {
                        saveCurrentNodeId(state.value.currentNode?.parentId)
                    }
                }
            }
            is MainScreenEvent.GoToNode -> {
                _node.value = getNode(event.node.id).also {
                    viewModelScope.launch {
                        saveCurrentNodeId(event.node.id)
                    }
                }
            }
            is MainScreenEvent.Delete -> {
                viewModelScope.launch {
                    deleteChildNode(event.node.id)
                }
            }
        }
    }
}
