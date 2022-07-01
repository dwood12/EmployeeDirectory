package com.square.employeedirectory.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.square.employeedirectory.R
import com.square.employeedirectory.databinding.ActivityEmployeeBinding
import com.square.employeedirectory.usecase.EmployeeDirectoryType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.android.x.viewmodel.viewModel

class EmployeeActivity : AppCompatActivity(), DIAware {
    override val di: DI by closestDI()
    private val employeeViewModel: EmployeeViewModel by viewModel()
    private var binding: ActivityEmployeeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                employeeViewModel.uiData.collect {
                    if (it.isLoading) {
                        binding?.loader?.show()
                    } else {
                        binding?.loader?.hide()
                        binding?.refresh?.isRefreshing = false

                        if (it.error) {
                            binding?.errorText?.isVisible = true
                            binding?.employees?.isVisible = false
                            binding?.emptyText?.isVisible = false
                        } else if (it.employees.isEmpty()) {
                            binding?.emptyText?.isVisible = true
                            binding?.employees?.isVisible = false
                            binding?.errorText?.isVisible = false
                        } else {
                            binding?.employees?.isVisible = true
                            binding?.emptyText?.isVisible = false
                            binding?.errorText?.isVisible = false
                            val adapter = EmployeeAdapter()
                            binding?.employees?.adapter = adapter
                            binding?.employees?.layoutManager = LinearLayoutManager(
                                this@EmployeeActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            binding?.employees?.addItemDecoration(
                                DividerItemDecoration(
                                    this@EmployeeActivity,
                                    DividerItemDecoration.VERTICAL
                                )
                            )

                            adapter.submitList(it.employees)
                        }
                    }
                }
            }
        }

        binding?.refresh?.setOnRefreshListener {
            employeeViewModel.getEmployees(EmployeeDirectoryType.EMPLOYEE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.employee_directory -> {
                item.isChecked = !item.isChecked
                employeeViewModel.getEmployees(EmployeeDirectoryType.EMPLOYEE)
                true
            }
            R.id.empty_employee_directory -> {
                item.isChecked = !item.isChecked
                employeeViewModel.getEmployees(EmployeeDirectoryType.EMPTY)
                true
            }
            R.id.malformed_employee_directory -> {
                item.isChecked = !item.isChecked
                employeeViewModel.getEmployees(EmployeeDirectoryType.MALFORMED)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}