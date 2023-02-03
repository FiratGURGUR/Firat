package com.app.firat.gurgurfirat.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.firat.gurgurfirat.base.BaseViewHolder
import com.app.firat.gurgurfirat.databinding.ItemSatelliteLayoutBinding
import com.app.firat.gurgurfirat.model.SatelliteListItemModel
import com.app.firat.gurgurfirat.util.setCustomClickListener

class SatelliteItemVH (parent: ViewGroup,
                       inflater: LayoutInflater
) : BaseViewHolder<ItemSatelliteLayoutBinding>(
    binding = ItemSatelliteLayoutBinding.inflate(inflater, parent, false)
) {

    fun bind(
        satellite_Item: SatelliteListItemModel,
        menuClickCallback : ((SatelliteListItemModel,Int) -> Unit)? = null
    ) {
        with(binding) {
            satellite = satellite_Item
            mainLayout.setCustomClickListener {
                menuClickCallback?.invoke(satellite_Item,bindingAdapterPosition)
            }
            executePendingBindings()
        }
    }

}