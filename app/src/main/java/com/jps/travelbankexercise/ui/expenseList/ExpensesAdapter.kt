package com.jps.travelbankexercise.ui.expenseList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jps.travelbankexercise.data.remote.response.TravelBankDataItem
import com.jps.travelbankexercise.databinding.ItemExpensesBinding
import kotlinx.android.synthetic.main.item_expenses.view.*


interface OnExpenseCallbacks {
    fun onItemClick(mNode: TravelBankDataItem?)
}

class ExpensesAdapter : ListAdapter<TravelBankDataItem, ExpensesAdapter.ViewHolder>(ExpenseDiffUtil()) {

    var mCallback: OnExpenseCallbacks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemExpensesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun submitList(list: MutableList<TravelBankDataItem?>?) {
        super.submitList(if (list.isNullOrEmpty()) null else ArrayList(list))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(getItem(position))
    }

    inner class ViewHolder(val binding: ItemExpensesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                 mCallback?.onItemClick(getItem(adapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBindView(item: TravelBankDataItem?) {
            item?.let {
                itemView.tv_expense_name.text = it.expenseVenueTitle
                itemView.tv_date.text = it.date.toString().substringBefore("T")

                itemView.tv_amount.text = "$" + it.amount
                itemView.tv_expense_type.text = it.type
                itemView.tv_expense_cur.text = it.currencyCode

                Glide
                    .with(itemView.iv_recipt.context)
                    .load(it.attachments?.get(0)?.thumbnails?.list)
                    //  .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_selected))

                    .into(itemView.iv_recipt)

            }


        }

    }

}

class ExpenseDiffUtil : DiffUtil.ItemCallback<TravelBankDataItem>() {
    override fun areItemsTheSame(
        oldItem: TravelBankDataItem,
        newItem: TravelBankDataItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: TravelBankDataItem,
        newItem: TravelBankDataItem
    ): Boolean {
        return oldItem == newItem

    }

}