package com.ahmadsuyadi.barqiaudiodashboard.core.data

import android.annotation.SuppressLint
import android.content.Context
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.RemoteDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Ads
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Artist
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository.IBarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.handleMessageError
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper.AdsMapper
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper.ArtistMapper
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper.AudioMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BarqiRepository(
    private val remoteDataSource: RemoteDataSource,
    private val context: Context
): IBarqiRepository {
    @SuppressLint("CheckResult")
    override fun getAds(): Flowable<Resource<Ads>> {
        val resultData = PublishSubject.create<Resource<Ads>>()
        GlobalScope.launch(Dispatchers.Main) {
            resultData.onNext(Resource.Loading())
        }
        remoteDataSource.getAds(context.packageName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                resultData.onNext(Resource.Success(AdsMapper.mapResponseToDomain(it)))
            }, {
                resultData.onNext(Resource.Error(it.handleMessageError()))
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getArtists(): Flowable<Resource<List<Artist>>> {
        val resultData = PublishSubject.create<Resource<List<Artist>>>()
        GlobalScope.launch(Dispatchers.Main) {
            resultData.onNext(Resource.Loading())
        }
        remoteDataSource.getArtists(context.packageName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                resultData.onNext(Resource.Success(ArtistMapper.mapResponsesToEntities(it)))
            }, {
                resultData.onNext(Resource.Error(it.handleMessageError()))
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getAudios(): Flowable<Resource<List<Audio>>> {
        val resultData = PublishSubject.create<Resource<List<Audio>>>()
        GlobalScope.launch(Dispatchers.Main) {
            resultData.onNext(Resource.Loading())
        }
        remoteDataSource.getAudios(context.packageName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                resultData.onNext(Resource.Success(AudioMapper.mapResponsesToDomains(it)))
            }, {
                resultData.onNext(Resource.Error(it.handleMessageError()))
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}