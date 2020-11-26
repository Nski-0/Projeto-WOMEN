package com.example.women;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TelaAlertar extends AppCompatActivity {

    private Button btnLigar, btnEnviar;
    private Intent IrLigar, IrMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alertar);

        btnLigar= (Button) findViewById(R.id.ligar);
        btnEnviar= (Button) findViewById(R.id.enviarMensagem);

        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IrLigar = new Intent(Intent.ACTION_DIAL);
                IrLigar.setData(Uri.parse("tel:" + "180"));
                if (IrLigar.resolveActivity(getPackageManager()) != null) {
                    startActivity(IrLigar);
                }
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMyLastLocation();

        }});
    }

    /*private void zoomMyCuurentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            double lat = location.getLatitude();
            double longi = location.getLongitude();
            LatLng latLng = new LatLng(lat,longi);
        } else {
            setMyLastLocation();
        }
    }*/

    private void setMyLastLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    double lat = location.getLatitude();
                    double longi = location.getLongitude();
                    LatLng latLng = new LatLng(lat,longi);
                    enviarMensagemWhats(latLng);


                }else{
                    enviarMensagemWhats(null);
                }
            }
        });
    }

    private void enviarMensagemWhats(LatLng latLng){

        String mensagem;
        if(latLng== null){
            mensagem = "estou em perigo nao sei minha localizaçao.";
        }else{
            mensagem = "Estou em perigo minha localizaçao:" + latLng.latitude + "," + latLng.longitude;
        }

            try {
                PackageManager packageManager = getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=" + "+559291939930" + "&text=" + URLEncoder.encode(mensagem, "UTF-8");
                i.setPackage("com.whatsapp");
                i.setData(Uri.parse(url));
                if (i.resolveActivity(packageManager) != null) {
                    startActivity(i);
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
}

