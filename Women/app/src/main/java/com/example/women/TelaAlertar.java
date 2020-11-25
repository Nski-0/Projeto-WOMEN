package com.example.women;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                IrMensagem = new Intent();
                IrMensagem.setPackage("com.whatsapp");
                IrMensagem.setAction(Intent.ACTION_SEND);
                IrMensagem.putExtra(Intent.EXTRA_TEXT, "Texto do whats para teste.");
                IrMensagem.setType("text/plain");

                if (IrMensagem.resolveActivity(getPackageManager()) != null) {
                    startActivity(IrMensagem);
                }
            }
        });
    }
}

