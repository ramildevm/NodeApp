package com.example.nodeapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nodeapp.R
import com.example.nodeapp.presentation.models.Node
import com.example.nodeapp.presentation.theme.NodeAppTheme
import com.example.nodeapp.presentation.theme.largeMargin
import com.example.nodeapp.presentation.theme.mainMargin
import com.example.nodeapp.presentation.theme.normalMargin
import com.example.nodeapp.presentation.theme.smallMargin

@Composable
fun NodeContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    val state by viewModel.state.collectAsState()
    LazyColumn(modifier = modifier) {
        items(state.childNodes) { node ->
            NodePanel(
                node = node,
                onClick = {
                    viewModel.onEvent(MainScreenEvent.GoToNode(node))
                },
                onDelete = {
                    viewModel.onEvent(MainScreenEvent.Delete(node))
                },
            )
        }

        item {
            val text = if (state.childNodes.isEmpty()) {
                stringResource(id = R.string.no_children)
            } else {
                stringResource(id = R.string.swipe_to_delete)
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(normalMargin).alpha(0.7f),
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NodePanel(
    node: Node,
    onClick: () -> Unit,
    onDelete: () -> Unit,
) {
    key(node.hashCode()) {
        val state = rememberDismissState(
            confirmValueChange = {
                if (it == DismissValue.DismissedToStart) {
                    onDelete()
                    true
                } else {
                    false
                }
            },
        )
        SwipeToDismiss(
            state = state,
            background = {
                val color = when (state.dismissDirection) {
                    DismissDirection.EndToStart -> Color.Red
                    DismissDirection.StartToEnd -> Color.Transparent
                    null -> Color.Transparent
                }
                Box(
                    Modifier
                        .padding(largeMargin, smallMargin, mainMargin, smallMargin)
                        .clip(RoundedCornerShape(mainMargin))
                        .background(color)
                        .fillMaxSize(),
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.align(Alignment.CenterEnd),
                    )
                }
            },
            directions = setOf(DismissDirection.EndToStart),
            dismissContent = {
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                    ),
                    modifier = Modifier
                        .padding(vertical = smallMargin, horizontal = mainMargin)
                        .clickable {
                            onClick()
                        },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(vertical = smallMargin, horizontal = mainMargin),
                    ) {
                        Text(
                            "0x${node.name}",
                        )
                    }
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NodePanelPreview() {
    NodeAppTheme {
        NodePanel(
            node = Node(
                name = "0x690B9A9E9aa1C9dB991C7721a92d351Db4FaC990",
            ),
            onClick = {},
            onDelete = {},
        )
    }
}
