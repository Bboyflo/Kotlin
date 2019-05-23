package com.ynov.kotlin.rickmorty.presentation.list.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.BaseViewModel
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : BaseViewModel() {

    val characterListLiveData: MutableLiveData<List<RMCharacter>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        retrieveCharacterList()
    }

    @SuppressLint("CheckResult")
    fun retrieveCharacterList(){
        RMApplication.app.dataRepository.retrieveCharacterList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribeBy (
                onSuccess = {
                    characterListLiveData.postValue(it)
                },
                onError = {
                    errorLiveData.postValue(it)
                }
            )
    }
}