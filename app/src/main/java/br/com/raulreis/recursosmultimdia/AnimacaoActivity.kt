package br.com.raulreis.recursosmultimdia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_animacao.*

class AnimacaoActivity : AppCompatActivity() {

    internal var animations = arrayOf("Slide Down", "Blink", "Fade", "Zoom", "Twist", "move")

    internal var animationID = intArrayOf(R.animator.slide_down, R.animator.blink, R.animator.fade,
        R.animator.zoom, R.animator.twist, R.animator.move)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animacao)

        lvLista.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animations)

        lvLista.onItemClickListener =
            AdapterView.OnItemClickListener{ parent, view, position, id ->
                val animation = AnimationUtils.loadAnimation(this@AnimacaoActivity, animationID[position])

                imgAndroid.startAnimation(animation)
            }
    }
}