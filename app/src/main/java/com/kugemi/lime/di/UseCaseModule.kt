package com.kugemi.lime.di

import com.kugemi.lime.domain.repository.ChannelsRepository
import com.kugemi.lime.domain.useCase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideChannelsUseCase(channelsRepository: ChannelsRepository) = ChannelsUseCases(
        getChannelsUseCase = GetChannelsUseCase(channelsRepository = channelsRepository),
        getFavoriteChannelsUseCase = GetFavoriteChannelsUseCase(channelsRepository = channelsRepository),
        addFavoriteChannelUseCase = AddFavoriteChannelUseCase(channelsRepository = channelsRepository),
        removeFavoriteChannelUseCase = RemoveFavoriteChannelUseCase(channelsRepository = channelsRepository)
    )
}