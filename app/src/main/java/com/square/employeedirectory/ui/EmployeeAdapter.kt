package com.square.employeedirectory.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.square.employeedirectory.R
import com.square.employeedirectory.data.model.Employee
import com.square.employeedirectory.databinding.EmployeeViewBinding
import com.squareup.picasso.Picasso

class EmployeeAdapter : ListAdapter<Employee, EmployeeAdapter.EmployeeViewHolder>(DIFF_UTIL) {
    class EmployeeViewHolder(private val binding: EmployeeViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(employee: Employee) {
            binding.name.text = employee.fullName
            binding.team.text = employee.team

            Picasso
                .get()
                .load(employee.photoUrl)
                .resizeDimen(R.dimen.employee_photo_width, R.dimen.employee_photo_height)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_face_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = EmployeeViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = getItem(position)

        holder.onBind(employee)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem == newItem
            }
        }
    }
}