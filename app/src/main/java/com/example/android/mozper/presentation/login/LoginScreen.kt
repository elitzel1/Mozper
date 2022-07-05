package com.example.android.mozper.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.mozper.R
import com.example.android.mozper.databinding.LoginScreenBinding
import com.example.android.mozper.presentation.base.DaggerBaseFragment
import com.example.android.mozper.presentation.utils.loadFromUrl

class LoginScreen : DaggerBaseFragment() {

    private var _binding: LoginScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewRouter: ViewRouter

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
            ViewModelProvider(requireActivity(), viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = LoginScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
        initObservers()
        viewModel.validateSession()
    }

    private fun initView() {
        binding.logoImageLogin.loadFromUrl(
            "https://miro.medium.com/max/678/1*xvJVhlwKmACwXNh9CCb76w.png",
            R.mipmap.ic_launcher
        )
    }

    private fun initListeners() {
        binding.buttonLogin.setOnClickListener {
            viewModel.login(
                binding.emailInputLogin.toString(),
                binding.passwordInputLogin.editableText?.toString()
            )
        }
    }

    private fun initObservers() {
        viewModel.loginLiveData.observe(viewLifecycleOwner, Observer {
            if (it) viewRouter.toLogin()
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        val TAG = LoginScreen::class.java.name
        fun newInstance() = LoginScreen()
    }
}