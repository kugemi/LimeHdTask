package com.kugemi.lime.domain.useCase

import com.kugemi.lime.domain.repository.ChannelsRepository

class GetChannelsUseCase(private val channelsRepository: ChannelsRepository) {
    suspend operator fun invoke() = channelsRepository.getChannels()
}