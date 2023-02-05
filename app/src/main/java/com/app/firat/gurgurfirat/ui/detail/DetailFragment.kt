package com.app.firat.gurgurfirat.ui.detail

import android.os.Handler
 import android.os.Looper
 import android.text.SpannableString
 import android.widget.Toast
 import androidx.fragment.app.viewModels
 import androidx.navigation.fragment.navArgs
import com.app.firat.gurgurfirat.base.BaseFragment
import com.app.firat.gurgurfirat.databinding.FragmentDetailBinding
 import com.app.firat.gurgurfirat.ui.list.SatelliteViewModel
 import com.app.firat.gurgurfirat.model.Position
import com.app.firat.gurgurfirat.model.PositionItemModel
import com.app.firat.gurgurfirat.model.SatelliteDetailItemModel
import com.app.firat.gurgurfirat.util.ReadExt
import com.app.firat.gurgurfirat.util.ReadExt.assetToString
import com.app.firat.gurgurfirat.util.bold
 import com.app.firat.gurgurfirat.util.launchPeriodic
 import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
 import kotlinx.coroutines.*
 import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args : DetailFragmentArgs by navArgs()
    private val positionList = arrayListOf<Position>()
    var positionOfSatellite : Position? =null
    lateinit var  job : Job

    private val viewModel : SatelliteViewModel by viewModels()

    override fun initView() {
        viewModel.getPositions()
        initData()
        viewModel.getSatelliteDetailFromJson(args.id)
    }

    override fun initObservers() {
        viewModel.positionListResponse.observe(viewLifecycleOwner){
            positionList.addAll(it)
        }

        viewModel.satelliteDetailResponse.observe(viewLifecycleOwner){ clickedItem->
            positionOfSatellite = positionList.find { s->s.id.toInt() == args.id } //find clicked item positions from id
            positionOfSatellite?.let { setPositionData(it) }
            binding.satelliteDetail = clickedItem
            binding.executePendingBindings()
            refreshPositionData()
        }

        viewModel.positionXYResponse.observe(viewLifecycleOwner){
            val newText = SpannableString( "Last Position: (${it.posX},${it.posY})")
                .bold("Last Position:")
            binding.lastPositionTv.text = newText
        }

    }


    private fun initData(){
        with(binding){
            nameTv.text = args.satelliteName
        }
    }


    private fun setPositionData(position : Position){
        viewModel.getXY(position)
    }

    private fun refreshPositionData(){
        Handler(Looper.getMainLooper()).postDelayed({
            job =  CoroutineScope(Dispatchers.IO).launchPeriodic(TimeUnit.SECONDS.toMillis(3)) {
                Handler(Looper.getMainLooper()).post {
                    positionOfSatellite?.let { setPositionData(it) }
                }
            }
        }, 3000)
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }



}