package com.kugemi.lime.domain.useCase

data class ChannelsUseCases(
    val getChannelsUseCase: GetChannelsUseCase,
    val getFavoriteChannelsUseCase: GetFavoriteChannelsUseCase,
    val addFavoriteChannelUseCase: AddFavoriteChannelUseCase,
    val removeFavoriteChannelUseCase: RemoveFavoriteChannelUseCase
)
