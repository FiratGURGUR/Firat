package com.app.firat.gurgurfirat.ui.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.app.firat.gurgurfirat.base.BaseFragment
import com.app.firat.gurgurfirat.databinding.FragmentDetailBinding
import com.app.firat.gurgurfirat.ui.list.SatelliteViewModel
import com.app.firat.gurgurfirat.model.Position
import com.app.firat.gurgurfirat.model.SatelliteDetailItemModel
import com.app.firat.gurgurfirat.util.bold
import com.app.firat.gurgurfirat.util.launchPeriodic
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: SatelliteViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private val positionList = arrayListOf<Position>()
    private var positionOfSatellite: Position? = null
    private lateinit var job: Job

    override fun initView() {
        viewModel.getPositions()
        initData()
        viewModel.checkItem(args.id)
    }

    override fun initObservers() {
        viewModel.positionListResponse.observe(viewLifecycleOwner) {
            positionList.addAll(it)
        }

        viewModel.satelliteDetailResponse.observe(viewLifecycleOwner) { clickedItem ->
            positionOfSatellite =
                positionList.find { s -> s.id.toInt() == args.id } //find clicked item positions from id
            positionOfSatellite?.let { setPositionData(it) }
            binding.satelliteDetail = clickedItem
            binding.executePendingBindings()
            refreshPositionData()
            addSatelliteToDb(clickedItem)
        }

        viewModel.positionXYResponse.observe(viewLifecycleOwner) {
            val newText =
                SpannableString("Last Position: (${it.posX},${it.posY})").bold("Last Position:")
            binding.lastPositionTv.text = newText
        }

        viewModel.satelliteDaoDetailResponse.observe(viewLifecycleOwner) { item ->
            positionOfSatellite = positionList.find { s -> s.id.toInt() == args.id }
            positionOfSatellite?.let { setPositionData(it) }
            binding.satelliteDetail = item
            binding.executePendingBindings()
            refreshPositionData()
        }

    }


    private fun initData() {
        with(binding) {
            nameTv.text = args.satelliteName
        }
    }


    private fun setPositionData(position: Position) {
        viewModel.getXY(position)
    }

    private fun refreshPositionData() {


        job = CoroutineScope(Dispatchers.IO).launchPeriodic(TimeUnit.SECONDS.toMillis(3)) {
            Handler(Looper.getMainLooper()).post {
                positionOfSatellite?.let { setPositionData(it) }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private fun addSatelliteToDb(satellite: SatelliteDetailItemModel) {
        viewModel.addSatellite(satellite)
    }


}