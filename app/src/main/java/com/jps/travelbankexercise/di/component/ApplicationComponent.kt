package com.jps.travelbankexercise.di.component

import android.content.Context
import com.jps.travelbankexercise.MyApplication
import com.jps.travelbankexercise.data.remote.NetworkService
import com.jps.travelbankexercise.di.ApplicationContext
import com.jps.travelbankexercise.di.module.ApplicationModule

import com.jps.travelbankexercise.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    @ApplicationContext
    fun getContext() : Context

    fun getCompositeDisposable(): CompositeDisposable
    fun getNetworkService(): NetworkService
    fun getSchedulerProvider(): SchedulerProvider


}