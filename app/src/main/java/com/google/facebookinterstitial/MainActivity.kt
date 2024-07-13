package com.google.facebookinterstitial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.facebookinterstitial.ads.FacebookInterstitialAd
import com.google.facebookinterstitial.ui.theme.FacebookInterstitialTheme
import com.google.metabanner.ui.component.DroidTopAppBar

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      FacebookInterstitialTheme {
       
       val interstitialAd = remember{ FacebookInterstitialAd(this) }
       
       val adId = stringResource(id = R.string.meta_interstitial_ad)
        
        Scaffold(modifier = Modifier.fillMaxSize(),
          topBar = {DroidTopAppBar()}) { innerPadding ->
          Surface(
            modifier = Modifier.padding(innerPadding)
          ){
          Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Row(modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceEvenly,) {
              Button(onClick = { interstitialAd.loadInterstitial(this@MainActivity,adId) }) {
                Text(text = "Load Interstitial Ad")
              }
              
              Button(onClick = { interstitialAd.showInterstitial() }) {
                Text(text = "Show Interstitial Ad")
              }
            }
          }
          }
        }
      }
    }
  }
}
