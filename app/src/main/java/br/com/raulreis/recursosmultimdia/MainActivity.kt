package br.com.raulreis.recursosmultimdia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Trabalhando com click longo (Talvez tenha outras formas)
        btnMapas.setOnLongClickListener(object: View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                startActivity(Intent(this@MainActivity, MapaActivity::class.java))
                return true  }
        })

    }

    fun recuperando_fotos_com_imageview(view: View) {
        startActivity(Intent(this@MainActivity, RecuperandoFotosComImageViewActivity::class.java))
    }

    fun utilizando_mapas_mapview(view: View) {
        startActivity(Intent(this@MainActivity, MapsActivity::class.java))
    }

    fun localizacao_do_usuario_com_location_api(view: View) {
        startActivity(Intent(this@MainActivity, LocalizacaoMapsActivity::class.java))
    }

    fun usando_webview_em_seus_apps(view: View) {
        startActivity(Intent(this@MainActivity, WebViewActivity::class.java))
    }

    fun sons_audiomanager(view: View) {
        startActivity(Intent(this@MainActivity, SomActivity::class.java))
    }

    fun videos_mediaplayer(view: View) {
        startActivity(Intent(this@MainActivity, VideoActivity::class.java))
    }

    fun animacao_em_views_com_view_animation(view: View) {
        startActivity(Intent(this@MainActivity, AnimacaoActivity::class.java))
    }
}
