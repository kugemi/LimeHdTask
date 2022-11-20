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
import com.kugemi.lime.presentataion.viewmodels.FavoritesViewModel

@Composable
fun ChannelsScreen(channelsViewModel: ChannelsViewModel, isFavorites: Boolean = false) {

    val favoritesViewModel: FavoritesViewModel = viewModel()

    val channels = channelsViewModel.channels.observeAsState()

    val favoriteChannels = favoritesViewModel.favoriteChannels.observeAsState()

    var channelsList = listOf<Channel>()

    channels.value?.let { channels ->
        channelsList = if (!isFavorites) {
            channels.channels
        } else {
            channels.channels.filter { channel ->
                var isContains = false
                favoriteChannels.value?.forEach { favorite ->
                    if (channel.name_ru == favorite.name) isContains = true
                }
                isContains
            }
        }
    }


    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
        }
        items(channelsList) { item ->
            ChannelItem(channel = item, favoritesViewModel)
        }
    }
}