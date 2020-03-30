package com.xplorerentacar.xplorereservas

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.xplorerentacar.xplorereservas.services.NetworkStatusManager.status
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        delaySplash()
    }

    private fun delaySplash() {
        val loading = 3000
        Handler().postDelayed({
            when (status(Objects.requireNonNull(applicationContext))) {
                "Offline" -> showMensaje(getDrawable(R.drawable.baseline_signal_wifi_off_24), getString(R.string.sin_internet))
                "Online" -> try {
                    val home = Intent(applicationContext, MainActivity::class.java)
                    startActivity(home)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, applicationContext.getString(R.string.content_error), Toast.LENGTH_LONG).show()
                }
            }
        }, loading.toLong())
    }

    fun retry(view: View?) {
        contenedorSplash!!.visibility = View.VISIBLE
        contenedorMensaje!!.visibility = View.GONE
        delaySplash()
    }

    private fun showMensaje(imagMensaje: Drawable?, mensaje: String) {
        imagenMensaje!!.setImageDrawable(imagMensaje)
        tvMensaje!!.text = mensaje
        contenedorSplash!!.visibility = View.GONE
        contenedorMensaje!!.visibility = View.VISIBLE
    }
}