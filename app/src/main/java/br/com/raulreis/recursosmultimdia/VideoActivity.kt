package br.com.raulreis.recursosmultimdia

import android.content.res.ColorStateList
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import com.google.android.gms.common.config.GservicesValue.value
import kotlinx.android.synthetic.main.activity_video.*
import java.time.chrono.JapaneseEra.values

class VideoActivity : AppCompatActivity() {

    private var mediacontroller: MediaController? = null
    private var uri: Uri? = null
    private var video: Uri? = null

    private var isContinously = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        mediacontroller = MediaController(this)
        mediacontroller!!.setAnchorView(vVideo)


        val uriVideo = """android.resource://${this.packageName}/${R.raw.nevergonnagiveyouup}"""
        val uriPath = "https://ia800209.us.archive.org/24/items/WildlifeSampleVideo/Wildlife.mp4"

        uri = Uri.parse(uriPath)
        video = Uri.parse(uriVideo)

        vVideo.setOnCompletionListener {
            if (isContinously) {
                vVideo!!.start()
            }
        }

        vVideo.setOnPreparedListener {
            progress.visibility = View.GONE
        }
    }

    fun stop(view: View) {
        vVideo.pause()
        btnPlay.backgroundTintList = getColorStateList(R.color.colorButtomBackground)
        btnStop.backgroundTintList = getColorStateList(R.color.colorAccent)
    }

    fun play(view: View) {
        vVideo.start()
        btnPlay.backgroundTintList = getColorStateList(R.color.colorAccent)
        btnStop.backgroundTintList = getColorStateList(R.color.colorButtomBackground)
    }

    fun load(view: View) {
        progress.visibility = View.VISIBLE
        vVideo.setMediaController(mediacontroller)
        if (view.id == R.id.btnOnce) {
            vVideo.setVideoURI(uri)
            btnOnce.backgroundTintList = getColorStateList(R.color.colorAccent)
            btnNever.backgroundTintList = getColorStateList(R.color.colorButtomBackground)
        }
        else {
            vVideo.setVideoURI(video)
            btnNever.backgroundTintList = getColorStateList(R.color.colorAccent)
            btnOnce.backgroundTintList = getColorStateList(R.color.colorButtomBackground)
        }
        vVideo.requestFocus()
        play(view)
    }

    fun continuously(view: View) {
        isContinously = !isContinously

        if(isContinously)
            btnConti.backgroundTintList = getColorStateList(R.color.colorAccent)
        else
            btnConti.backgroundTintList = getColorStateList(R.color.colorButtomBackground)
    }
}