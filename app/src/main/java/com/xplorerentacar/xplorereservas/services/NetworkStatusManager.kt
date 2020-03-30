package com.xplorerentacar.xplorereservas.services

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

/**
 * Created by César Andrade on 29/04/2019.
 */
object NetworkStatusManager {
    private var statusResponse: String? = null
    @JvmStatic
    fun status(context: Context): String? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        statusResponse = if (networkInfo != null && networkInfo.isConnected) {
            "Online"
            /*switch (networkInfo.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    // connected to wifi

                    WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();

                   Log.d("NetworkStatusManager", " SSID iguales: " + wifiInfo);

                    if (wifiInfo.getSSID().equals(context.getString(R.string.ssid)) ){
                        statusResponse = "SSID Correct";
                    }else
                        statusResponse = "SSID Incorrect";

                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    // connected to mobile data
                    statusResponse = "Mobile data";
                    break;
                default:
                    break;
            }*/
        } else {
            "Offline"
        }
        return statusResponse
    }
}