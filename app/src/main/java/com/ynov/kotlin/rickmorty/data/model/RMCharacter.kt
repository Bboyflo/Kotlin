package com.ynov.kotlin.rickmorty.data.model

data class RMCharacter(
    val id: Int,
    val name: String,
    val gender: String,
    val image: String,
    val status: String,
    val species: String,
    val type: String,
    val location: String,
    val origin: String
)