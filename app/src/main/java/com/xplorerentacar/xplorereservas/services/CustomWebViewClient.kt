package com.xplorerentacar.xplorereservas.services

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.xplorerentacar.xplorereservas.MainActivity

class CustomWebViewClient(private val progress: WebView) : WebViewClient() {
    
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean { // TODO Auto-generated method stub
        view.loadUrl(url)
        return true
    }

    /***
     * INTERCEPTA ERRORES AL CARGAR LA WEB
     */
    override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
        view!!.loadUrl("file:///android_asset/html/errorPage.html")
    }

    override fun onPageFinished(view: WebView, url: String) { // TODO Auto-generated method stub
        super.onPageFinished(view, url)
        progress.visibility = View.GONE
    }

    init {
        progress.visibility = View.VISIBLE
    }
}