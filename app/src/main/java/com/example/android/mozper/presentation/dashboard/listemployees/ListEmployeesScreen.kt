package com.example.android.mozper.presentation.dashboard.listemployees

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.mozper.databinding.ListEmployeesScreenBinding
import com.example.android.mozper.domain.employee.Employee
import com.example.android.mozper.presentation.base.DaggerBaseFragment
import com.example.android.mozper.presentation.dashboard.DashboardViewModel
import com.example.android.mozper.presentation.dashboard.ViewRouter
import com.example.android.mozper.presentation.utils.show

class ListEmployeesScreen : DaggerBaseFragment() {

    private var _binding: ListEmployeesScreenBinding? = null
    private val binding get() = _binding!!
    private val employeesAdapter = EmployeesAdapter()

    private lateinit var viewModel: DashboardViewModel
    private lateinit var viewRouter: ViewRouter

    private val onItemSelected = object : OnItemSelected {
        override fun onItemClicked(employee: Employee) {
            viewModel.setSelectedEmployee(employee)
            viewRouter.onShowDetail()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            viewRouter = context as ViewRouter
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement ViewRouter")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[DashboardViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = ListEmployeesScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEmployees()
    }

    private fun initObservers() {
        viewModel.dataEmployeesLiveData.observe(viewLifecycleOwner, Observer {
            binding.recyclerMain.show()
            employeesAdapter.addItems(it)
            binding.loaderMain.hide()
        })

        viewModel.errorStateEmitter.observe(viewLifecycleOwner, Observer {
            it.printStackTrace()
            Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initView() {
        employeesAdapter.callback = onItemSelected
        binding.recyclerMain.apply {
            adapter = employeesAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.loaderMain.show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = ListEmployeesScreen()
    }
}