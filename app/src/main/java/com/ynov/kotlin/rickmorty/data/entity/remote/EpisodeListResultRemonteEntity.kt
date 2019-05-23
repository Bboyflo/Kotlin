package com.ynov.kotlin.rickmorty.data.entity.remote

data class EpisodeListResultRemonteEntity(
    val info: InfoRemoteEntity,
    val results: List<ResultEpisodeRemoteEntity>
)