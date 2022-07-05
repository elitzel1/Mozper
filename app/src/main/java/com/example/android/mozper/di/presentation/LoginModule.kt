package com.example.android.mozper.di.presentation

import androidx.lifecycle.ViewModel
import com.example.android.mozper.di.ActivityScope
import com.example.android.mozper.di.FragmentScope
import com.example.android.mozper.di.ViewModelKey
import com.example.android.mozper.presentation.login.LoginActivity
import com.example.android.mozper.presentation.login.LoginScreen
import com.example.android.mozper.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(
        loginViewModel: LoginViewModel
    ): ViewModel

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeLoginScreen(): LoginScreen
}