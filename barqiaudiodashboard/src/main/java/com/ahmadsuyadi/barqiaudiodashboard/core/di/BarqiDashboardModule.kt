package com.ahmadsuyadi.barqiaudiodashboard.core.di

import androidx.room.Room
import com.ahmadsuyadi.barqiaudiodashboard.core.data.BarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.LocalDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room.BarqiDatabase
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.RemoteDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network.ApiService
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.ConfigBarqiAudioDashboard
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.Constants.ACCESS_TOKEN
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.Constants.HEADER_CACHE_CONTROL
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.Constants.HEADER_PRAGMA
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.isNetworkAvailable
import com.ahmadsuyadi.barqiaudiodashboard.core.viewmodel.BarqiDashboardViewModel
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val barqiDashboardDatabaseModule = module {
    factory { get<BarqiDatabase>().audioDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("BarqiAudioDashboard".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            BarqiDatabase::class.java, "BarqiAudioDashboard.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val barqiDashboardNetworkModule = module {
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.NONE)
        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader(ACCESS_TOKEN, ConfigBarqiAudioDashboard.ACCESS_TOKEN).build()
            it.proceed(request)
        }

        fun offlineInterceptor(): Interceptor {
            return Interceptor { chain ->
                var request = chain.request()
                val cacheControl = CacheControl.Builder()
                    .maxStale(1000, TimeUnit.DAYS)
                    .build()
                if (!isNetworkAvailable(get())) {
                    request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }
                chain.proceed(request)
            }
        }

        OkHttpClient.Builder()
            .cache(Cache(androidContext().cacheDir, 50 * 1024 * 1024))
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(offlineInterceptor())
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

val barqiDashboardRepositoryModule = module {

    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }

    single {
        BarqiRepository(
            get(),
            get(),
            get()
        )
    }
}

val barqiDashboardViewModelModule = module {
    viewModel { BarqiDashboardViewModel(get()) }
}