package com.example.nodeapp.presentation

import com.example.nodeapp.presentation.models.Node

sealed class MainScreenEvent {
    object Add : MainScreenEvent()
    data class GoToNode(val node: Node) : MainScreenEvent()
    data class Delete(val node: Node) : MainScreenEvent()
    object GoBack : MainScreenEvent()
}
