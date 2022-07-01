package com.square.employeedirectory.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.square.employeedirectory.usecase.EmployeeDirectoryType
import com.square.employeedirectory.usecase.GetEmployeeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployeeViewModel(private val getEmployeeUseCase: GetEmployeeUseCase): ViewModel() {
    private val _uiData = MutableStateFlow(EmployeeUiData())
    val uiData: StateFlow<EmployeeUiData> = _uiData.asStateFlow()

    init {
        getEmployees(EmployeeDirectoryType.EMPLOYEE)
    }

    fun getEmployees(type: EmployeeDirectoryType) {
        _uiData.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            val employeeResult = getEmployeeUseCase(type)

            if (employeeResult.isSuccess) {
                _uiData.update {
                    it.copy(
                        isLoading = false,
                        employees = employeeResult.getOrThrow(),
                        error = false
                    )
                }
            } else {
                _uiData.update {
                    it.copy(isLoading = false,
                        employees = emptyList(),
                        error = true
                    )
                }
            }
        }
    }
}