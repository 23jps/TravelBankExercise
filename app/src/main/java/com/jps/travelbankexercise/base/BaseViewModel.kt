package com.jps.travelbankexercise.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.jps.travelbankexercise.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable : CompositeDisposable, //because it can only access by the class which inherit the base view model

    ) : ViewModel() {
    val messageStringId = MutableLiveData<Int>()
    val messageString = MutableLiveData<String>()
    protected fun handleNetworkError(err: Throwable){
        err.message
    }
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    abstract fun onCreate()
}
