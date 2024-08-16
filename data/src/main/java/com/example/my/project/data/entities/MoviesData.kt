package com.example.my.project.data.entities

import com.example.my.project.domain.entities.MovieEntity
import com.google.gson.annotations.SerializedName

data class MoviesData(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("backgroundUrl") val backgroundUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String
)


fun MoviesData.toDomain() = MovieEntity(
    id = id,
    title = title,
    descriptionUrl = description,
    image = image,
    category = category,
    backgroundUrl = backgroundUrl
)