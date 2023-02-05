package com.app.firat.gurgurfirat.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.firat.gurgurfirat.model.SatelliteDetailItemModel


@Database(
    entities = [SatelliteDetailItemModel::class],
    version = 1
)
abstract class SatelliteDatabase : RoomDatabase()  {

    abstract fun getSatelliteDao(): SatelliteDao

}