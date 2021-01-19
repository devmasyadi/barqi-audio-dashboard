package com.ahmadsuyadi.barqiaudiodashboard.core.di

import androidx.room.Room
import com.ahmadsuyadi.barqiaudiodashboard.core.data.BarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.LocalDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.room.BarqiDatabase
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.RemoteDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network.ApiService
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository.IBarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.AppExecutors
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.ConfigBarqiAudioDashboard
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.Constants.ACCESS_TOKEN
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.Constants.HEADER_CACHE_CONTROL
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.Constants.HEADER_PRAGMA
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.isNetworkAvailable
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<BarqiDatabase>().favoriteAudioDao() }
    factory { get<BarqiDatabase>().recentPlayedAudioDao() }
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

val networkModule = module {
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(
                if (androidContext().applicationInfo.flags == 0)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            )
        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader(ACCESS_TOKEN, ConfigBarqiAudioDashboard.ACCESS_TOKEN).build()
            it.proceed(request)
        }

        fun offlineInterceptor(): Interceptor {
            return object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
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
                    return chain.proceed(request)
                }
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

val repositoryModule = module {

    single { RemoteDataSource(get()) }
    single { LocalDataSource(get(), get()) }
    factory { AppExecutors() }

    single<IBarqiRepository> {
        BarqiRepository(
            get(),
            get(),
            get(),
            get()
        )
    }


}