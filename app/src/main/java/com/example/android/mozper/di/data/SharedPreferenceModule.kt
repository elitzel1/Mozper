package com.example.android.mozper.di.data

import android.content.Context
import com.example.android.mozper.data.sharedpreferences.PreferenceDataSource
import com.example.android.mozper.data.sharedpreferences.SharedPreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class SharedPreferenceModule {

    @Reusable
    @Provides
    fun providePreferenceDataSource(context: Context): PreferenceDataSource =
        SharedPreferencesDataSource(context)
}
