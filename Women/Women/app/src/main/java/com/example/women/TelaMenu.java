package com.example.women;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TelaMenu extends AppCompatActivity {

    private Intent IrDenunciar, IrAlertar, IrMapear;
    private Button btnDenunciar, btnAlertar, btnMapear;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exit:
                Intent VoltarInicio = new Intent(TelaMenu.this, MainActivity.class);
                startActivity(VoltarInicio);
                finish();
                return false;

            case R.id.change_password:
                /*Intent MudarSenha = new Intent(TelaMenu.this, MudarSenha.class);
                startActivity(MudarSenha);*/
                Toast.makeText(TelaMenu.this, "Essa função não estar disponivel nesse momento", Toast.LENGTH_SHORT).show();
                return false;

            case R.id.menu_update:
                /*Intent AtualizarConta = new Intent(TelaMenu.this, AtualizarConta.class);
                startActivity(AtualizarConta);*/
                Toast.makeText(TelaMenu.this, "Essa função não estar disponivel nesse momento", Toast.LENGTH_SHORT).show();
                return false;

            case R.id.menu_delete:
                /*Intent DeletarConta = new Intent(TelaMenu.this, DeletarConta.class);
                startActivity(DeletarConta);*/
                Toast.makeText(TelaMenu.this, "Essa função não estar disponivel nesse momento", Toast.LENGTH_SHORT).show();
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

        IrAlertar = new Intent(TelaMenu.this, TelaAlertar.class);
        btnAlertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrAlertar);
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


