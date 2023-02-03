package com.app.firat.gurgurfirat.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.firat.gurgurfirat.base.BaseListAdapter
import com.app.firat.gurgurfirat.model.SatelliteListItemModel

class SatelliteAdapter(
    private val clickCallback: ((SatelliteListItemModel, Int) -> Unit)?,
) : BaseListAdapter<SatelliteListItemModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SatelliteItemVH -> {
                holder.bind(
                    satellite_Item = getItem(position),
                    menuClickCallback = clickCallback
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return SatelliteItemVH(parent, inflater)
    }

}