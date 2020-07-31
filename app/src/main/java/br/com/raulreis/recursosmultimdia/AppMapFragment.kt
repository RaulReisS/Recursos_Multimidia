package br.com.raulreis.recursosmultimdia

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AppMapFragment : SupportMapFragment() {
    //Informações dos pins fixos (Mesmos que o da tela de mapa)
    private val local = arrayOf(
        arrayOf("IME - Instituto de Matemática e Estatística da USP",
            "Rua do Matão, 1010\nButantã, São Paulo - SP\n05508-090"),
        arrayOf("Paróquia Nossa Senhora do Carmo",
            "Largo da Matriz, 85\nItaquera, São Paulo - SP\n08210-110"),
        arrayOf("MASP - Museu de Arte de São Paulo",
            "Av. Paulista, 1578\nBela Vista, São Paulo - SP\n01310-200")
    )

    private val ime = LatLng(-23.559501,-46.731300)
    private val carmo = LatLng(-23.539575,-46.453682)
    private val masp = LatLng(-23.561432, -46.655914)

    private  val viewModel: MapViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MapViewModel::class.java)
    }
    private var googleMap: GoogleMap? = null

    @SuppressLint("MissingPermission")
    override fun getMapAsync(callback: OnMapReadyCallback?) {
        super.getMapAsync {
            googleMap = it
            googleMap?.isMyLocationEnabled = true
            setupMap()
            callback?.onMapReady(googleMap)
        }
    }
    private fun setupMap() {
        googleMap?.run {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            uiSettings.isMapToolbarEnabled = false
            uiSettings.isZoomControlsEnabled = true
        }
        viewModel.getMapState()
            .observe(this, Observer { mapState ->
                if (mapState != null) {
                    updateMap(mapState)
                }
            })
    }
    private  fun updateMap(mapState: MapViewModel.MapState) {
        googleMap?.run {
            clear() //Remove quaisquer elementos que tenham sido adicionados ao mapa (Como marcadores e Polylines)
            val origin = mapState.origin
            if (origin != null) {
                addMarker(MarkerOptions()
                    .position(origin)
                    .title("Local Atual")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
                animateCamera(CameraUpdateFactory.newLatLngZoom(origin, 17.0f))
            }
            //Adicionando os pins fixos
            addMarker(MarkerOptions()
                .position(ime)
                .title(local[0][0])
                .snippet(local[0][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            )
            addMarker(MarkerOptions()
                .position(carmo)
                .title(local[1][0])
                .snippet(local[1][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
            )
            addMarker(MarkerOptions()
                .position(masp)
                .title(local[2][0])
                .snippet(local[2][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)))

        }
    }
}