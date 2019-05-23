package com.ynov.kotlin.rickmorty.data.entity.remote

data class ResultRemoteEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationRemoteEntity,
    val name: String,
    val origin: OriginRemoteEntity,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)