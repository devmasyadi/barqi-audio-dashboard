package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.InfoAppResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.InfoApp

object InfoAppMapper {
    fun mapResponseToDomain(input: InfoAppResponse) =
            InfoApp(
                    isShowAds = input.isShowAds,
                    isTestAds = input.isTestAds,
                    isShowImageAudio = input.isShowImageAudio,
                    modeAds = input.modeAds,
                    testDeviceID = input.testDeviceID,
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
                    sdkKeyAppLovin = input.sdkKeyAppLovin,
                    appLovinInter = input.appLovinInter,
                    appLovinBanner = input.appLovinBanner,
                    urlMoreApp = input.urlMoreApp,
                    privacyPolicyApp = input.privacyPolicyApp,
                    urlRedirect = input.urlRedirect
            )
}