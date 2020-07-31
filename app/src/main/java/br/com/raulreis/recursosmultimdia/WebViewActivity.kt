package br.com.raulreis.recursosmultimdia

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        wbvGoogle.settings.javaScriptEnabled = true

        // Carregar página a partir do endereço
        home()

        /*  Ao solicitar uma nova URL (ex., clicar e, algum link) o WebView chama o browser padrão
            O seguinte recho de código impede que isso aconteça, carregando a nova URL no próprio
            WebView que solicitou. A partir da API 24 essa versão do código foi descontinuada, por
            isso contornamos com o código seguinte.
        */
        wbvGoogle.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (view != null) {
                    view.loadUrl(url)
                }
                return false
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (request != null) {
                    view?.loadUrl(request.url.toString())
                }
                return false
            }

            // Atualizar a URL na barra
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (url != null) {
                        edtUrl.setText(url)
                }
            }
        }

        // Listener para quando a tecla enter for clicada realize a mesma função
        // que o botão "Ir"/"Go"
        edtUrl.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                    wbvGoogle.loadUrl(treatment(edtUrl.editableText.toString()))
                    hideKeyboard()
                    return@OnKeyListener true
                }
            }
            false
        })
    }

    // Configurando ActionBar personalizada
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //Tratando as açoes dos itens da ActionBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_home -> {
                home()
                true
            }

            R.id.action_back -> {
                back()
                true
            }

            R.id.action_forward -> {
                forward()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun home() {
        // Carregar página a partir do endereço
        wbvGoogle.loadUrl("https://www.google.com.br/")
        hideKeyboard()
    }

    // Função Voltar
    private fun back() {
        if (wbvGoogle.canGoBack())
            wbvGoogle.goBack()
        hideKeyboard()
    }

    // Função Avançar
    private  fun forward() {
        if(wbvGoogle.canGoForward())
            wbvGoogle.goForward()
        hideKeyboard()
    }

    // Função voltar no botão Back
    override fun onBackPressed() {
        if (wbvGoogle.canGoBack()) {
            wbvGoogle.goBack()
        }
        else {
            super.onBackPressed()
        }
        hideKeyboard()
    }

    /* Tratamento para garantir que seja enviada uma url válida para o WebView*/
    private fun treatment(url: String) : String {
        if (!url.contains("http://") && !url.contains("https://")) {
            if (url.contains("www.")) {
                return "http://$url"
            }
            return "https://www.google.com/search?q=${url.replace(" ", "+")}"
        }
        return url
    }

    // Carregar url
    fun go(view: View) {
        wbvGoogle.loadUrl(treatment(edtUrl.editableText.toString()))
        hideKeyboard()
    }

    // Método que esconde o teclado
    private fun hideKeyboard() {
        val view: View? = currentFocus
        view?.let {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    // Método criado para garantir que quando o usuário clica fora do teclado
    // ou do EditText o teclado seja ocultado
    fun layoutclick(view: View) {
        hideKeyboard()
    }
}