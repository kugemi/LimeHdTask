package com.kugemi.lime.di

import com.kugemi.lime.domain.repository.ChannelsRepository
import com.kugemi.lime.domain.useCase.ChannelsUseCases
import com.kugemi.lime.domain.useCase.GetChannelsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideChannelsUseCase(channelsRepository: ChannelsRepository) = ChannelsUseCases(
        getChannelsUseCase = GetChannelsUseCase(channelsRepository = channelsRepository)
    )
}