package com.example.my.project.data.repositories.remote

import com.example.my.project.data.api.MoviesApi
import com.example.my.project.data.entities.toDomain
import com.example.my.project.data.utils.safeApiCall
import com.example.my.project.domain.entities.MovieEntity
import com.example.my.project.domain.util.Result


class MoviesRemoteDataSource(private val moviesApi: MoviesApi) : MoviesDataSource.Remote {


    override suspend fun getMovies(page: Int, limit: Int): Result<List<MovieEntity>> = safeApiCall {
        moviesApi.getMovies(page,limit).map { it.toDomain() }
    }

    override suspend fun getMovies(movieIds: List<Int>): Result<List<MovieEntity>> = safeApiCall {
        moviesApi.getMovies(movieIds).map { it.toDomain() }
    }

    override suspend fun getMovie(movieId: Int): Result<MovieEntity> = safeApiCall {
        moviesApi.getMovie(movieId).toDomain()
    }

    override suspend fun search(query: String, page: Int, limit: Int): Result<List<MovieEntity>> = safeApiCall {
        moviesApi.search(query, page, limit).map { it.toDomain() }
    }

}