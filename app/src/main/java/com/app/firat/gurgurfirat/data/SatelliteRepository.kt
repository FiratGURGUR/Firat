package com.app.firat.gurgurfirat.data

import com.app.firat.gurgurfirat.model.SatelliteDetailItemModel
import javax.inject.Inject

class SatelliteRepository @Inject constructor(
    private val satelliteDao: SatelliteDao
){

    suspend fun addSatellite(satelliteDetailItemModel: SatelliteDetailItemModel) = satelliteDao.addSatellite(satelliteDetailItemModel)

    suspend fun getSatellite(id : Int) = satelliteDao.getSatellite(id)
}