package com.kugemi.lime.presentataion.components

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionOverrides
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.util.Util
import com.kugemi.lime.domain.model.Channel
import com.kugemi.lime.presentataion.custom_states.ExoPlayerState
import com.kugemi.lime.presentataion.extensions.generateQualityList

@Composable
fun VideoView(channel: Channel) {

    val context = LocalContext.current

    val trackSelector by remember {
        mutableStateOf(DefaultTrackSelector(context, AdaptiveTrackSelection.Factory()))
    }

    var qualityList by remember {
        mutableStateOf(arrayListOf<Pair<String, TrackSelectionOverrides.Builder>>())
    }

    val exoPlayerState by remember(context) { mutableStateOf(ExoPlayerState(context, trackSelector)) }

    LaunchedEffect(channel.url) {
        exoPlayerState.exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_READY -> {
                        qualityList = trackSelector.generateQualityList()
                    }

                    Player.STATE_ENDED -> {}

                    Player.STATE_BUFFERING, Player.STATE_IDLE -> {}
                }
            }
        })

        val mediaSource = generateMediaSource(context, channel.url)
        exoPlayerState.exoPlayer.setMediaSource(mediaSource)
        exoPlayerState.exoPlayer.prepare()
    }

    DisposableEffect(
        AndroidView(
            factory = {
                StyledPlayerView(context).apply {
                    player = exoPlayerState.exoPlayer
                }
            }
        )
    ) {
        onDispose { exoPlayerState.exoPlayer.release() }
    }

    VideoOverlay(
        channel = channel,
        trackSelector = trackSelector,
        qualityList = qualityList,
        onItemClick = {
            trackSelector.parameters = it
        }
    )
}

private fun generateMediaSource(context: Context, videoUrl: String): MediaSource {
    val mediaItem = MediaItem.Builder()
        .setUri(Uri.parse(videoUrl))
        .setDrmSessionForClearPeriods(true)
        .build()
    return DefaultMediaSourceFactory(buildDataSourceFactory(context)).createMediaSource(mediaItem)
}

private fun buildDataSourceFactory(context: Context): DataSource.Factory {
    return DefaultDataSourceFactory(
        context,
        getDefaultHttpDataSourceFactory(context)
    )
}

private fun getDefaultHttpDataSourceFactory(context: Context): HttpDataSource.Factory {
    return DefaultHttpDataSource.Factory()
        .setUserAgent(Util.getUserAgent(context, context.packageName))
}