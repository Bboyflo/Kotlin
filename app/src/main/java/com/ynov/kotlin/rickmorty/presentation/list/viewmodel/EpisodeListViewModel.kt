package com.ynov.kotlin.rickmorty.presentation.list.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.ynov.kotlin.rickmorty.data.model.RMEpisode
import com.ynov.kotlin.rickmorty.presentation.BaseViewModel
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class EpisodeListViewModel : BaseViewModel() {

    val episodeListLiveData: MutableLiveData<List<RMEpisode>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        retrieveEpisodeList()
    }

    @SuppressLint("CheckResult")
    fun retrieveEpisodeList(){
        RMApplication.app.dataRepository.retrieveEpisodeList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy (
                onSuccess = {
                    episodeListLiveData.postValue(it)
                },
                onError = {
                    errorLiveData.postValue(it)
                }
            )
    }
}