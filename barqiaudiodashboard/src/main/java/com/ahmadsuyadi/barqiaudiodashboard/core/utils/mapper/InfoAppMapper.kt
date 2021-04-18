package com.ahmadsuyadi.barqiaudiodashboard.core.utils.mapper

import com.ahmadsuyadi.barqiaudiodashboard.core.data.source.remote.response.InfoAppResponse
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.InfoApp
import com.ahmadsuyadi.barqiaudiodashboard.core.domain.model.TestDevicesItem

object InfoAppMapper {
    fun mapResponseToDomain(input: InfoAppResponse) =
        with(input) {
            InfoApp(
                isShowAds = isShowAds,
                startAppId = startAppId,
                appLovinInter = appLovinInter,
                intervalInt = intervalInt,
                unityGameID = unityGameID,
                isTestAds = isTestAds,
                testDeviceID = testDeviceID,
                urlRedirect = urlRedirect,
                packageName = packageName,
                idRewardAdmob = idRewardAdmob,
                idBannerAdmob = idBannerAdmob,
                mopubInter = mopubInter,
                openIdAdmob = openIdAdmob,
                isOnRedirect = isOnRedirect,
                isShowImageAudio = isShowImageAudio,
                fanInter = fanInter,
                unityBanner = unityBanner,
                modeAds = modeAds,
                fanBanner = fanBanner,
                appLovinBanner = appLovinBanner,
                idNativeAdmob = idNativeAdmob,
                urlMoreApp = urlMoreApp,
                sdkKeyAppLovin = sdkKeyAppLovin,
                mopubBanner = mopubBanner,
                name = name,
                testDevices = testDevices?.map {
                    TestDevicesItem(
                        id = it.id,
                        deviceName = it.deviceName,
                        deviceId = it.deviceId
                    )
                },
                idIntAdmob = idIntAdmob,
                id = id,
                unityInter = unityInter,
                privacyPolicyApp = privacyPolicyApp,
            )
        }
}