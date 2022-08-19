package com.jps.travelbankexercise.ui.expenseDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.jps.travelbankexercise.R
import com.jps.travelbankexercise.base.BaseFragment
import com.jps.travelbankexercise.databinding.FragmentExpenseDetailedBinding
import com.jps.travelbankexercise.di.component.FragmentComponent
import com.jps.travelbankexercise.ui.expenseList.ExpenseListVM


class ExpenseDetailedFragment : BaseFragment<ExpenseListVM>(){

    companion object {

        const val TAG = "ExpenseDetailedFragment"

        fun newInstance()= ExpenseDetailedFragment()

    }


     var binding: FragmentExpenseDetailedBinding? = null

    override fun provideLayoutId(): Int = R.layout.fragment_expense_detailed

    override fun injectDependencies(fragmentComponent: FragmentComponent) =   fragmentComponent.inject(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentExpenseDetailedBinding.inflate(inflater,container ,false)
        return binding!!.root
    }
    override fun setupView(view: View) {

    }

     @SuppressLint("SetTextI18n")
     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         viewModel.expenseItem.observe(viewLifecycleOwner, Observer {
             val mNode = it?.getIfNotHandled()
             binding?.run {
                 tvMerchantt.text = mNode?.expenseVenueTitle
                 tvExpenseCur.text = mNode?.currencyCode
                 tvExpCategory.text = mNode?.type
                 tvExpDate.text = mNode?.date.toString().substringBefore("T")
                 tvExpDescription.text = mNode?.description
                 tvExpTotal.text = "$"+ mNode?.amount.toString()

                 Glide
                     .with(ivExpDetailed.context)
                     .load(mNode?.attachments?.get(0)?.thumbnails?.list)
                     //  .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_selected))

                     .into(ivExpDetailed)

             }

         })
     }

    override fun setupObservers() {
        super.setupObservers()

    }



}