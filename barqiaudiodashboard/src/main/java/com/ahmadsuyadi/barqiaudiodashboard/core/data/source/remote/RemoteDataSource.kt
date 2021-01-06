package com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote

import android.annotation.SuppressLint
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.network.ApiService
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AdsResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.ArtistV2Response
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AudioResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import okhttp3.ResponseBody

class RemoteDataSource(
        private val apiService: ApiService
) {

    @SuppressLint("CheckResult")
    fun getAds(packageName: String): Flowable<AdsResponse> {
        val resultData = PublishSubject.create<AdsResponse>()
        apiService.getAds(packageName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getArtists(packageName: String): Flowable<List<ArtistResponse>> {
        val resultData = PublishSubject.create<List<ArtistResponse>>()
        apiService.getArtists(packageName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAudios(packageName: String): Flowable<List<AudioResponse>> {
        val resultData = PublishSubject.create<List<AudioResponse>>()
        apiService.getAudios(packageName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun increaseView(idAudio: String): Flowable<ResponseBody> {
        val resultData = PublishSubject.create<ResponseBody>()
        apiService.increaseView(idAudio)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getArtistsV2(packageName: String): Flowable<List<ArtistV2Response>> {
        val resultData = PublishSubject.create<List<ArtistV2Response>>()
        apiService.getArtistsV2(packageName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAudiosTrending(packageName: String, limit: Int?): Flowable<List<AudioResponse>> {
        val resultData = PublishSubject.create<List<AudioResponse>>()
        apiService.getAudiosTrending(packageName, limit)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getLatestUpload(packageName: String, limit: Int?): Flowable<List<AudioResponse>> {
        val resultData = PublishSubject.create<List<AudioResponse>>()
        apiService.getLatestUpload(packageName, limit)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAudiosRecent(limit: Int?): Flowable<List<AudioResponse>> {
        val resultData = PublishSubject.create<List<AudioResponse>>()
        apiService.getAudiosRecent(limit)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun searchAudio(nameAudio: String?): Flowable<List<AudioResponse>> {
        val resultData = PublishSubject.create<List<AudioResponse>>()
        apiService.searchAudio(nameAudio)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAudioByArtistId(artistId: String?): Flowable<List<AudioResponse>> {
        val resultData = PublishSubject.create<List<AudioResponse>>()
        apiService.getByArtistID(artistId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    resultData.onNext(it)
                }, {
                    resultData.onError(it)
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

}