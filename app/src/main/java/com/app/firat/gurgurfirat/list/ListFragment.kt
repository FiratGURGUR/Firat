package com.app.firat.gurgurfirat.list

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.app.firat.gurgurfirat.R
import com.app.firat.gurgurfirat.base.BaseFragment
import com.app.firat.gurgurfirat.databinding.FragmentListBinding
import com.app.firat.gurgurfirat.list.adapter.SatelliteAdapter
import com.app.firat.gurgurfirat.model.SatelliteListItemModel
import com.app.firat.gurgurfirat.util.ReadExt
import com.app.firat.gurgurfirat.util.onTextChanged
import com.app.firat.gurgurfirat.util.setDivider
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private lateinit var satelliteAdapter : SatelliteAdapter

    override fun initView() {
        setupAdapter()
        fetchList()

        binding.searchBar.onTextChanged { text->
            satelliteAdapter.filter.filter(text)
        }

    }

    override fun initObservers() {
    }


    private fun fetchList(){
        val type = object : TypeToken<List<SatelliteListItemModel>>() {}.type
        val result: List<SatelliteListItemModel> = ReadExt.readDataListToList(requireContext(),"satellite-list.json",type)
        satelliteAdapter.let {
           it.putData(result.toMutableList())
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