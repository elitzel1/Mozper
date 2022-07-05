package com.example.android.mozper.di

import com.example.android.mozper.BuildConfig
import com.example.android.mozper.data.api.MozperApi
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val baseUrl = "https://demo3535907.mockable.io/"
    private val buildTypeRelease = "release"

    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
        if (!BuildConfig.BUILD_TYPE.startsWith(buildTypeRelease)) {
            okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideMozperApi(retrofit: Retrofit): MozperApi {
        return retrofit.create(MozperApi::class.java)
    }
}