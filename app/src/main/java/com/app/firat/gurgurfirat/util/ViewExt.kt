package com.app.firat.gurgurfirat.util

import android.os.SystemClock
import android.view.View

class CustomClickListenerExt(
    private var defaultInterval: Int = 500,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

fun View.setCustomClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = CustomClickListenerExt {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}


