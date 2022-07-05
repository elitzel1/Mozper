package com.example.android.mozper.di

import android.content.Context
import com.example.android.mozper.MozperApp
import com.example.android.mozper.di.data.SharedPreferenceModule
import com.example.android.mozper.di.presentation.LoginModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        LocalModule::class,
        com.example.android.mozper.di.data.EmployeeModule::class,
        com.example.android.mozper.di.presentation.DashboardModule::class,
        LoginModule::class,
        SharedPreferenceModule::class]
)
interface MozperAppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: MozperApp)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MozperAppComponent
    }
}