package com.jps.travelbankexercise.di.component



import com.jps.travelbankexercise.ui.expenseList.MainActivity
import com.jps.travelbankexercise.di.ActivityScope
import com.jps.travelbankexercise.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}
