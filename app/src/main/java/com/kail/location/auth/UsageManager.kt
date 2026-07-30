package com.kail.location.auth

import android.content.Context
import com.kail.location.utils.KailLog

object UsageManager {
    private const val TAG = "UsageManager"

    private const val BOOT_READY_THRESHOLD_MS = 100_000L

    fun init(context: Context) {}

    fun systemReadiness(): Pair<Boolean, Int> {
        val uptimeMs = android.os.SystemClock.elapsedRealtime()
        if (uptimeMs >= BOOT_READY_THRESHOLD_MS) return true to 0
        val remainSec = ((BOOT_READY_THRESHOLD_MS - uptimeMs + 999) / 1000).toInt()
        return false to remainSec
    }

    fun bootReadyThresholdSeconds(): Int = (BOOT_READY_THRESHOLD_MS / 1000).toInt()

    suspend fun canStartSimulation(context: Context): Boolean {
        KailLog.i(context, TAG, "canStartSimulation=true: offline mode")
        return true
    }

    suspend fun consumeSimulation(context: Context): Boolean {
        KailLog.i(context, TAG, "consumeSimulation=true: offline mode")
        return true
    }
}
