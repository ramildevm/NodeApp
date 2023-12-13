package com.example.nodeapp.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainContent() {
    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    viewModel.onEvent(MainScreenEvent.Add)
                },
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null, tint = Color.White)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "${state.currentNode?.depth ?: "0"}: ",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                        )
                        Text(
                            modifier = Modifier.weight(1f).basicMarquee(),
                            text = "0x${state.currentNode?.name ?: ""}",
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                        )
                    }
                },
                navigationIcon = {
                    state.currentNode?.parentId?.let {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(MainScreenEvent.GoBack)
                            },
                        ) {
                            Icon(
                                Icons.Rounded.ArrowBack,
                                null,
                            )
                        }
                    }
                },
            )
        },
    ) {
        NodeContent(Modifier.padding(it), viewModel)
    }
}
