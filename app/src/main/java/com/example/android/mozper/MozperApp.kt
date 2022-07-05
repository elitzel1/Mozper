package com.example.android.mozper

import android.app.Application
import com.example.android.mozper.di.DaggerMozperAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MozperApp : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        val component = DaggerMozperAppComponent.factory().create(applicationContext)
        component.inject(this)

        Stetho.initializeWithDefaults(this)
    }
}