package com.example.android.mozper.presentation.dashboard

import androidx.lifecycle.LiveData
import com.example.android.mozper.domain.employee.Employee
import com.example.android.mozper.domain.employee.GetEmployeesUseCase
import com.example.android.mozper.domain.logout.LogoutUseCase
import com.example.android.mozper.presentation.base.MozperViewModel
import com.example.android.mozper.presentation.base.SingleLiveEvent
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val logoutUseCase: LogoutUseCase
) : MozperViewModel() {

    private val _dataEmployeesLiveData = SingleLiveEvent<List<Employee>>()
    val dataEmployeesLiveData: LiveData<List<Employee>> get() = _dataEmployeesLiveData

    private val _logoutEmitter = SingleLiveEvent<Boolean>()
    val logoutEmitter: LiveData<Boolean> get() = _logoutEmitter

    var employee: Employee? = null
        private set

    fun getEmployees() {
      launchIo {
          val data = getEmployeesUseCase.run()
          _dataEmployeesLiveData.postValue(data)
      }
    }

    fun logout() {
        launchIo {
            logoutUseCase.run()
            _logoutEmitter.postValue(true)
        }
    }

    fun setSelectedEmployee(employee: Employee) {
        this.employee = employee
    }
}