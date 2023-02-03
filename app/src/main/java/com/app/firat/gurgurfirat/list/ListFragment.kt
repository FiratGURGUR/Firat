package com.app.firat.gurgurfirat.list

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.app.firat.gurgurfirat.R
import com.app.firat.gurgurfirat.base.BaseFragment
import com.app.firat.gurgurfirat.databinding.FragmentListBinding
import com.app.firat.gurgurfirat.list.adapter.SatelliteAdapter
import com.app.firat.gurgurfirat.model.SatelliteListItemModel
import com.app.firat.gurgurfirat.util.setDivider
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private lateinit var satelliteAdapter : SatelliteAdapter
    private val satelliteList = arrayListOf<SatelliteListItemModel>()

    override fun initView() {
        setupAdapter()
        fetchList()
    }

    override fun initObservers() {
    }

    //attempt for adapter and vh
    private fun fetchList(){
        satelliteList.clear()
        satelliteList.add(SatelliteListItemModel(1,false,"Starship-1"))
        satelliteList.add(SatelliteListItemModel(2,true,"Dragon-1"))
        satelliteList.add(SatelliteListItemModel(3,true,"Starship-3"))

        satelliteAdapter.let {
           it.submitList(satelliteList)
        }
    }

    private fun setupAdapter(){
        satelliteAdapter = SatelliteAdapter(clickCallback = { clickedItem,position->
            findNavController().navigate(R.id.action_global_detailFragment, bundleOf("id" to clickedItem.id))
        })


        binding.satelliteRecyclerview.apply {
            adapter = satelliteAdapter
            setDivider()
        }
    }

}