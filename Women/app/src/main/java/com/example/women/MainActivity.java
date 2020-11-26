package com.example.women;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Intent IrTela,IrMenu,EnviarEmail;
    private EditText txtEmail, txtSenha;
    private TextView teste, txtPermissao, txtEsquecerSenha;
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
        txtEsquecerSenha = (TextView) findViewById(R.id.txtSenhaEsquecer);
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

        IrTela = new Intent(MainActivity.this, TelaCadastro.class);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrTela);
            }
        });

        txtEsquecerSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetEmail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Esqueçeu a senha ?");
                passwordResetDialog.setMessage("Digite seu Email para receber o link de resetar.");
                passwordResetDialog.setView(resetEmail);

                passwordResetDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extrair o email para receber o link de resetar senha
                        String Email = resetEmail.getText().toString();
                        mAuth.sendPasswordResetEmail(Email).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Link enviado para  seu Email",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "" + e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("Não ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //fechar dialogo

                    }
                });

                passwordResetDialog.create().show();

            }
        });

        IrMenu = new Intent(MainActivity.this, TelaMenu.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = txtEmail.getText().toString();
                final String password = txtSenha.getText().toString();

                if(!email.trim().isEmpty()&&!password.trim().isEmpty()){

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String msg = "Iniciando Tela Principal...";
                                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        Toast.makeText(MainActivity.this, " Email e/ou senha incorretos",
                                                Toast.LENGTH_SHORT).show();
                                        txtEmail.setText("");
                                        txtSenha.setText("");
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

    private void updateUI(FirebaseUser currentUser) {
        startActivity(IrMenu);
        finish();
    }

}
