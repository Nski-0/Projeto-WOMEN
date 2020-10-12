package pro.exe.aplicativo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaDenunciar extends AppCompatActivity {

    private Intent VoltarMenu;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_denunciar);


        btnCancelar = (Button) findViewById(R.id.cancelar);

        VoltarMenu =  new Intent(TelaDenunciar.this, TelaMenu.class);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VoltarMenu);
            }
        });
    }
}
