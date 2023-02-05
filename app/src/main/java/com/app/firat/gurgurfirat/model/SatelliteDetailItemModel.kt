package com.app.firat.gurgurfirat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_detail")
data class SatelliteDetailItemModel(
    @ColumnInfo(name = "cost_per_launch")
    val cost_per_launch: Int,
    @ColumnInfo(name = "first_flight")
    val first_flight: String,
    @ColumnInfo(name = "height")
    val height: Int,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "mass")
    val mass: Int
)