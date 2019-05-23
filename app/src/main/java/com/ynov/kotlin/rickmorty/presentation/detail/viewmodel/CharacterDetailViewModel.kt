package com.ynov.kotlin.rickmorty.presentation.detail.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.BaseViewModel
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterDetailViewModel(val id:String) : BaseViewModel() {

    val characterDetailLiveData: MutableLiveData<RMCharacter> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        retrieveDetailCharacter()
    }

    @SuppressLint("CheckResult")
    fun retrieveDetailCharacter() {
        RMApplication.app.dataRepository.retrieveDetailCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onSuccess = {
                    characterDetailLiveData.postValue(it)
                },
                onError = {
                    errorLiveData.postValue(it)
                }
            )
    }
}