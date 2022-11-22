package com.kugemi.lime.presentataion.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kugemi.lime.domain.model.Channel
import com.kugemi.lime.presentataion.components.ChannelItem
import com.kugemi.lime.presentataion.resources.emptyPadding
import com.kugemi.lime.presentataion.viewmodels.ChannelsViewModel
import com.kugemi.lime.presentataion.viewmodels.FavoritesViewModel

@Composable
fun ChannelsScreen(channelsViewModel: ChannelsViewModel, isFavorites: Boolean = false) {

    val favoritesViewModel: FavoritesViewModel = viewModel()

    val channels = channelsViewModel.channels.observeAsState()

    val searchQuery = channelsViewModel.searchQuery.observeAsState()

    val favoriteChannels = favoritesViewModel.favoriteChannels.observeAsState()

    var channelsList by remember { mutableStateOf(listOf<Channel>()) }

    channels.value?.let { channels ->
        channelsList = if (!isFavorites) {
            channels.channels
        } else {
            channels.channels.filter { channel ->
                favoriteChannels.value?.firstOrNull { channel.name_ru == it.name } != null
            }
        }
    }

    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(emptyPadding)
            )
        }
        items(
            if (searchQuery.value == "") {
                channelsList
            } else {
                channelsList.filter {
                    searchQuery.value?.let { searchQuery ->
                        it.name_ru.take(searchQuery.length).lowercase() == searchQuery ||
                                (it.current?.title?.take(searchQuery.length)?.lowercase() ?: "") == searchQuery
                    } ?: true
                }
            }
        ) { item ->
            ChannelItem(channel = item, favoritesViewModel, channelsViewModel)
        }
    }
}