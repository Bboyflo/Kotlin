package com.ynov.kotlin.rickmorty.data.manager

import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.data.model.RMEpisode

class CacheManager {

    // TODO on préfère utiliser l'interface List pour le type et emptyList() pour initialiser une liste vide
    var charactersList : MutableList<RMCharacter> = mutableListOf()
    var episodesList : MutableList<RMEpisode> = mutableListOf()
    var characterDetail: MutableList<RMCharacter> = mutableListOf()

    fun updateCharactersList(newCharactersList: List<RMCharacter>){
        charactersList.clear()
        charactersList.addAll(newCharactersList)
    }

    fun updateEpisodesList(newEpisodesList: List<RMEpisode>){
        episodesList.clear()
        episodesList.addAll(newEpisodesList)
    }

    fun updateCharacterDetailList(newCharacterDetail: RMCharacter) = characterDetail.add(newCharacterDetail)

    fun getCharacterDetail(id: Int): RMCharacter? = characterDetail.find { it.id == id }
}