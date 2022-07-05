package com.example.android.mozper.presentation.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.mozper.R
import com.example.android.mozper.databinding.EmployeeActivityBinding
import com.example.android.mozper.presentation.base.MozperActivity
import com.example.android.mozper.presentation.dashboard.detailemployee.DetailEmployeeScreen
import com.example.android.mozper.presentation.dashboard.listemployees.ListEmployeesScreen
import com.example.android.mozper.presentation.login.LoginActivity
import com.example.android.mozper.presentation.utils.SceneTransition


interface ViewRouter {
    fun onShowDetail()
}

class MainActivity : MozperActivity(), ViewRouter {

    lateinit var binding: EmployeeActivityBinding
    lateinit var viewModel: DashboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[DashboardViewModel::class.java]
        setSupportActionBar(binding.toolbarEmployee)
        viewModel.logoutEmitter.observe(this, Observer {
            val flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            LoginActivity.startActivity(context = this, flags = flags)
        })
        showFragmentView(ListEmployeesScreen.newInstance())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.logout -> {
                viewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getBindingRootView(): View {
        binding = EmployeeActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onShowDetail() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = viewModel.employee?.fullName
        showFragmentView(
            DetailEmployeeScreen.newInstance(),
            DetailEmployeeScreen.TAG,
            addToBackStack = true,
            sceneTransition = SceneTransition.PUSH
        )
    }

    override fun onBackPressed() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setTitle(R.string.app_name)
        super.onBackPressed()
    }

    companion object {

        fun startActivity(context: Context, flags: Int? = null) {
            val intent = Intent(context, MainActivity::class.java)
            if (null != flags) {
                intent.flags = flags
            }
            context.startActivity(intent)
        }
    }
}