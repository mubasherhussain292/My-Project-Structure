package com.example.my.project.strucutre.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MoviesViewModel>()
    val moviesPaging = viewModel.movies.collectAsLazyPagingItems()
}