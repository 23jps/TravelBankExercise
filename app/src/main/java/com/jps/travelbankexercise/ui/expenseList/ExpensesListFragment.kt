package com.jps.travelbankexercise.ui.expenseList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.jps.travelbankexercise.R
import com.jps.travelbankexercise.base.BaseFragment
import com.jps.travelbankexercise.data.remote.response.TravelBankDataItem

import com.jps.travelbankexercise.databinding.FragmentExpensesListBinding
import com.jps.travelbankexercise.di.component.FragmentComponent
import com.jps.travelbankexercise.ui.expenseDetail.ExpenseDetailedFragment
import com.jps.travelbankexercise.utils.Event
import kotlinx.android.synthetic.main.fragment_expenses_list.*


class ExpensesListFragment : BaseFragment<ExpenseListVM>() {

    companion object {
        const val TAG = "ExpensesListFragment"
        fun newInstance() = ExpensesListFragment()

    }

    var binding: FragmentExpensesListBinding? = null

   private val expensesAdapter: ExpensesAdapter by lazy{
       ExpensesAdapter()
   }

    override fun provideLayoutId(): Int = R.layout.fragment_expenses_list


    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentExpensesListBinding.inflate(inflater,container ,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_expenses.apply {
            adapter = expensesAdapter
        }
        expensesAdapter.mCallback = object :OnExpenseCallbacks{
            override fun onItemClick(mNode: TravelBankDataItem?) {
                mNode?.let{
                    Log.e(TAG, "onItemClick: ${it.expenseVenueTitle}")
                    viewModel.expenseItem.value = Event(it)

                    (activity as? MainActivity)?.checkInBackstack(ExpenseDetailedFragment.newInstance())
                }
            }
        }

    }


    override fun setupView(view: View) {
    }


    override fun setupObservers() {
        super.setupObservers()
        activity?.let {
            viewModel.expenses.observe(it, Observer {
                it.data?.run { expensesAdapter.submitList(this.toMutableList())
                    tv_total_expenses.text = viewModel.getListCount()
                }
            })
        }

    }

}