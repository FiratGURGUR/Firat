package com.app.firat.gurgurfirat.ui.list


import android.content.Context
import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.firat.gurgurfirat.data.SatelliteRepository
import com.app.firat.gurgurfirat.model.*
import com.app.firat.gurgurfirat.util.ReadExt
import com.app.firat.gurgurfirat.util.bold
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatelliteViewModel  @Inject constructor(
    private val context: Context,
    private val repository: SatelliteRepository
) : ViewModel() {

    private val currentPositionIndex = MutableLiveData(0)

    private val satelliteList = MutableLiveData<List<SatelliteListItemModel>>()
    val satelliteListResponse: LiveData<List<SatelliteListItemModel>> = satelliteList

    private val satelliteDetail = MutableLiveData<SatelliteDetailItemModel>()
    val satelliteDetailResponse: LiveData<SatelliteDetailItemModel> = satelliteDetail

    private val positionList = MutableLiveData<List<Position>>()
    val positionListResponse: LiveData<List<Position>> = positionList

    private val positionXY = MutableLiveData<PositionX>()
    val positionXYResponse: LiveData<PositionX> = positionXY

    private val satelliteDaoDetail = MutableLiveData<SatelliteDetailItemModel>()
    val satelliteDaoDetailResponse: MutableLiveData<SatelliteDetailItemModel> = satelliteDaoDetail

    fun getSatelliteListFromJson(){
        val type = object : TypeToken<List<SatelliteListItemModel>>() {}.type
        satelliteList.postValue(ReadExt.readDataListToList(context,"satellite-list.json",type))
    }


    fun getSatelliteDetailFromJson(id : Int){
        val type = object : TypeToken<List<SatelliteDetailItemModel>>() {}.type
        val detailList = arrayListOf<SatelliteDetailItemModel>()
        detailList.addAll(ReadExt.readDataListToList(context,"satellite-detail.json",type))
        satelliteDetail.postValue(detailList.find { s->s.id == id })
    }


    fun getPositions(){
        val json = ReadExt.assetToString(context, "positions.json")
        val gson = Gson()
        val model = gson.fromJson(json, PositionItemModel::class.java)
        positionList.postValue(model.list)
    }


    fun getXY(position : Position){
        val positionsList = position.positions
        positionXY.value = positionsList[currentPositionIndex.value!!]
        if (currentPositionIndex.value != 2){
            currentPositionIndex.value = currentPositionIndex.value?.plus(1)
        }else{
            currentPositionIndex.value = 0
        }
    }


    fun checkItem(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
           val item : SatelliteDetailItemModel =  repository.getSatellite(id)
           if (item != null){
               satelliteDaoDetail.postValue(item)
           }else{
               getSatelliteDetailFromJson(id)
           }
        }
    }

    fun addSatellite(item : SatelliteDetailItemModel){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addSatellite(item)
        }
    }



}