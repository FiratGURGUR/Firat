package com.app.firat.gurgurfirat.util

import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan
import java.text.SimpleDateFormat
import java.util.*

fun SpannableString.bold(text: String): SpannableString {
    try {
        val startPos = this.indexOf(text)
        val endPos = this.indexOf(text) + text.length
        this.setSpan(StyleSpan(Typeface.BOLD), startPos, endPos, 0)
    } catch (_: Exception) {
    }
    return this
}


fun Int.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(".")
        .reversed()
}

fun Date.formatToString(format: String): String {
    val formatter = SimpleDateFormat(format, Locale("tr"))
    //return the formatted date string
    return formatter.format(this)
}

fun String.formatToDate(format: String): Date {
    val formatter = SimpleDateFormat(format)
    return formatter.parse(this) as Date
}