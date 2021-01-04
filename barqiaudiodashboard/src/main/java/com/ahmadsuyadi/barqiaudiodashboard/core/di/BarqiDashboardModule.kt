package com.ahmadsuyadi.barqiaudiodashboard.core.di

import com.ahmadsuyadi.barqiaudiodashboard.core.data.BarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.RemoteDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network.ApiService
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository.IBarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.ConfigBarqiAudioDashboard
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(if (androidContext().applicationInfo.flags == 0)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE))
            .addInterceptor {
                val request =
                    it.request().newBuilder().addHeader("access-token", ConfigBarqiAudioDashboard.ACCESS_TOKEN).build()
                it.proceed(request)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(ConfigBarqiAudioDashboard.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IBarqiRepository> {
        BarqiRepository(
            get(),
            get()
        )
    }
}