package com.kugemi.lime.presentataion.extensions

import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.MappingTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionOverrides

fun DefaultTrackSelector.generateQualityList(): ArrayList<Pair<String, TrackSelectionOverrides.Builder>> {
    val trackOverrideList = ArrayList<Pair<String, TrackSelectionOverrides.Builder>>()

    val renderTrack = this.currentMappedTrackInfo
    val renderCount = renderTrack?.rendererCount ?: 0
    for (rendererIndex in 0 until renderCount) {
        if (isSupportedFormat(renderTrack, rendererIndex)) {
            val trackGroupType = renderTrack?.getRendererType(rendererIndex)
            val trackGroups = renderTrack?.getTrackGroups(rendererIndex)
            val trackGroupsCount = trackGroups?.length ?: 0
            if (trackGroupType == C.TRACK_TYPE_VIDEO) {
                for (groupIndex in 0 until trackGroupsCount) {
                    val videoQualityTrackCount = trackGroups?.get(groupIndex)?.length ?: 0
                    for (trackIndex in 0 until videoQualityTrackCount) {
                        val isTrackSupported = renderTrack.getTrackSupport(
                            rendererIndex,
                            groupIndex,
                            trackIndex
                        ) == C.FORMAT_HANDLED
                        if (isTrackSupported) {
                            val track = trackGroups?.get(groupIndex)
                            val trackName =
                                "${track?.getFormat(trackIndex)?.width} x ${track?.getFormat(trackIndex)?.height}"
                            if (track != null) {
                                if (track.getFormat(trackIndex).selectionFlags==C.SELECTION_FLAG_AUTOSELECT){
                                    trackName.plus(" (Default)")
                                }
                            }
                            track?.let {
                                val trackBuilder =
                                    TrackSelectionOverrides.Builder()
                                        .clearOverridesOfType(C.TRACK_TYPE_VIDEO)
                                        .addOverride(TrackSelectionOverrides.TrackSelectionOverride(track,
                                            listOf(trackIndex)))
                                trackOverrideList.add(Pair(trackName, trackBuilder))
                            }
                        }
                    }
                }
            }
        }
    }
    return trackOverrideList
}

fun isSupportedFormat(mappedTrackInfo: MappingTrackSelector.MappedTrackInfo?, rendererIndex: Int): Boolean {
    val trackGroupArray = mappedTrackInfo?.getTrackGroups(rendererIndex)
    return if (trackGroupArray?.length == 0) {
        false
    } else mappedTrackInfo?.getRendererType(rendererIndex) == C.TRACK_TYPE_VIDEO || mappedTrackInfo?.getRendererType(
        rendererIndex
    ) == C.TRACK_TYPE_AUDIO || mappedTrackInfo?.getRendererType(rendererIndex) == C.TRACK_TYPE_TEXT
}