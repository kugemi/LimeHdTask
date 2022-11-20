package com.kugemi.lime.domain.useCase

import com.kugemi.lime.domain.model.FavoriteChannel
import com.kugemi.lime.domain.repository.ChannelsRepository

class RemoveFavoriteChannelUseCase(private val channelsRepository: ChannelsRepository) {
    suspend operator fun invoke(channel: String) = channelsRepository.removeFavoriteChannel(channel)
}