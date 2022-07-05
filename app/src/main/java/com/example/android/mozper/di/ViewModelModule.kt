package com.example.android.mozper.di

import androidx.lifecycle.ViewModelProvider
import com.example.android.mozper.presentation.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
