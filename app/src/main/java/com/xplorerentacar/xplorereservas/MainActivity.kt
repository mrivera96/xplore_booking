package com.xplorerentacar.xplorereservas

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.webkit.WebView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.xplorerentacar.xplorereservas.services.CustomWebViewClient
import com.xplorerentacar.xplorereservas.services.NetworkStatusManager.status
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    /*var imagenMensaje: ImageView? = null
    var contenedorMensaje: LinearLayout? = null

    var tvMensaje: TextView? = null

    var contenedorPadre: RelativeLayout? = null

    var contenedorWebView: RelativeLayout? = null

    var progress: WebView? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()

        checkNetwork()
    }

    private fun initComponents() {
        progress!!.setBackgroundColor(Color.TRANSPARENT)
        progress!!.loadUrl("file:///android_asset/html/loader.html")
        val webView = findViewById<View>(R.id.loader) as WebView
        webView.webViewClient = CustomWebViewClient(progress!!)
        webView.setBackgroundColor(Color.TRANSPARENT)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://reservas.xplorerentacar.com")


    }


    /***
     * VERIFICA SI HAY ACCESO A INTERNET
     */
     private fun checkNetwork() {
        when (status(Objects.requireNonNull(applicationContext))) {
            "Offline" -> showMensaje(resources.getDrawable(R.drawable.baseline_signal_wifi_off_24), getString(R.string.sin_internet))
            "Online" ->
                showWebView()
        }
    }

    fun MainActivity.showNoInternet() {
        contenedorWebView!!.visibility = View.GONE
        contenedorMensaje!!.visibility = View.VISIBLE
    }

    /***
     * MUESTRA UN MENSAJE EN CASO DE ERROR
     */
     private fun showMensaje(imagMensaje: Drawable, mensaje: String) {
        imagenMensaje!!.setImageDrawable(imagMensaje)
        tvMensaje!!.text = mensaje
        TransitionManager.beginDelayedTransition(contenedorPadre)
        contenedorWebView!!.visibility = View.GONE
        contenedorMensaje!!.visibility = View.VISIBLE
    }

    /***
     * MUESTRA EL WEBVIEW QUE CARGA LA PÁGINA DE RESERVACIONES
     */
     private fun showWebView() {
        TransitionManager.beginDelayedTransition(contenedorPadre)
        contenedorMensaje!!.visibility = View.GONE
    }
}