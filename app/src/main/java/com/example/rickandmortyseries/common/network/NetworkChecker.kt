package com.example.rickandmortyseries.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import androidx.core.content.getSystemService

/**
 * Utility class for checking internet connection.
 * @param context The application context.
 */
internal class NetworkChecker(context: Context) {

    private val connectivityManager: ConnectivityManager? = context.getSystemService()

    /**
     * Check if the device has internet connection.
     */
    fun hasInternet(): Boolean {
        val capabilities = connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NET_CAPABILITY_INTERNET) ?: false
    }
}