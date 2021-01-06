package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.AdsResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.Ads

object AdsMapper {
    fun mapResponseToDomain(input: AdsResponse) =
            Ads(
                    isShowAds = input.isShowAds,
                    isTestAds = input.isTestAds,
                    isShowImageAudio = input.isShowImageAudio,
                    modeAds = input.modeAds,
                    idBannerAdmob = input.idBannerAdmob,
                    idIntAdmob = input.idIntAdmob,
                    idNativeAdmob = input.idNativeAdmob,
                    idRewardAdmob = input.idRewardAdmob,
                    openIdAdmob = input.openIdAdmob,
                    unityGameID = input.unityGameID,
                    unityBanner = input.unityBanner,
                    unityInter = input.unityInter,
                    fanBanner = input.fanBanner,
                    fanInter = input.fanInter,
                    mopubBanner = input.mopubBanner,
                    mopubInter = input.mopubInter,
                    startAppId = input.startAppId,
                    intervalInt = input.intervalInt,
                    isOnRedirect = input.isOnRedirect,
                    urlRedirect = input.urlRedirect,
                    urlMoreApp = input.urlMoreApp,
                    privacyPolicyApp = input.privacyPolicyApp,
            )
}