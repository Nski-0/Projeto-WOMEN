package com.example.front_end_refeito;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Intent IrTela,IrMenu;
    private EditText txtEmail, txtSenha;
    private TextView teste, txtPermissao;
    private Button btnLogin, btnCriar;

    String[] appPermissoes = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static final int  CODIGO_PERMISSOES_REQUIRIDAS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPermissao = (TextView) findViewById(R.id.txtPermissao);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCriar = (Button) findViewById(R.id.btnCriar);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        teste = (TextView) findViewById(R.id.teste);
        mAuth = FirebaseAuth.getInstance();

        if(verificarPermissoes()){
                // iniciar aplicativo
        } else {
                //txtPermissao.setText("Nem todas as permissões estão ativas");
        }

        IrTela = new Intent(TelaInicial.this, TelaCadastro.class);
        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrTela);
            }
        });

        IrMenu = new Intent(TelaInicial.this, TelaMenu.class);

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrMenu);
            }
        });*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String password = txtSenha.getText().toString();

                if(email.isEmpty()&&password.isEmpty()){
                    Toast.makeText(TelaInicial.this, "Por favor preencha os campos",
                            Toast.LENGTH_SHORT).show();
                }else{
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(TelaInicial.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    Toast.makeText(TelaInicial.this, "Falha ao realizar o Login",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }}
        });
    }

    private boolean verificarPermissoes() {

        List<String> permissoesRequiridas = new ArrayList<>();

        for (String permissao: appPermissoes) {

            if (ContextCompat.checkSelfPermission(this, permissao) != PackageManager.PERMISSION_GRANTED) {
                permissoesRequiridas.add(permissao);
            }
        }

            if(!permissoesRequiridas.isEmpty())
            {
                ActivityCompat.requestPermissions(this,
                        permissoesRequiridas.toArray(new
                                String[permissoesRequiridas.size()]),
                        CODIGO_PERMISSOES_REQUIRIDAS);
                return false;
            }

            //txtPermissao.setText("Todas as permissões ativas");
            return true;
    }

    /*@Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }else{
            String msg = "Erro ao fazer o Login";
            Toast.makeText(TelaInicial.this, msg, Toast.LENGTH_LONG).show();
        }
    }*/

    private void updateUI(FirebaseUser currentUser) {
        String msg = "Iniciando Tela Principal...";
        Toast.makeText(TelaInicial.this, msg, Toast.LENGTH_LONG).show();
        startActivity(IrMenu);
        finish();
    }
}
