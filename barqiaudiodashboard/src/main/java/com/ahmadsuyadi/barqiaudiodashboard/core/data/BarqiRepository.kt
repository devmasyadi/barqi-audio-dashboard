package com.ahmadsuyadi.barqiaudiodashboard.core.data

import android.content.Context
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.LocalDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.AudioEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.PlaylistAudioCrossRef
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.local.entity.PlaylistEntity
import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.RemoteDataSource
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.*
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.repository.IBarqiRepository
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.handleMessageError
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.isNetworkAvailable
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.extesion.toPathAudioDownload
import com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger

class BarqiRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val context: Context
) : IBarqiRepository, AnkoLogger {

    override fun getInfoApp(): Flow<Resource<InfoApp>> {
        return flow<Resource<InfoApp>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.getInfoApp(context.packageName)
                emit(Resource.Success(InfoAppMapper.mapResponseToDomain(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun getArtists(): Flow<Resource<List<Artist>>> {
        return flow<Resource<List<Artist>>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.getArtists(context.packageName)
                emit(Resource.Success(ArtistMapper.mapResponsesToDomains(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun getAudios(): Flow<Resource<List<Audio>>> {
        return flow<Resource<List<Audio>>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.getAudios(context.packageName)
                emit(Resource.Success(AudioMapper.mapResponsesToDomains(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun increaseView(idAudio: String?) {
        if (isNetworkAvailable(context)) {
            idAudio?.let {
                GlobalScope.launch {
                    remoteDataSource.increaseView(it)
                }
            }
        }
    }

    override fun getArtistsV2(): Flow<Resource<List<ArtisV2>>> {
        return flow<Resource<List<ArtisV2>>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.getArtistsV2(context.packageName)
                emit(Resource.Success(ArtistV2Mapper.mapResponsesToDomains(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun getAudiosTrending(limit: Int?): Flow<Resource<List<Audio>>> {
        return flow<Resource<List<Audio>>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.getAudiosTrending(context.packageName, limit)
                emit(Resource.Success(AudioMapper.mapResponsesToDomains(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun getAudiosRecent(limit: Int?): Flow<Resource<List<Audio>>> {
        return flow<Resource<List<Audio>>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.getAudiosRecent(limit)
                emit(Resource.Success(AudioMapper.mapResponsesToDomains(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }


    override fun getLatestUpload(limit: Int?): Flow<Resource<List<Audio>>> {
        return flow<Resource<List<Audio>>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.getLatestUpload(context.packageName, limit)
                emit(Resource.Success(AudioMapper.mapResponsesToDomains(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun searchAudio(nameAudio: String): Flow<Resource<List<Audio>>> {
        return flow<Resource<List<Audio>>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.searchAudio(nameAudio)
                emit(Resource.Success(AudioMapper.mapResponsesToDomains(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun getAudioByIdArtist(idArtist: String): Flow<Resource<List<Audio>>> {
        return flow<Resource<List<Audio>>> {
            try {
                emit(Resource.Loading())
                val resultData = remoteDataSource.getAudioByArtistId(idArtist)
                emit(Resource.Success(AudioMapper.mapResponsesToDomains(resultData)))
            } catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun addAudioToFavorite(audio: Audio): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            localDataSource.insertFavorite(FavoriteAudioMapper.mapAudioToEntity(audio))
            emit(Resource.Success(true))
        }
    }

    override fun removeAudioFromFavorite(idAudio: Int): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            localDataSource.deleteFavoriteByIdAudio (idAudio)
            emit(Resource.Success(true))
        }
    }

    override fun getFavoriteAudios(): Flow<Resource<List<Audio>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(localDataSource.getListFavorite()
                .map {
                    Resource.Success(FavoriteAudioMapper.mapEntitiesToAudios(it))
                })
        }
    }

    override fun isAudioFavorite(idAudio: String): Flow<Boolean> {
        return localDataSource.getFavoriteByIdAudio(idAudio).map {!it.isNullOrEmpty() }
    }

    override fun setRecentPlayedAudio(audio: Audio) {
        GlobalScope.launch {
            localDataSource.insertRecent(RecentPlayedAudioMapper.mapAudioToEntity(audio))
        }
    }

    override fun getRecentPlayedAudios(): Flow<Resource<List<Audio>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(localDataSource.getListRecent().map { Resource.Success(RecentPlayedAudioMapper.mapEntitiesToAudios(it)) })
        }
    }

    override fun deleteAllRecentPlayed(): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            localDataSource.deleteAllRecent()
            emit(Resource.Success(true))
        }.flowOn(Dispatchers.IO)
    }

    override fun addToDownload(audio: Audio): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            audio.url = audio.title?.toPathAudioDownload(context)
            val data = DownloadedAudioMapper.mapDomainToEntity(audio)
            localDataSource.insertDownloaded(data)
            emit(Resource.Success(true))
        }.flowOn(Dispatchers.IO)
    }

    override fun updateAudioDownloadByReq(idReqDownload: Long): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            localDataSource.updateDownloadedByReq(idReqDownload)
            emit(Resource.Success(true))
        }
    }

    override fun deleteFromDownload(idAudio: Int): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            localDataSource.deleteFavoriteByIdAudio(idAudio)
            emit(Resource.Success(true))
        }.flowOn(Dispatchers.IO)
    }

    override fun getAudiosDownload(): Flow<Resource<List<Audio>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(localDataSource.getListDownloaded().map { Resource.Success(DownloadedAudioMapper.mapEntitiesToDomains(it)) })
        }
    }

    override fun addPlaylist(playlist: Playlist): Flow<Resource<Long>> {
        return flow {
            emit(Resource.Loading())
            val result = localDataSource.insertPlaylist(PlaylistMapper.mapDomainToEntity(playlist))
            emit(Resource.Success(result))
        }.flowOn(Dispatchers.IO)
    }

    override fun editPlaylist(playlist: Playlist): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            localDataSource.updatePlaylist(PlaylistMapper.mapDomainToEntity(playlist))
            emit(Resource.Success(true))
        }.flowOn(Dispatchers.IO)
    }

    override fun deletePlaylist(idPlaylist: Int): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            localDataSource.deletePlaylist(PlaylistEntity(idPlaylist))
            emit(Resource.Success(true))
        }.flowOn(Dispatchers.IO)
    }

    override fun addAudioToPlaylist(playlistId: Int, audio: Audio): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            val audioEntity = AudioEntity(audioId = audio.id?.toLong(), audio = AudioMapper.mapDomainToEntity(audio))
            localDataSource.insertAudio(audioEntity)
            localDataSource.addAudioToPlaylist(PlaylistAudioCrossRef(playlistId, audio.id ?: -1))
            emit(Resource.Success(true))
        }.flowOn(Dispatchers.IO)
    }

    override fun deleteAudioFromPlaylist(playlistId: Int, audioId: Int): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            localDataSource.deleteAudioFromPlaylist(PlaylistAudioCrossRef(playlistId, audioId))
            emit(Resource.Success(true))
        }.flowOn(Dispatchers.IO)
    }

    override fun getListPlaylist(): Flow<Resource<List<Playlist>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(localDataSource.getListPlaylist().map { Resource.Success(PlaylistMapper.mapEntitiesToDomains(it)) })
        }
    }

    override fun getPlaylistAndAudios(): Flow<Resource<List<PlaylistAndAudios>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(localDataSource.getPlaylistAndAudios().map { Resource.Success(PlaylistAndAudioMapper.mapEntitiesToDomains(it)) })
        }
    }

    override fun getAudiosByPlaylist(idPlaylist: Int): Flow<Resource<List<Audio>>> {
        return flow<Resource<List<Audio>>> {
            try {
                emit(Resource.Loading())
                emitAll(localDataSource.getAudiosByPlaylist(idPlaylist).map { Resource.Success(AudioMapper.mapEntitiesToDomains(it)) })
            }catch (e: Throwable) {
                emit(Resource.Error(e.handleMessageError()))
            }
        }
    }

    override fun getDataDummyTest(): Flow<Resource<List<PlaylistAudioCrossRef>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(localDataSource.getPlaylistAudioCrossRef().map { Resource.Success(it) })
        }
    }
}