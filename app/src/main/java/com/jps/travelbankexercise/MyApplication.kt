package com.jps.travelbankexercise

import android.app.Application
import com.jps.travelbankexercise.data.remote.NetworkService
import com.jps.travelbankexercise.di.component.ApplicationComponent
import com.jps.travelbankexercise.di.component.DaggerApplicationComponent
import com.jps.travelbankexercise.di.module.ApplicationModule
import javax.inject.Inject

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var networkService: NetworkService


    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }

    private fun getDependencies() {

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}