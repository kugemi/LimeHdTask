package com.kugemi.lime.presentataion.custom_states

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector

class ExoPlayerState(context: Context, trackSelector: DefaultTrackSelector) {
    val exoPlayer = ExoPlayer.Builder(context).setTrackSelector(trackSelector).build()
    val duration by mutableStateOf(exoPlayer.duration)
    val bufferedPosition by mutableStateOf(exoPlayer.bufferedPosition)
    var position by mutableStateOf(exoPlayer.currentPosition)
}