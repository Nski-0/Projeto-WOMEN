package pro.exe.aplicativo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TelaCadastro extends AppCompatActivity {

    private EditText ctxtNovoNome, ctxtNovoEmail, ctxtNovoTelefone, ctxtNovoSenha;
    private Button btnCriarCadastro;
    private Intent IrMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        btnCriarCadastro = (Button) findViewById(R.id.btnCriarCadastro);
        ctxtNovoNome = (EditText) findViewById(R.id.ctxtNovoNome);
        ctxtNovoEmail = (EditText) findViewById(R.id.ctxtNovoEmail);
        ctxtNovoTelefone = (EditText) findViewById(R.id.ctxtNovoTelefone);
        ctxtNovoSenha = (EditText) findViewById(R.id.ctxtNovoSenha);

        IrMenu = new Intent(TelaCadastro.this, TelaMenu.class);

        btnCriarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrMenu);
            }
        });

        /*btnCriarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nome = ctxtNovoNome.getText().toString();
                String Email = ctxtNovoEmail.getText().toString();
                String Telefone = ctxtNovoTelefone.getText().toString();
                String Senha = ctxtNovoSenha.getText().toString();

                Toast AvisoContaCriada = Toast.makeText(getApplicationContext(), "Conta Cadastrada com sucesso!", Toast.LENGTH_SHORT);
                AvisoContaCriada.show();
                Intent VoltarTela = new Intent(TelaCadastro.this, TelaInicial.class);
                VoltarTela.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                VoltarTela.putExtra("key1", Nome);
                VoltarTela.putExtra("key2", Email);
                VoltarTela.putExtra("key3", Telefone);
                VoltarTela.putExtra("key4", Senha);
                startActivity(VoltarTela);
            }
        });*/
    }

}
