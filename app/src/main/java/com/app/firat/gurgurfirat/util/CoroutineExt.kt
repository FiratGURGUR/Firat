package com.app.firat.gurgurfirat.util

import kotlinx.coroutines.*

fun CoroutineScope.launchPeriodic(repeatMillis: Long, action: () -> Unit) : Job {
    return launch {
        while (isActive) {
            action()
            delay(repeatMillis)
        }
    }
}

