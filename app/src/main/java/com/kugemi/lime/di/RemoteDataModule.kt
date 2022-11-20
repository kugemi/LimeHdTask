package com.kugemi.lime.di

import com.kugemi.lime.data.api.ChannelsApi
import com.kugemi.lime.data.repository.dataSource.ChannelsRemoteDataSource
import com.kugemi.lime.data.repository.dataSourceImpl.ChannelsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideChannelsRemoteDataSource(channelsApi: ChannelsApi) : ChannelsRemoteDataSource =
        ChannelsRemoteDataSourceImpl(channelsApi)
}