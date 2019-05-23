package com.ynov.kotlin.rickmorty.data.entity.remote

data class CharacterListResultRemoteEntity(
    val info: InfoRemoteEntity,
    val results: List<ResultRemoteEntity>
)