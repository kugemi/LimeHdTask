package com.kugemi.lime.presentataion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionOverrides
import com.kugemi.lime.domain.model.Channel
import com.kugemi.lime.presentataion.extensions.px
import com.kugemi.lime.presentataion.resources.*
import com.kugemi.lime.presentataion.viewmodels.ChannelsViewModel

@Composable
fun VideoOverlay(
    channel: Channel,
    trackSelector: DefaultTrackSelector,
    qualityList: ArrayList<Pair<String, TrackSelectionOverrides.Builder>>,
    onItemClick: (item: DefaultTrackSelector.Parameters) -> Unit
) {
    val channelsViewModel: ChannelsViewModel = viewModel()

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var isShowPopup by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(end = (screenWidth) / 2, bottom = (screenHeight) - (defaultPadding * 2))
            .offset(x = (defaultPadding / 2), y = (defaultPadding / 2)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { channelsViewModel.removeCurrentUrl() },
            modifier = Modifier.size(defaultPadding)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = "contentDescription",
            )
        }

        Image(
            painter = rememberAsyncImagePainter(channel.image),
            contentDescription = null,
            modifier = Modifier
                .size(overlayImageSize)
                .offset(x = defaultPadding)
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = overlayImageSize)
        ) {
            Text(
                text = channel.current?.title ?: "",
                color = Color.White,
                fontSize = overlayChannelTitleTextSize
            )
            Text(
                text = channel.name_ru,
                modifier = Modifier.alpha(0.8f),
                color = Color.White,
                fontSize = overlayChannelNameTextSize
            )
        }
    }

    IconButton(
        onClick = { isShowPopup = true },
        modifier = Modifier
            .size(defaultPadding)
            .padding(
                start = (screenWidth) - (defaultPadding * 2),
                bottom = (screenHeight) - (defaultPadding * 2)
            )
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            tint = Color.White,
            contentDescription = "contentDescription",
        )
    }

    if (isShowPopup) {
        Popup(
            offset = IntOffset(
                x = screenWidth.px - (defaultPadding * 4).px,
                y = (defaultPadding * 2).px
            )
        ) {
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF343438),
                ),
                shape = RoundedCornerShape(cardCornerShape)
            ) {
                LazyColumn {
                    items(qualityList) {
                        Text(
                            text = it.first,
                            modifier = Modifier
                                .padding(qualityPopupPadding)
                                .clickable {
                                    isShowPopup = false
                                    onItemClick(trackSelector.parameters
                                        .buildUpon()
                                        .setTrackSelectionOverrides(it.second.build())
                                        .setTunnelingEnabled(true)
                                        .build())
                                }
                        )
                    }
                }
            }
        }
    }
}