package com.ynov.kotlin.rickmorty.data.entity.remote

data class InfoRemoteEntity(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)