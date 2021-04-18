package com.ahmadsuyadi.barqiaudiodashboard.core.domain.model

data class InfoApp(
	val isShowAds: Boolean? = null,
	val startAppId: String? = null,
	val appLovinInter: String? = null,
	val intervalInt: Int? = null,
	val unityGameID: String? = null,
	val isTestAds: Boolean? = null,
	val testDeviceID: String? = null,
	val urlRedirect: String? = null,
	val packageName: String? = null,
	val idRewardAdmob: String? = null,
	val idBannerAdmob: String? = null,
	val mopubInter: String? = null,
	val openIdAdmob: String? = null,
	val isOnRedirect: Boolean? = null,
	val isShowImageAudio: Boolean? = null,
	val fanInter: String? = null,
	val unityBanner: String? = null,
	val modeAds: Int? = null,
	val fanBanner: String? = null,
	val appLovinBanner: String? = null,
	val idNativeAdmob: String? = null,
	val urlMoreApp: String? = null,
	val sdkKeyAppLovin: String? = null,
	val mopubBanner: String? = null,
	val name: String? = null,
	val testDevices: List<TestDevicesItem>? = null,
	val idIntAdmob: String? = null,
	val id: String? = null,
	val unityInter: String? = null,
	val privacyPolicyApp: String? = null
)

data class TestDevicesItem(
	val id: String? = null,
	val deviceName: String? = null,
	val deviceId: String? = null
)

