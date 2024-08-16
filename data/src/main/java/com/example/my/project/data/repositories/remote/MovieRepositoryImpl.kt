package com.example.my.project.data.repositories.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.my.project.domain.util.Result
import com.example.my.project.data.entities.toDomain
import com.example.my.project.domain.entities.MovieEntity
import com.example.my.project.domain.repository.MovieRepository
import com.example.my.project.domain.util.Result.Error
import com.example.my.project.domain.util.Result.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val remote: MoviesDataSource.Remote,
    private val local: MoviesDataSource.Local,
    private val remoteMediator: MovieRemoteMediator,
) : MovieRepository {


    @OptIn(ExperimentalPagingApi::class)
    override fun movies(pageSize: Int): Flow<PagingData<MovieEntity>> = Pager(
        config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
        remoteMediator = remoteMediator,
        pagingSourceFactory = { local.movies() }
    ).flow.map { pagingData ->
        pagingData.map { it.toDomain() }
    }


    override suspend fun getMovie(movieId: Int): Result<MovieEntity> {
        return when (val localResult = local.getMovie(movieId)) {
            is Success -> localResult
            is Error -> remote.getMovie(movieId)
        }
    }

    override suspend fun sync(): Boolean {
        return when (val result = local.getMovies()) {
            is Error -> false
            is Success -> {
                val movieIds = result.data.map { it.id }
                return updateLocalWithRemoteMovies(movieIds)
            }
        }
    }

    private suspend fun updateLocalWithRemoteMovies(movieIds: List<Int>): Boolean {
        return when (val remoteResult = remote.getMovies(movieIds)) {
            is Error -> false
            is Success -> {
                local.saveMovies(remoteResult.data)
                true
            }
        }
    }

}