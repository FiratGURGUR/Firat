package com.app.firat.gurgurfirat.ui.list

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.firat.gurgurfirat.R
import com.app.firat.gurgurfirat.base.BaseFragment
import com.app.firat.gurgurfirat.databinding.FragmentListBinding
import com.app.firat.gurgurfirat.ui.list.adapter.SatelliteAdapter
import com.app.firat.gurgurfirat.model.SatelliteListItemModel
import com.app.firat.gurgurfirat.util.*
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private lateinit var satelliteAdapter : SatelliteAdapter
    private val viewModel : SatelliteViewModel by viewModels()

    override fun initView() {
        setupAdapter()
        viewModel.getSatelliteListFromJson()
        binding.searchBar.addTextChangedListener(EndTypingWatcher(delayMillis = 500) {
            satelliteAdapter.filter.filter(it)
        })
    }

    override fun initObservers() {
        viewModel.satelliteListResponse.observe(viewLifecycleOwner){
            satelliteAdapter.putData(it.toMutableList())
        }
    }


    private fun setupAdapter(){
        satelliteAdapter = SatelliteAdapter(clickCallback = { clickedItem,position->
            findNavController().navigate(R.id.action_global_detailFragment, bundleOf("id" to clickedItem.id,"satellite_name" to clickedItem.name))
        })


        binding.satelliteRecyclerview.apply {
            adapter = satelliteAdapter
            setDivider()
        }
    }

}