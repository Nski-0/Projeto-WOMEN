package pro.exe.aplicativo;

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
    private Marker mdel1;
    private Marker mdel2;
    private Marker mdel3;


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

        mMap = googleMap;

        mMap.setMyLocationEnabled(true);

        //Criando imagem do icone
        Drawable genderDrawable = getDrawable(R.drawable.gender);

        //Converter a imagem para tipo Bitmap
        Bitmap genderIconBitmap = ((BitmapDrawable) genderDrawable).getBitmap();

        //Definindo o tamanho do icone
        Bitmap genderBitmapIcon = Bitmap.createScaledBitmap(genderIconBitmap, 45, 60, false);

        LatLng del1 = new LatLng(-3.0880988,-60.0186837);
        mdel1= mMap.addMarker(new MarkerOptions()
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

        LatLng manaus = new LatLng(-3.044653, -60.1071907);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(manaus));
    }
}
