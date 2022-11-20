package com.kugemi.lime.domain.useCase

import com.kugemi.lime.domain.repository.ChannelsRepository

class GetFavoriteChannelsUseCase(private val channelsRepository: ChannelsRepository) {
    operator fun invoke() = channelsRepository.getFavoriteChannels()
}