package com.ahmadsuyadi.barqiaudiodashboard.core.data

import android.annotation.SuppressLint
import android.content.Context
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.LocalDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.RemoteDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Ads
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.ArtisV2
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Artist
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Audio
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository.IBarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.AppExecutors
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.diskIO
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.handleMessageError
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper.*
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
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val context: Context
) : IBarqiRepository {
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

    override fun increaseView(idAudio: String) {
        remoteDataSource.increaseView(idAudio)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe()
    }

    @SuppressLint("CheckResult")
    override fun getArtistsV2(): Flowable<Resource<List<ArtisV2>>> {
        val resultData = PublishSubject.create<Resource<List<ArtisV2>>>()
        GlobalScope.launch(Dispatchers.Main) {
            resultData.onNext(Resource.Loading())
        }
        remoteDataSource.getArtistsV2(context.packageName)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({
                resultData.onNext(Resource.Success(ArtistV2Mapper.mapResponsesToDomains(it)))
            }, {
                resultData.onNext(Resource.Error(it.handleMessageError()))
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun getAudiosTrending(limit: Int?): Flowable<Resource<List<Audio>>> {
        val resultData = PublishSubject.create<Resource<List<Audio>>>()
        GlobalScope.launch(Dispatchers.Main) {
            resultData.onNext(Resource.Loading())
        }
        remoteDataSource.getAudiosTrending(context.packageName, limit)
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

    @SuppressLint("CheckResult")
    override fun getAudiosRecent(limit: Int?): Flowable<Resource<List<Audio>>> {
        val resultData = PublishSubject.create<Resource<List<Audio>>>()
        GlobalScope.launch(Dispatchers.Main) {
            resultData.onNext(Resource.Loading())
        }
        remoteDataSource.getAudiosRecent(limit)
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

    @SuppressLint("CheckResult")
    override fun searchAudio(nameAudio: String): Flowable<Resource<List<Audio>>> {
        val resultData = PublishSubject.create<Resource<List<Audio>>>()
        GlobalScope.launch(Dispatchers.Main) {
            resultData.onNext(Resource.Loading())
        }
        remoteDataSource.searchAudio(nameAudio)
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

    @SuppressLint("CheckResult")
    override fun getAudioByIdArtist(idArtist: String): Flowable<Resource<List<Audio>>> {
        val resultData = PublishSubject.create<Resource<List<Audio>>>()
        GlobalScope.launch(Dispatchers.Main) {
            resultData.onNext(Resource.Loading())
        }
        remoteDataSource.getAudioByArtistId(idArtist)
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

    override fun addAudioToFavorite(audio: Audio) {
        localDataSource.insertFavoriteAudio(FavoriteAudioMapper.mapAudioToEntity(audio)).diskIO()
    }

    override fun removeAudioFromFavorite(idAudio: String) {
        appExecutors.diskIO().execute {
            localDataSource.deleteFavoriteAudioById(idAudio)
        }
    }

    override fun getFavoriteAudios(): Flowable<List<Audio>> {
        return localDataSource.getFavoriteAudios()
            .map { FavoriteAudioMapper.mapEntitiesToAudios(it) }
    }

    @SuppressLint("CheckResult")
    override fun isAudioFavorite(idAudio: String): Flowable<Boolean> {
        val resultData = PublishSubject.create<Boolean>()
        localDataSource.isFavoriteAudio(idAudio)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                resultData.onNext(!it.isNullOrEmpty())
            }
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    override fun setRecentPlayedAudio(audio: Audio) {
        localDataSource.insertRecentPlayedAudio(RecentPlayedAudioMapper.mapAudioToEntity(audio))
            .diskIO()
        localDataSource.getRecentPlayedAudio()
            .doOnNext {
                if (it.size > 10) {
                    for (i in 9..it.size) {
                        appExecutors.diskIO().execute {
                            localDataSource.deleteRecentPlayedAudio(it[i])
                        }
                    }
                }
            }
    }

    override fun getRecentPlayedAudios(): Flowable<List<Audio>> {
        return localDataSource.getRecentPlayedAudio()
            .map { RecentPlayedAudioMapper.mapEntitiesToAudios(it) }
    }
}