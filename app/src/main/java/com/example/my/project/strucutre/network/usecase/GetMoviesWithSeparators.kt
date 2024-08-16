package com.example.my.project.strucutre.network.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.my.project.domain.repository.MovieRepository
import com.example.my.project.strucutre.entities.MovieListItem
import com.example.my.project.strucutre.mapper.toPresentation
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMoviesWithSeparators @Inject constructor(
    private val movieRepository: MovieRepository,
    private val insertSeparatorIntoPagingData: InsertSeparatorIntoPagingData
) {

    fun movies(pageSize: Int): Flow<PagingData<MovieListItem>> = movieRepository.movies(pageSize).map {
        val pagingData: PagingData<MovieListItem.Movie> = it.map { movie -> movie.toPresentation() }
        insertSeparatorIntoPagingData.insert(pagingData)
    }

}