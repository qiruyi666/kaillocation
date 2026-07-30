package com.kail.location.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf

object AuthManager {

    private const val PREFS_NAME = "auth_prefs"
    private const val KEY_TOKEN = "auth_token"
    private const val KEY_EMAIL = "auth_email"
    private const val KEY_USER_ID = "auth_user_id"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_SUBSCRIBED = "is_subscribed"
    private const val KEY_SUB_EXPIRES = "sub_expires_at"

    private const val OFFLINE_TOKEN = "offline-token"
    private const val OFFLINE_EMAIL = "offline@local"
    private const val OFFLINE_USER_ID = "offline-user"

    private lateinit var prefs: SharedPreferences

    private val _isLoggedIn = mutableStateOf(true)
    private val _email = mutableStateOf(OFFLINE_EMAIL)
    private val _isSubscribed = mutableStateOf(true)

    val isLoggedIn: Boolean get() = _isLoggedIn.value
    val email: String get() = _email.value
    val isSubscribed: Boolean get() = _isSubscribed.value
    val isLoggedInState get() = _isLoggedIn
    val emailState get() = _email
    val isSubscribedState get() = _isSubscribed

    var token: String?
        get() = prefs.getString(KEY_TOKEN, OFFLINE_TOKEN)
        private set(value) = prefs.edit().putString(KEY_TOKEN, value ?: OFFLINE_TOKEN).apply()

    var userId: String?
        get() = prefs.getString(KEY_USER_ID, OFFLINE_USER_ID)
        private set(value) = prefs.edit().putString(KEY_USER_ID, value ?: OFFLINE_USER_ID).apply()

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        ensureOfflineIdentity()
    }

    private fun ensureOfflineIdentity() {
        prefs.edit()
            .putString(KEY_TOKEN, OFFLINE_TOKEN)
            .putString(KEY_EMAIL, OFFLINE_EMAIL)
            .putString(KEY_USER_ID, OFFLINE_USER_ID)
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .putBoolean(KEY_SUBSCRIBED, true)
            .putString(KEY_SUB_EXPIRES, "2099-12-31 23:59:59")
            .apply()

        _isLoggedIn.value = true
        _email.value = OFFLINE_EMAIL
        _isSubscribed.value = true
    }

    fun saveAuth(token: String, email: String, userId: String) {
        prefs.edit()
            .putString(KEY_TOKEN, token.ifBlank { OFFLINE_TOKEN })
            .putString(KEY_EMAIL, email.ifBlank { OFFLINE_EMAIL })
            .putString(KEY_USER_ID, userId.ifBlank { OFFLINE_USER_ID })
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .putBoolean(KEY_SUBSCRIBED, true)
            .putString(KEY_SUB_EXPIRES, "2099-12-31 23:59:59")
            .apply()

        _isLoggedIn.value = true
        _email.value = email.ifBlank { OFFLINE_EMAIL }
        _isSubscribed.value = true
    }

    fun updateSubscription(subscribed: Boolean, expiresAt: String) {
        prefs.edit()
            .putBoolean(KEY_SUBSCRIBED, true)
            .putString(KEY_SUB_EXPIRES, if (expiresAt.isBlank()) "2099-12-31 23:59:59" else expiresAt)
            .apply()
        _isSubscribed.value = true
    }

    fun isSubscriptionActive(): Boolean = true

    fun clearAuth() {
        ensureOfflineIdentity()
    }
}
