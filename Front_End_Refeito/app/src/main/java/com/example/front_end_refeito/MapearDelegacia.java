package com.example.front_end_refeito;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapearDelegacia extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker mdel1, mdel2, mdel3;
    private Marker mdel4, mdel5, mdel6, mdel7, mdel8, mdel9, mdel10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapear_delegacia);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);

        //Criando imagem do icone
        Drawable genderDrawable = getDrawable(R.drawable.gender);

        //Converter a imagem para tipo Bitmap
        Bitmap genderIconBitmap = ((BitmapDrawable) genderDrawable).getBitmap();

        //Definindo o tamanho do icone
        Bitmap genderBitmapIcon = Bitmap.createScaledBitmap(genderIconBitmap, 45, 60, false);

        LatLng del1 = new LatLng(-3.0880988,-60.0186837);
        mdel1 = mMap.addMarker(new MarkerOptions()
                .position(del1)
                .title("Delegacia da mulher.")
                .icon(BitmapDescriptorFactory.fromBitmap(genderBitmapIcon))
                .alpha(0.7f)
                .snippet("Av. Mário Ypiranga, 3395 - Parque Dez de Novembro,Manaus - AM , 69057-002"));

        LatLng del2 = new LatLng( -3.1488772,-60.0031077);
        mdel2 = mMap.addMarker(new MarkerOptions()
                .position(del2)
                .title("Delegacia da mulher - Zona Sul.")
                .icon(BitmapDescriptorFactory.fromBitmap(genderBitmapIcon))
                .alpha(0.7f)
                .snippet("R. Des. Filismino Soares, 155 - Colônia Oliveira Machado, Manaus - AM, 69070-620"));

        LatLng del3 = new LatLng( -3.0171389,-59.9389007);
        mdel3 = mMap.addMarker(new MarkerOptions()
                .position(del3)
                .title("DECCM Delegacia Especializada Em Crimes Contra A Mulher.")
                .icon(BitmapDescriptorFactory.fromBitmap(genderBitmapIcon))
                .alpha(0.7f)
                .snippet("R. Santa Ana, 398-490 - Cidade Nova, Manaus - AM, 69099-262"));

        LatLng del4 = new LatLng( -3.0002343,-59.9893775);
        mdel4 = mMap.addMarker(new MarkerOptions()
                .position(del4)
                .title("15° DIP - Distrito Integrado de Polícia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.7f)
                .snippet("Av.Margarita, s/n - Cidade Nova, Manaus-AM, 69097-305"));

        LatLng del5 = new LatLng( -3.0316358,-59.9869542);
        mdel5 = mMap.addMarker(new MarkerOptions()
                .position(del5)
                .title("6° DIP - Distrito Integrado de Polícia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.7f)
                .snippet("Av. Noel Nutels, 500 - Cidade Nova, Manaus-AM, 69090-000"));

        LatLng del6 = new LatLng( -3.0707579,-59.9963379);
        mdel6 = mMap.addMarker(new MarkerOptions()
                .position(del6)
                .title("23º DIP - Distrito Integrado de Polícia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.7f)
                .snippet("R.Jack London - Parque Dez de Novembro, Manaus-AM, 69054-070"));

        LatLng del7 = new LatLng( -3.118502,-59.9949412);
        mdel7 = mMap.addMarker(new MarkerOptions()
                .position(del7)
                .title("3º DIP - Distrito Integrado de Polícia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.7f)
                .snippet("R. Cel. Ferreira de Araújo, 417 - Petrópolis, Manaus - AM, 69063-000"));

        LatLng del8 = new LatLng( -3.0904084,-59.9815015);
        mdel8 = mMap.addMarker(new MarkerOptions()
                .position(del8)
                .title("11° DIP - Distrito Integrado de Polícia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.7f)
                .snippet("Próximo ao n° 149, R. Santo Antônio, S/N - Coroado, AM, 69080-540"));

        LatLng del9 = new LatLng( -3.1184664,-60.0115191);
        mdel9 = mMap.addMarker(new MarkerOptions()
                .position(del9)
                .title("1° DIP - Distrito Integrado de Polícia")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.7f)
                .snippet("Av.Duque de Caxias, 1928 - Praça 14 de Janeiro, Manaus-AM, 69020-141"));

        LatLng del10 = new LatLng( -3.0861193,-60.0298912);
        mdel10 = mMap.addMarker(new MarkerOptions()
                .position(del10)
                .title("Delegacia Geral da Polícia Civil")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.7f)
                .snippet("Av.Pedro Teixeira, 180-Dom Pedro, Manaus-AM, 69040-000"));

        LatLng manaus = new LatLng(-3.044653, -60.1071907);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(manaus));
    }
}
