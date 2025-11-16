package com.example.myfirstandroidapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirstandroidapp.ui.products.ProductCard
import com.example.myfirstandroidapp.ui.products.ProductsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ProductsScreen() {
    val vm: ProductsViewModel = viewModel()
    val state by vm.state

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { vm.refresh() },
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading && state.all.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null && state.all.isEmpty() -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Error: ${state.error}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { vm.load() }) {
                        Text("Try Again")
                    }
                }
            }

            else -> {
                val visible = state.all.take(state.visibleCount)

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(
                        items = visible,
                        key = { _, p -> p.id }
                    ) { index, product ->

                        ProductCard(product)

                        // Auto-load next page
                        if (index == visible.lastIndex) {
                            LaunchedEffect(visible.size) {
                                vm.loadMore()
                            }
                        }
                    }

                    if (state.visibleCount < state.all.size) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }
        }
    }
}
