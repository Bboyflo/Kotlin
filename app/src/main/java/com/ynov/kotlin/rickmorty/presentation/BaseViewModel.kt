package com.ynov.kotlin.rickmorty.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() = compositeDisposable.clear()
}