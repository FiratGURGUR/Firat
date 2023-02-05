package com.app.firat.gurgurfirat.util

import android.text.SpannableString
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.firat.gurgurfirat.R
import com.app.firat.gurgurfirat.model.SatelliteDetailItemModel
import com.bumptech.glide.Glide

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadStatus")
    fun loadStatus(view: ImageView, isActive: Boolean) {
        Glide.with(view.context).load(if(isActive) R.drawable.ic_active_circle else R.drawable.ic_passive_circle).into(view)
    }

    @JvmStatic
    @BindingAdapter("setTextStatus")
    fun setTextStatus(view: TextView, isActive: Boolean) {
        view.text =  if (isActive) "Active" else "Passive"
    }


    @JvmStatic
    @BindingAdapter("setHeightMass")
    fun setHeightMass(view: TextView, item: SatelliteDetailItemModel?) {
        val newText = SpannableString( "Height/Mass:${item?.height}/${item?.mass}")
            .bold("Height/Mass:")
        view.text = newText
    }


    @JvmStatic
    @BindingAdapter("setDate")
    fun setDate(view: TextView, date: String?) {
        view.text = date?.formatToDate("yyyy-MM-dd")?.formatToString("dd.MM.yyyy") ?: ""
    }


    @JvmStatic
    @BindingAdapter("setCost")
    fun setCost(view: TextView, item: SatelliteDetailItemModel?) {
        val costPerLaunch = item?.cost_per_launch?.formatDecimalSeparator()
        val newText = SpannableString( "Cost:${costPerLaunch}")
            .bold("Cost:")
        view.text = newText
    }


    @JvmStatic
    @BindingAdapter("setColorFromStatus")
    fun setColorFromStatus(view: TextView, isActive: Boolean) {
        val colorId = if (isActive) R.color.black else R.color.gray
        view.setTextColor(view.context.getColor(colorId))
    }

}