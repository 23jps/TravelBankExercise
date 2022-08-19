package com.jps.travelbankexercise.ui.expenseList

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.jps.travelbankexercise.R
import com.jps.travelbankexercise.base.BaseActivity
import com.jps.travelbankexercise.di.component.ActivityComponent



class MainActivity : BaseActivity<MainVM>() {

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependency(activityComponent: ActivityComponent) = activityComponent.inject(this)

    fun checkInBackstack(fragment: Fragment) {
        val nameFragmentInBackstack = fragment.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(nameFragmentInBackstack, 0)
        val ft = manager.beginTransaction()

        if (!fragmentPopped && manager.findFragmentByTag(nameFragmentInBackstack) == null) {  // fragment not in back stack, create it.
            ft.replace(R.id.containerFragment, fragment, nameFragmentInBackstack)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.addToBackStack(nameFragmentInBackstack)
            ft.commit()
        } else if (manager.findFragmentByTag(nameFragmentInBackstack) != null) {
            val currentFragment = manager.findFragmentByTag(nameFragmentInBackstack)
            ft.replace(R.id.containerFragment, currentFragment!!, nameFragmentInBackstack)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.addToBackStack(nameFragmentInBackstack)
            ft.commit()
        }
    }


    override fun setupView(savedInstanceState: Bundle?){
        checkInBackstack(ExpensesListFragment.newInstance())
    }

}