package com.jps.travelbankexercise.di.module

import android.app.Application
import android.content.Context
import com.jps.travelbankexercise.MyApplication
import com.jps.travelbankexercise.data.remote.EndPoint.BASE_URL
import com.jps.travelbankexercise.data.remote.NetworkService
import com.jps.travelbankexercise.data.remote.Networking
import com.jps.travelbankexercise.di.ApplicationContext

import com.jps.travelbankexercise.utils.rx.RxSchedulerProvider
import com.jps.travelbankexercise.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @ApplicationContext
    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

   /* @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)*/

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()
}