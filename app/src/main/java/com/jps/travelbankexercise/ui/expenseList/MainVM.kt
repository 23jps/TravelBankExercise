package com.jps.travelbankexercise.ui.expenseList

import androidx.lifecycle.MutableLiveData
import com.jps.travelbankexercise.base.BaseViewModel
import com.jps.travelbankexercise.utils.Event
import com.jps.travelbankexercise.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable



class MainVM (
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,

) : BaseViewModel(schedulerProvider, compositeDisposable) {

    override fun onCreate() {
    }

}


