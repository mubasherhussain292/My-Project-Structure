package com.example.my.project.data.api

import com.example.my.project.data.entities.MoviesData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("/movies?&_sort=category,id")
    suspend fun getMovies(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): List<MoviesData>

    @GET("/movies")
    suspend fun getMovies(@Query("id") movieIds: List<Int>): List<MoviesData>

    @GET("/movies/{id}")
    suspend fun getMovie(@Path("id") movieId: Int): MoviesData

    @GET("/movies")
    suspend fun search(
        @Query("q") query: String,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): List<MoviesData>

}