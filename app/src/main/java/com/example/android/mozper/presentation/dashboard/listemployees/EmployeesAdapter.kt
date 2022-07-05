package com.example.android.mozper.presentation.dashboard.listemployees

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mozper.R
import com.example.android.mozper.databinding.ItemEmployeeBinding
import com.example.android.mozper.domain.employee.Employee
import com.example.android.mozper.presentation.utils.loadFromUrl

interface OnItemSelected {
    fun onItemClicked(employee: Employee)
}

class EmployeesAdapter : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    private var items: List<Employee> = listOf()
    var callback: OnItemSelected? = null

    fun addItems(items: List<Employee>) {
        if (this.items.isEmpty()) {
            this.items = items
            notifyItemRangeInserted(0, items.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEmployeeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: Employee) {
            binding.apply {
                employeeImageItem.loadFromUrl(employee.image, R.mipmap.ic_launcher)
                nameLabelItem.text = employee.fullName
                descriptionLabelItem.text = employee.description
                rateLabelItem.text = employee.ratingText
                root.setOnClickListener {
                    callback?.onItemClicked(employee)
                }
            }
        }
    }
}