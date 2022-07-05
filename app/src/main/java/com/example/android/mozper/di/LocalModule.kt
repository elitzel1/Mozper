package com.example.android.mozper.di

import android.content.Context
import androidx.room.Room
import com.example.android.mozper.data.database.MozperDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Singleton
    @Provides
    fun provideRoom(applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        MozperDatabase::class.java,
        "mozper_database"
    ).build()

}