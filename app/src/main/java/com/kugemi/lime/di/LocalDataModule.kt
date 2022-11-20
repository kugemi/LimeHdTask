package com.kugemi.lime.di

import com.kugemi.lime.data.database.FavoritesDao
import com.kugemi.lime.data.repository.dataSource.FavoritesLocalDataSource
import com.kugemi.lime.data.repository.dataSourceImpl.FavoritesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(favoritesDao: FavoritesDao): FavoritesLocalDataSource =
        FavoritesLocalDataSourceImpl(favoritesDao)
}