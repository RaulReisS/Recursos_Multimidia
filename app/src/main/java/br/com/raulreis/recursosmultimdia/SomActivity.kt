package br.com.raulreis.recursosmultimdia

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SomActivity : AppCompatActivity() {
    private var mario : MediaPlayer? = null
    private var luigi: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_som)
    }

    fun marioSound(view: View) {
        mario?.stop()
        mario = MediaPlayer.create(this, R.raw.mariosound)
        luigi?.stop()
        mario!!.start()
    }

    fun luigiSound(view: View) {
        luigi?.stop()
        luigi = MediaPlayer.create(this, R.raw.luigisound)
        mario?.stop()
        luigi!!.start()
    }
}