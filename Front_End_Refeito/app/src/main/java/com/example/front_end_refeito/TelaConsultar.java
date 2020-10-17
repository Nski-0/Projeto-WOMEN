package com.example.front_end_refeito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaConsultar extends AppCompatActivity {

    private Intent VoltarMenu;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consultar);


        btnCancelar = (Button) findViewById(R.id.cancelar);

        VoltarMenu =  new Intent(TelaConsultar.this, TelaMenu.class);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VoltarMenu);
            }
        });
    }
}

