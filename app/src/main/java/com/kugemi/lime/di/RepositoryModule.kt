package com.kugemi.lime.di

import com.kugemi.lime.data.repository.ChannelsRepositoryImpl
import com.kugemi.lime.data.repository.dataSource.ChannelsRemoteDataSource
import com.kugemi.lime.data.repository.dataSource.FavoritesLocalDataSource
import com.kugemi.lime.domain.repository.ChannelsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideChannelsRepository(
        channelsRemoteDataSource: ChannelsRemoteDataSource,
        favoritesLocalDataSource: FavoritesLocalDataSource
    ): ChannelsRepository =
        ChannelsRepositoryImpl(channelsRemoteDataSource, favoritesLocalDataSource)
}