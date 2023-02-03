package com.app.firat.gurgurfirat.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.firat.gurgurfirat.R
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
    @BindingAdapter("setColorFromStatus")
    fun setColorFromStatus(view: TextView, isActive: Boolean) {
        val colorId = if (isActive) R.color.black else R.color.gray
        view.setTextColor(view.context.getColor(colorId))
    }

}