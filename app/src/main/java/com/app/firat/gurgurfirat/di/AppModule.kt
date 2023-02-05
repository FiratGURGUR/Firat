package com.app.firat.gurgurfirat.di

import android.content.Context
import androidx.room.Room
import com.app.firat.gurgurfirat.data.SatelliteDao
import com.app.firat.gurgurfirat.data.SatelliteDatabase
import com.app.firat.gurgurfirat.data.SatelliteRepository
import com.app.firat.gurgurfirat.ui.list.SatelliteViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSatelliteViewModel(@ApplicationContext context: Context,repository: SatelliteRepository): SatelliteViewModel =
        SatelliteViewModel(context,repository)

    @Singleton
    @Provides
    fun provideSatelliteDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        SatelliteDatabase::class.java,
        "satellite.db"
    ).build()

    @Singleton
    @Provides
    fun provideSatelliteDao(db: SatelliteDatabase) = db.getSatelliteDao()

    @Singleton
    @Provides
    fun provideRepository(dao: SatelliteDao) = SatelliteRepository(dao)

}