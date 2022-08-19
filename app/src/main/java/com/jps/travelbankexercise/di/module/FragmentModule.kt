package com.jps.travelbankexercise.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jps.travelbankexercise.base.BaseFragment
import com.jps.travelbankexercise.data.repository.DataRepository
import com.jps.travelbankexercise.ui.expenseList.ExpenseListVM

import com.jps.travelbankexercise.utils.ViewModelProvideFactory
import com.jps.travelbankexercise.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)








    @Provides
    fun provideExpenseViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,

        dataRepository: DataRepository,
    ): ExpenseListVM = ViewModelProvider(
        fragment.activity!!, ViewModelProvideFactory(ExpenseListVM::class) {
            ExpenseListVM(
                schedulerProvider, compositeDisposable, dataRepository,
                ArrayList()
            )
        }).get(ExpenseListVM::class.java)


}