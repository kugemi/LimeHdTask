package com.kugemi.lime.di

import android.app.Application
import androidx.room.Room
import com.kugemi.lime.data.database.FavoritesDao
import com.kugemi.lime.data.database.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(app: Application): FavoritesDatabase =
        Room.databaseBuilder(
            app,
            FavoritesDatabase::class.java,
            "FavoritesDatabase"
        ).build()

    @Provides
    fun provideFavoriteDao(database: FavoritesDatabase): FavoritesDao = database.Dao()
}