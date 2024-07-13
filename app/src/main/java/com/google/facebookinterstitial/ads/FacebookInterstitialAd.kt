package com.google.facebookinterstitial.ads

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener

class FacebookInterstitialAd(context: Activity) {
  
  init {
      AudienceNetworkAds.initialize(context)
      AdSettings.setTestMode(true)
      //      AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE)
  }
  
  private var interstitialAd: InterstitialAd? = null

  fun loadInterstitial(context: Context,adId: String) {
    interstitialAd = InterstitialAd(context,adId)
   
    val interstitialAdListener = object : InterstitialAdListener {
      override fun onError(p0: Ad?, p1: AdError?) {
        Toast.makeText(context,"Interstitial Failed to Load",Toast.LENGTH_LONG).show()
       Log.d("TAGMETA","${p1?.errorMessage}")
      }
      
      override fun onAdLoaded(p0: Ad?) {
        Toast.makeText(context,"Interstitial Ad Loaded",Toast.LENGTH_LONG).show()
      }
      
      override fun onAdClicked(p0: Ad?) {
  
      }
      
      override fun onLoggingImpression(p0: Ad?) {
    
      }
      
      override fun onInterstitialDisplayed(p0: Ad?) {
   
      }
      
      override fun onInterstitialDismissed(p0: Ad?) {
     
      }
    }
    
    interstitialAd?.loadAd(
      interstitialAd!!
        .buildLoadAdConfig()
        .withAdListener(interstitialAdListener)
        .build())
  }
  
  fun showInterstitial() {
    // Check if interstitialAd has been loaded successfully
    if(interstitialAd == null || interstitialAd?.isAdLoaded() == false) {
      return
    }
    // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
    if(interstitialAd?.isAdInvalidated() == true) { return }
    
    interstitialAd?.show()
  }

}