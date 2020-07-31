package br.com.raulreis.recursosmultimdia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recuperando_fotos_com_image_view.*

class RecuperandoFotosComImageViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperando_fotos_com_image_view)

        // Array com os nomes das imagens
        var nome = arrayOf("Leão", "Pinguim", "Urso", "Girafa", "Tigre", "Rato")

        //Adicionando o texto nos componentes
        textView1.text = nome[0]
        textView2.text = nome[1]
        textView3.text = nome[2]
        textView4.text = nome[3]
        textView5.text = nome[4]
        textView6.text = nome[5]

        // Declarando as variáveis das url das imagens
        var i1 = "http://clipartbarn.com/wp-content/uploads/2017/08/Clipart-animals-free-images.png"
        var i2 = "http://clipartbarn.com/wp-content/uploads/2017/08/Animal-clipart-black-and-white-free-images-2.png"
        var i3 = "http://clipartbarn.com/wp-content/uploads/2017/08/Free-animal-clipart-for-teachers-animales-predise-ados.png"
        var i4 = "http://clipartbarn.com/wp-content/uploads/2017/08/Animal-clipart-images-on.jpg"
        var i5 = "http://clipartbarn.com/wp-content/uploads/2017/08/Free-animal-clip-art-clipart-2.gif"
        var i6 = "http://clipartbarn.com/wp-content/uploads/2017/08/Clip-art-animals-woodland-images-on.jpg"

        // Utilizando o Glide para recuperar as imagens da web para o imageView
        // Realmente não achei outra maneira se não com o Glide
        Glide.with(this).load(i1).into(imageView1)
        Glide.with(this).load(i2).into(imageView2)
        Glide.with(this).load(i3).into(imageView3)
        Glide.with(this).load(i4).into(imageView4)
        Glide.with(this).load(i5).into(imageView5)
        Glide.with(this).load(i6).into(imageView6)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
