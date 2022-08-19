package com.jps.travelbankexercise.base

import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jps.travelbankexercise.MyApplication

import com.jps.travelbankexercise.di.component.ActivityComponent
import com.jps.travelbankexercise.di.component.DaggerActivityComponent
import com.jps.travelbankexercise.di.module.ActivityModule
import dagger.internal.DaggerCollections
import javax.inject.Inject

abstract class BaseActivity<VM: BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM //this view model depends on class which we use, to achieve this we use t as genaric
    // here viewmodel is type of veriable parameter, here VM is something which extend something

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    protected open fun setupObservers(){

        viewModel.messageStringId.observe(this, Observer{

            showMessage(it)
        })
        viewModel.messageString.observe(this, Observer{

            showMessage(it)
        })
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
        .builder()
        .applicationComponent((application as MyApplication).applicationComponent)
        .activityModule(ActivityModule(this))
        .build()

    fun showMessage(message: String) = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))
    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    @LayoutRes
    protected abstract fun provideLayoutId():Int

    protected abstract fun setupView(savedInstanceState: Bundle?) //this will get call back when onCreate is created

    protected abstract fun injectDependency(activityComponent: ActivityComponent)

}