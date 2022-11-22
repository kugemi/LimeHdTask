package com.kugemi.lime.presentataion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.rememberAsyncImagePainter
import com.kugemi.lime.domain.model.Channel
import com.kugemi.lime.domain.model.FavoriteChannel
import com.kugemi.lime.presentataion.resources.*
import com.kugemi.lime.presentataion.viewmodels.ChannelsViewModel
import com.kugemi.lime.presentataion.viewmodels.FavoritesViewModel

@Composable
fun ChannelItem(
    channel: Channel,
    favoritesViewModel: FavoritesViewModel,
    channelsViewModel: ChannelsViewModel
) {
    val favoriteChannels = favoritesViewModel.favoriteChannels.observeAsState()

    var isSelected by remember { mutableStateOf(false) }

    favoriteChannels.value?.let { favoriteChannels ->
        isSelected = (favoriteChannels.firstOrNull { channel.name_ru == it.name } != null)
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = channelItemHorizontalPadding,
                vertical = channelItemVerticalPadding
            )
            .clickable { channelsViewModel.setCurrentUrl(channel) },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF343438),
        ),
        shape = RoundedCornerShape(cardCornerShape)
    ) {
        Row(
            modifier = Modifier.padding(channelItemHorizontalPadding)
        ) {
            Image(
                painter = rememberAsyncImagePainter(channel.image),
                contentDescription = null,
                modifier = Modifier
                    .size(channelImageSize)
                    .padding(end = channelImageEndPadding)
                    .align(Alignment.CenterVertically)
                    .weight(0.2f)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.7f)
            ) {
                Text(
                    text = channel.name_ru,
                    color = Color.White,
                    fontSize = channelNameTextSize
                )
                Text(
                    text = channel.current?.title ?: "",
                    color = channelTitleTextColor,
                    fontSize = channelTitleTextSize
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .weight(0.1f),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    modifier = Modifier.size(defaultPadding),
                    onClick = {
                        if (!isSelected) {
                            favoritesViewModel.addFavoriteChannel(FavoriteChannel().apply {
                                this.name = channel.name_ru
                            })
                        } else {
                            favoritesViewModel.removeFavoriteChannel(channel.name_ru)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        tint = if (isSelected) favoriteColor else commonColor,
                        contentDescription = "contentDescription",
                    )
                }
            }
        }
    }
}