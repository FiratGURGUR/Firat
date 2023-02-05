package com.app.firat.gurgurfirat.data

import androidx.room.*
import com.app.firat.gurgurfirat.model.SatelliteDetailItemModel


@Dao
interface SatelliteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSatellite(satellite: SatelliteDetailItemModel)

    @Query("SELECT * FROM satellite_detail WHERE id = :id")
    fun getSatellite(id : Int): SatelliteDetailItemModel

    @Update
    fun update(satellite: SatelliteDetailItemModel)

    @Delete
    fun delete(satellite: SatelliteDetailItemModel)

}