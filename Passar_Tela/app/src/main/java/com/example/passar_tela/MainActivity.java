package com.example.passar_tela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnTelaNova;
    private Intent IrTelaNova;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTelaNova = (Button) findViewById(R.id.telaNova);
        IrTelaNova = new Intent(MainActivity.this, Outra_Tela.class);

        btnTelaNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrTelaNova);
            }
        });
    }
}
