package br.com.raulreis.recursosmultimdia

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    override fun onMapReady(googleMap: GoogleMap) {
        //Vincula o objeto
        mMap = googleMap

        //Preparando informações sobre locais
        val local = arrayOf(
            arrayOf("IME - Instituto de Matemática e Estatística da USP",
                "Rua do Matão, 1010\nButantã, São Paulo - SP\n05508-090"),
            arrayOf("Paróquia Nossa Senhora do Carmo",
                "Largo da Matriz, 85\nItaquera, São Paulo - SP\n08210-110"),
            arrayOf("MASP - Museu de Arte de São Paulo",
                "Av. Paulista, 1578\nBela Vista, São Paulo - SP\n01310-200")
        )

        val ime = LatLng(-23.559501,-46.731300)
        val carmo = LatLng(-23.539575,-46.453682)
        val masp = LatLng(-23.561432, -46.655914)

        //Inserindo os marcadores no mapa
        mMap.addMarker(MarkerOptions()
            .position(ime)
            .title(local[0][0])
            .snippet(local[0][1])
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )

        mMap.addMarker(MarkerOptions()
            .position(carmo)
            .title(local[1][0])
            .snippet(local[1][1])
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        mMap.addMarker(MarkerOptions()
            .position(masp)
            .title(local[2][0])
            .snippet(local[2][1])
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ime, 12.5F))

        //Configuração de exibição de títuo e informações da maneira personalizada
        mMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(p0: Marker?): View? {
                return null
            }

            override fun getInfoContents(marker: Marker?): View {
                val info = LinearLayout(applicationContext)
                info.orientation = LinearLayout.VERTICAL

                //Título
                val title = TextView(applicationContext)
                title.setTextColor(Color.BLACK)
                title.gravity = Gravity.LEFT
                title.setTypeface(null, Typeface.BOLD)
                if (marker != null) {
                    title.text = marker.title
                }

                //Complemento
                val snippet = TextView(applicationContext)
                snippet.setTextColor(Color.GRAY)
                if (marker != null) {
                    snippet.text = marker.snippet
                }

                //Adiciona o Título e o complemento na marca
                info.addView(title)
                info.addView(snippet)

                return info
            }
        })
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(ime))

        /* Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        */
    }


}