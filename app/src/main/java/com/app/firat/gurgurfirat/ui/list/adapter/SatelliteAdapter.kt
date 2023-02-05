package com.app.firat.gurgurfirat.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.app.firat.gurgurfirat.base.BaseListAdapter
import com.app.firat.gurgurfirat.model.SatelliteListItemModel
import java.util.*

class SatelliteAdapter(
    private val clickCallback: ((SatelliteListItemModel, Int) -> Unit)?,
) : BaseListAdapter<SatelliteListItemModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
),Filterable {
    private var list = mutableListOf<SatelliteListItemModel>()
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

    fun putData(list: MutableList<SatelliteListItemModel>?){
        this.list = list!!
        submitList(list)
    }


    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<SatelliteListItemModel>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {
                    if (item.name.lowercase(Locale.getDefault()).contains(constraint.toString().lowercase(Locale.getDefault()))) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(filterResults?.values as MutableList<SatelliteListItemModel>)
        }

    }
}