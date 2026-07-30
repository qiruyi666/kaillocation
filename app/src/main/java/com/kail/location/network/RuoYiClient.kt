package com.kail.location.network

import okhttp3.OkHttpClient

object RuoYiClient {
    var baseUrl: String = "offline://local"
    val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

    data class AuthResult(
        val token: String,
        val email: String,
        val id: String,
        val verified: Boolean
    )

    fun checkSimulation(token: String): Result<Int> {
        return Result.success(Int.MAX_VALUE)
    }

    fun useSimulation(token: String): Result<Unit> {
        return Result.success(Unit)
    }

    fun sendMailCode(mail: String, scene: Int): Result<Unit> {
        return Result.success(Unit)
    }

    fun loginByMail(mail: String, code: String): Result<AuthResult> {
        return Result.success(
            AuthResult(
                token = "offline-token",
                email = mail.ifBlank { "offline@local" },
                id = "offline-user",
                verified = true
            )
        )
    }

    data class SubscriptionStatus(
        val active: Boolean,
        val planName: String,
        val expiresAt: String,
        val daysRemaining: Int
    )

    data class SubscriptionPlan(
        val id: Long,
        val name: String,
        val description: String,
        val price: Int,
        val currency: String,
        val billingInterval: String,
        val billingIntervalCount: Int,
        val trialDays: Int
    )

    suspend fun getPlans(token: String): Result<List<SubscriptionPlan>> {
        return Result.success(emptyList())
    }

    data class NoticeInfo(
        val id: Long,
        val title: String,
        val type: Int,
        val content: String,
        val createTime: String
    )

    suspend fun getNoticeList(): Result<List<NoticeInfo>> {
        return Result.success(emptyList())
    }

    suspend fun getSubscriptionStatus(token: String): Result<SubscriptionStatus> {
        return Result.success(
            SubscriptionStatus(
                active = true,
                planName = "Offline",
                expiresAt = "2099-12-31 23:59:59",
                daysRemaining = 99999
            )
        )
    }
}
