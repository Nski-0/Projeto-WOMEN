package com.example.front_end_refeito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaCadastro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText ctxtNovoNome, ctxtNovoEmail, ctxtNovoSenha , ctxtNovoCPF , ctxtNovoEndereco;
    private Button btnCriarCadastro;
    private Intent IrMenu;
    private String TAG = "TelaCadastro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        btnCriarCadastro = (Button) findViewById(R.id.btnCriarCadastro);
        ctxtNovoNome = (EditText) findViewById(R.id.ctxtNovoNome);
        ctxtNovoEmail = (EditText) findViewById(R.id.ctxtNovoEmail);
        ctxtNovoCPF = (EditText) findViewById(R.id.cpf);
        ctxtNovoEndereco = (EditText) findViewById(R.id.endereco);
        ctxtNovoSenha = (EditText) findViewById(R.id.ctxtNovoSenha);
        mAuth = FirebaseAuth.getInstance();

        IrMenu = new Intent(TelaCadastro.this, TelaMenu.class);

        btnCriarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = ctxtNovoNome.getText().toString();
                String email = ctxtNovoEmail.getText().toString();
                String cpf = ctxtNovoCPF.getText().toString();
                String endereco = ctxtNovoEndereco.getText().toString();
                String senha = ctxtNovoSenha.getText().toString();

                registerNewUser(nome, email, cpf, endereco, senha);
            }
        });
    }

    private void registerNewUser(String nome, String email, String cpf, String endereco, String senha){

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            finishRegister(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(TelaCadastro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            finishRegister(null);
                        }
                    }
                });

    }

    private void finishRegister(FirebaseUser firebaseUser){
        if(firebaseUser == null){
            Toast.makeText(TelaCadastro.this, "Erro ao criar o usuário.", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(TelaCadastro.this, "Usuário cadastrado.", Toast.LENGTH_SHORT).show();
        finish();

    }
}
