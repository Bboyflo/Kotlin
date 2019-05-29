package com.ynov.kotlin.rickmorty.data.repository

import com.ynov.kotlin.rickmorty.data.manager.ApiManager
import com.ynov.kotlin.rickmorty.data.manager.CacheManager
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.data.model.RMEpisode
import io.reactivex.Single

class DataRepository (
    private val apiManager: ApiManager,
    private val cacheManager: CacheManager
){
    fun retrieveCharacterList(): Single<List<RMCharacter>> {
        // TODO on peut mettre le return une seule fois avant le if pour otimiser
        if (cacheManager.charactersList.isNotEmpty())
            return Single.just(cacheManager.charactersList)
        else
            return apiManager.retrieveCharacterList().map {
                val charactersList = it.map { resultRemoteEntity ->
                    RMCharacter(resultRemoteEntity.id, resultRemoteEntity.name, resultRemoteEntity.gender, resultRemoteEntity.image, resultRemoteEntity.status,
                        resultRemoteEntity.species, resultRemoteEntity.type, resultRemoteEntity.location.name,
                        resultRemoteEntity.origin.name)
                }
                cacheManager.updateCharactersList(charactersList)
                charactersList
            }
    }

    fun retrieveDetailCharacter(id: String): Single<RMCharacter> {
        if (cacheManager.characterDetail.isNotEmpty() && cacheManager.getCharacterDetail(id.toInt()) != null)
            return Single.just(cacheManager.getCharacterDetail(id.toInt()))
        else{
            return apiManager.retrieveDetailCharacter(id).map {
                val characterDetail = RMCharacter(
                    it.id, it.name, it.gender, it.image, it.status, it.species, it.type, it.location.name,
                    it.origin.name
                )
                cacheManager.updateCharacterDetailList(characterDetail)
                characterDetail
            }
        }
    }

    fun retrieveEpisodeList(): Single<List<RMEpisode>> {
        if (cacheManager.episodesList.isNotEmpty())
            return Single.just(cacheManager.episodesList)
        else
            return apiManager.retrieveEpisodeList().map {
                val episodesList = it.map { resultRemoteEntity ->
                    RMEpisode(
                        resultRemoteEntity.id,
                        resultRemoteEntity.name,
                        resultRemoteEntity.air_date,
                        resultRemoteEntity.episode
                    )
                }
                cacheManager.updateEpisodesList(episodesList)
                episodesList
            }
    }
}