package com.example.my.project.domain.repository

import androidx.paging.PagingData
import com.example.my.project.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import com.example.my.project.domain.util.Result

interface MovieRepository {

    fun movies(pageSize: Int): Flow<PagingData<MovieEntity>>
    suspend fun getMovie(movieId: Int): Result<MovieEntity>
    suspend fun sync(): Boolean
}