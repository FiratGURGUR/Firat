package com.app.firat.gurgurfirat.di

import android.content.Context
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
    fun provideSatelliteViewModel(@ApplicationContext context: Context): SatelliteViewModel =
        SatelliteViewModel(context)

}