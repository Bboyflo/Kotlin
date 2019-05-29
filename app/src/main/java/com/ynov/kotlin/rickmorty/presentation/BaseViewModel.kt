package com.ynov.kotlin.rickmorty.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    // TODO ça part d'une bonne initiative mais si vous ajoutez pas les Disposables des subscribeBy()
    //  dans le compositeDisposable à chaque appel il y a aucun intérêt à le faire
    //  du coup il doit être en protected et pas en private pour pouvoir y accéder dans les classes fille
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() = compositeDisposable.clear()
}