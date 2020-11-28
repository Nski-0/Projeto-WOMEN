package com.example.women;

import androidx.annotation.NonNull;
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
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.women.models.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TelaAlertar extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference myRef;
    FirebaseUser user;
    private String contato;
    private User userData;
    private Button btnLigar, btnEnviar;
    private Intent IrLigar, IrMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_alertar);

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();

        btnLigar= (Button) findViewById(R.id.ligar);
        btnEnviar= (Button) findViewById(R.id.enviarMensagem);

        getData();

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
        String numero = contato;

        if(latLng== null){
            mensagem = "estou em perigo nao sei minha localizaçao.";
        }else{
            mensagem = "Estou em perigo minha localizaçao:" + latLng.latitude + "," + latLng.longitude;
        }

            try {
                
                PackageManager packageManager = getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);
                String url = "https://api.whatsapp.com/send?phone=" + numero + "&text=" + URLEncoder.encode( mensagem, "UTF-8");
                i.setPackage("com.whatsapp");
                i.setData(Uri.parse(url));
                if (i.resolveActivity(packageManager) != null) {
                    startActivity(i);
                }

            } catch (UnsupportedEncodingException e) {
              //  Log.d("teste", "aqui");
                e.printStackTrace();
            }
        }

    private void getData() {
        myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    userData = snapshot.getValue(User.class);

                    contato = userData.getContato();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

