package com.example.my.project.strucutre.mapper

import com.example.my.project.domain.entities.MovieEntity
import com.example.my.project.strucutre.entities.MovieListItem


fun MovieEntity.toPresentation() = MovieListItem.Movie(
    id = id,
    imageUrl = image,
    category = category
)

fun MovieEntity.toMovieListItem(): MovieListItem = this.toPresentation()