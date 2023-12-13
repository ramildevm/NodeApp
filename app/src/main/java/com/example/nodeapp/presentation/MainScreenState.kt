package com.example.nodeapp.presentation

import com.example.nodeapp.presentation.models.Node

data class MainScreenState(
    val currentNode: Node? = null,
    val childNodes: List<Node> = emptyList()
)
