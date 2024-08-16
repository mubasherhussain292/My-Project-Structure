package com.example.my.project.strucutre.entities

sealed class MovieListItem {
    data class Movie(
        val id: Int,
        val imageUrl: String,
        val category: String,
    ) : MovieListItem()

    data class Separator(val category: String) : MovieListItem()
}
