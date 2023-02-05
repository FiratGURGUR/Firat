package com.app.firat.gurgurfirat.util


import android.os.Handler
import android.text.Editable
import android.text.TextWatcher

class EndTypingWatcher(
    var delayMillis: Long = DELAY,
    val action: (text : String) -> Unit
) : Handler(), TextWatcher {
    companion object {
        private const val DELAY: Long = 1000
    }

    var lastEditTime: Long = 0
    var text : String = ""

    private val finishCheckerRunnable = Runnable {
        if (System.currentTimeMillis() > lastEditTime + delayMillis - 500) {
            action.invoke(text)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        afterTextChanged(s.toString())
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    private fun afterTextChanged(s: String) {
        lastEditTime = System.currentTimeMillis()
        text = s
        postDelayed(finishCheckerRunnable, delayMillis)
    }

    private fun onTextChanged() {
        removeCallbacks(finishCheckerRunnable)
    }

}
