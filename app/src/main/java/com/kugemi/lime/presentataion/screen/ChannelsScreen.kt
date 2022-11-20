package com.kugemi.lime.presentataion.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kugemi.lime.domain.model.Channel
import com.kugemi.lime.presentataion.components.ChannelItem
import com.kugemi.lime.presentataion.viewmodels.ChannelsViewModel

@Composable
fun ChannelsScreen() {
    val channelsViewModel: ChannelsViewModel = viewModel()

    val channels = channelsViewModel.channels.observeAsState()

    channels.value?.let { channels ->
        LazyColumn {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
            }
            items(channels.channels) { item ->
                ChannelItem(channel = item)
            }
        }
    }
}