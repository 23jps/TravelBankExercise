package com.jps.travelbankexercise.di.component

import com.jps.travelbankexercise.di.FragmentScope
import com.jps.travelbankexercise.di.module.FragmentModule
import com.jps.travelbankexercise.ui.expenseDetail.ExpenseDetailedFragment
import com.jps.travelbankexercise.ui.expenseList.ExpensesListFragment

import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: ExpensesListFragment)
    fun inject(fragment: ExpenseDetailedFragment)

}