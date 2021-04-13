package com.ahmadsuyadi

import android.app.Application
import com.ahmadsuyadi.barqiaudiodashboard.core.di.barqiDashboardDatabaseModule
import com.ahmadsuyadi.barqiaudiodashboard.core.di.barqiDashboardNetworkModule
import com.ahmadsuyadi.barqiaudiodashboard.core.di.barqiDashboardRepositoryModule
import com.ahmadsuyadi.barqiaudiodashboard.core.di.barqiDashboardViewModelModule
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.ConfigBarqiAudioDashboard
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ConfigBarqiAudioDashboard.BASE_URL = "https://api-lagu.masyadi.com/"
        ConfigBarqiAudioDashboard.ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaGVjayI6dHJ1ZSwiaWF0IjoxNTg4NzcxMjA4fQ.aY2Rw8GOdUD3ewg6spv4rQPA3avdNNeAed2hBtTm40w"
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    barqiDashboardDatabaseModule,
                    barqiDashboardNetworkModule,
                    barqiDashboardRepositoryModule,
                    barqiDashboardViewModelModule
                )
            )
        }
    }
}