package com.jps.travelbankexercise.ui.expenseList


import androidx.lifecycle.MutableLiveData
import com.jps.travelbankexercise.base.BaseViewModel

import com.jps.travelbankexercise.data.remote.response.TravelBankDataItem
import com.jps.travelbankexercise.data.repository.DataRepository
import com.jps.travelbankexercise.utils.Event

import com.jps.travelbankexercise.utils.Resource
import com.jps.travelbankexercise.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ExpenseListVM (
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,

    dataRepository: DataRepository,
    allExpenseList: ArrayList<TravelBankDataItem>,

    ) : BaseViewModel(schedulerProvider, compositeDisposable)  {

    val expenses: MutableLiveData<Resource<List<TravelBankDataItem>>> = MutableLiveData()
    val expenseItem: MutableLiveData<Event<TravelBankDataItem>> = MutableLiveData()



    init {
        compositeDisposable.add(

            dataRepository.fetchExpenseList()
                .subscribeOn(Schedulers.io())
                .doOnError {
                    handleNetworkError(it)
                }

                .subscribe(
                    {
                        allExpenseList.addAll(it)
                        expenses.postValue(Resource.success(it))
                    },
                    {
                        handleNetworkError(it)
                    }
                )
        )
    }


    fun getListCount(): String {
       var gTotal = 0.0
        expenses.value?.data?.forEach{
            gTotal +=it.amount ?: 0.0
        }
        return "Total: $$gTotal"
    }

    override fun onCreate() {
    }



}

