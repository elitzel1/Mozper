package com.example.android.mozper.presentation.dashboard.detailemployee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android.mozper.R
import com.example.android.mozper.databinding.DetailEmployeeScreenBinding
import com.example.android.mozper.presentation.base.DaggerBaseFragment
import com.example.android.mozper.presentation.dashboard.DashboardViewModel
import com.example.android.mozper.presentation.utils.loadFromUrl

class DetailEmployeeScreen : DaggerBaseFragment() {
    private var _binding: DetailEmployeeScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DashboardViewModel

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
        _binding = DetailEmployeeScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val employee = viewModel.employee
        binding.apply {
            employeeImageDetail.loadFromUrl(employee?.image, R.mipmap.ic_launcher)
            descriptionLabelDetail.text = employee?.description
            rateLabelDetail.text = employee?.ratingText
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        val TAG: String = DetailEmployeeScreen::class.java.name
        fun newInstance() = DetailEmployeeScreen()
    }
}