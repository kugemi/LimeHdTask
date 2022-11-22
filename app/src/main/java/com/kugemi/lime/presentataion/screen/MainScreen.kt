package com.kugemi.lime.presentataion.screen

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kugemi.lime.presentataion.components.SearchComponent
import com.kugemi.lime.presentataion.components.TabLayout
import com.kugemi.lime.presentataion.components.VideoView
import com.kugemi.lime.presentataion.resources.mainBackgroundColor
import com.kugemi.lime.presentataion.viewmodels.ChannelsViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {

    val channelsViewModel: ChannelsViewModel = viewModel()

    val activity = LocalContext.current as Activity

    val currentChannel = channelsViewModel.currentChannel.observeAsState()

    if (currentChannel.value == null) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = mainBackgroundColor)
            ) {
                SearchComponent(channelsViewModel)
            }

            TabLayout(channelsViewModel)
        }
    } else {
        currentChannel.value?.let {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            VideoView(
                channel = it
            )
        }
    }
}