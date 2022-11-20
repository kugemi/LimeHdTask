package com.kugemi.lime.presentataion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kugemi.lime.domain.model.FavoriteChannel
import com.kugemi.lime.domain.useCase.ChannelsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val channelsUseCase: ChannelsUseCases
) : ViewModel() {
    val favoriteChannels = channelsUseCase.getFavoriteChannelsUseCase.invoke()

    fun addFavoriteChannel(channel: FavoriteChannel) {
        viewModelScope.launch(Dispatchers.IO) {
            channelsUseCase.addFavoriteChannelUseCase.invoke(channel)
        }
    }

    fun removeFavoriteChannel(channel: String) {
        viewModelScope.launch(Dispatchers.IO) {
            channelsUseCase.removeFavoriteChannelUseCase.invoke(channel)
        }
    }
}