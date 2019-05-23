package com.ynov.kotlin.rickmorty.data.manager

import com.ynov.kotlin.rickmorty.data.entity.remote.CharacterListResultRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.remote.EpisodeListResultRemonteEntity
import com.ynov.kotlin.rickmorty.data.entity.remote.ResultEpisodeRemoteEntity
import com.ynov.kotlin.rickmorty.data.entity.remote.ResultRemoteEntity
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_BASE_URL = "https://rickandmortyapi.com/"

class ApiManager {

    private val service: ApiService

    init {
        service = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    interface ApiService {
        @GET("api/character")
        fun retrieveCharacterList(): Single<CharacterListResultRemoteEntity>

        @GET("api/character/{id}")
        fun retrieveDetailCharacter(@Path("id") id: String): Single<ResultRemoteEntity>

        @GET("api/episode")
        fun retrieveEpisodeList(): Single<EpisodeListResultRemonteEntity>
    }

    fun retrieveCharacterList(): Single<List<ResultRemoteEntity>> =
        service.retrieveCharacterList().map{
            it.results
        }

    fun retrieveDetailCharacter(id: String): Single<ResultRemoteEntity> =
            service.retrieveDetailCharacter(id)

    fun retrieveEpisodeList(): Single<List<ResultEpisodeRemoteEntity>> =
            service.retrieveEpisodeList().map{
                it.results
            }
}