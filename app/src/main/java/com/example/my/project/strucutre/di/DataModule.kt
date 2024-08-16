package com.example.my.project.strucutre.di

import com.example.my.project.data.api.MoviesApi
import com.example.my.project.data.repositories.remote.MovieRemoteMediator
import com.example.my.project.data.repositories.remote.MovieRepositoryImpl
import com.example.my.project.data.repositories.remote.MoviesDataSource
import com.example.my.project.data.repositories.remote.MoviesRemoteDataSource
import com.example.my.project.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemote: MoviesDataSource.Remote,
        movieLocal: MoviesDataSource.Local,
        movieRemoteMediator: MovieRemoteMediator,
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemote, movieLocal,movieRemoteMediator)
    }

    @Provides
    @Singleton
    fun provideMovieRemoveDataSource(moviesApi: MoviesApi): MoviesDataSource.Remote {
        return MoviesRemoteDataSource(moviesApi)
    }


    @Provides
    @Singleton
    fun provideMovieMediator(
        movieLocalDataSource: MoviesDataSource.Local,
        movieRemoteDataSource: MoviesDataSource.Remote
    ): MovieRemoteMediator {
        return MovieRemoteMediator(movieLocalDataSource, movieRemoteDataSource)
    }

}