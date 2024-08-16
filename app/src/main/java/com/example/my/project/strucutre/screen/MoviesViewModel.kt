package com.example.my.project.strucutre.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.my.project.strucutre.entities.MovieListItem
import com.example.my.project.strucutre.network.usecase.GetMoviesWithSeparators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getMoviesWithSeparators: GetMoviesWithSeparators
) : ViewModel() {

    val movies: Flow<PagingData<MovieListItem>> = getMoviesWithSeparators.movies(pageSize = 90).cachedIn(viewModelScope)


}