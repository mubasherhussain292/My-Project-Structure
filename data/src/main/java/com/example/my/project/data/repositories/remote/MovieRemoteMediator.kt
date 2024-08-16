package com.example.my.project.data.repositories.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.my.project.data.entities.MovieDbData
import com.example.my.project.data.entities.MovieRemoteKeyDbData
import com.example.my.project.domain.util.Result

private const val MOVIE_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val local: MoviesDataSource.Local,
    private val remote: MoviesDataSource.Remote
) : RemoteMediator<Int, MovieDbData>() {


    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieDbData>): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.APPEND -> MOVIE_STARTING_PAGE_INDEX
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.REFRESH -> 2
        }

        if (state.isEmpty() && page == 2) return MediatorResult.Success(endOfPaginationReached = false)


        when (val result = remote.getMovies(page, state.config.pageSize)) {
            is Result.Success -> {

                val movies = result.data

                val endOfPaginationReached = movies.isEmpty()

                val prevPage = if (page == MOVIE_STARTING_PAGE_INDEX) null else page - 1
                val nextPage = if (endOfPaginationReached) null else page + 1

                val key = MovieRemoteKeyDbData(prevPage = prevPage, nextPage = nextPage)

                local.saveMovies(movies)
                local.saveRemoteKey(key)

                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

            }

            is Result.Error -> {
                return MediatorResult.Error(result.error)
            }
        }

    }
}