package com.example.my.project.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.my.project.domain.entities.MovieEntity

@Entity
data class MovieDbData(
    @PrimaryKey val id: Int,
    val description: String,
    val image: String,
    val backgroundUrl: String,
    val title: String,
    val category: String
)


fun MovieDbData.toDomain() = MovieEntity(
    id = id,
    description = description,
    title = title,
    image = image,
    category = category,
    backgroundUrl = backgroundUrl
)
