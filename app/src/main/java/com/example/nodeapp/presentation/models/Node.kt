package com.example.nodeapp.presentation.models

data class Node(
    val id: Int = 0,
    val name: String = "",
    val parentId: Int? = null,
    val depth: Int = 0,
)
