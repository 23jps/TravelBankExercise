package com.jps.travelbankexercise.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jps.travelbankexercise.base.BaseActivity

import com.jps.travelbankexercise.di.ActivityContext
import com.jps.travelbankexercise.ui.expenseList.MainVM

import com.jps.travelbankexercise.utils.ViewModelProvideFactory
import com.jps.travelbankexercise.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)


    @Provides
    fun provideMainVM(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,

      //  allExpenseList: ArrayList<TravelBankDataItem>

    ): MainVM =
        ViewModelProvider(activity, ViewModelProvideFactory( MainVM::class){
            MainVM(schedulerProvider,
                compositeDisposable)
        }).get(MainVM::class.java)



}