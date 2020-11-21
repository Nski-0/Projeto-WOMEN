package com.example.front_end_refeito;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelaMenu extends AppCompatActivity {

    private Intent IrDenunciar, IrConsultar, IrAlertar, IrMapear;
    private Button btnDenunciar, btnConsultar, btnAlertar, btnMapear;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exit:
                Intent VoltarInicio = new Intent(TelaMenu.this, TelaInicial.class);
                startActivity(VoltarInicio);
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);

        btnDenunciar = (Button) findViewById(R.id.denunciar);
        btnAlertar = (Button) findViewById(R.id.alertar);
        btnMapear = (Button) findViewById(R.id.mapear);

        btnDenunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.delegaciainterativa.am.gov.br/#/home");
                IrDenunciar  = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(IrDenunciar);
            }
        });

        btnAlertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IrAlertar = new Intent(Intent.ACTION_DIAL);
                IrAlertar.setData(Uri.parse("tel:" + "180"));
                if (IrAlertar.resolveActivity(getPackageManager()) != null) {
                    startActivity(IrAlertar);
                }
            }
        });

        IrMapear =  new Intent(TelaMenu.this, MapearDelegacia.class);
        btnMapear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrMapear);
            }
        });
    }
}

