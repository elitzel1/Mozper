package com.example.android.mozper.di.presentation

import androidx.lifecycle.ViewModel
import com.example.android.mozper.di.ActivityScope
import com.example.android.mozper.di.FragmentScope
import com.example.android.mozper.di.ViewModelKey
import com.example.android.mozper.presentation.dashboard.DashboardViewModel
import com.example.android.mozper.presentation.dashboard.MainActivity
import com.example.android.mozper.presentation.dashboard.detailemployee.DetailEmployeeScreen
import com.example.android.mozper.presentation.dashboard.listemployees.ListEmployeesScreen
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DashboardModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(
        dashboardViewModel: DashboardViewModel
    ): ViewModel

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeListEmployeesScreen(): ListEmployeesScreen

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeDetailEmployeeScreen(): DetailEmployeeScreen
}