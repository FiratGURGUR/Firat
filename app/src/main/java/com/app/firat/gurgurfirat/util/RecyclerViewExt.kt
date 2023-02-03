package com.app.firat.gurgurfirat.util

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration

fun RecyclerView.setDivider(){
    val divider = MaterialDividerItemDecoration(this.context, MaterialDividerItemDecoration.VERTICAL)
    divider.isLastItemDecorated = false
    this.addItemDecoration(divider)
}