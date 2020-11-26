package com.example.women;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TelaCadastro extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebase;
    private DatabaseReference myRef;
    private EditText ctxtNovoNome, ctxtNovoEmail, ctxtNovoSenha , ctxtNovoCPF , ctxtNovoEndereco;
    private Button btnCriarCadastro;
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

        firebase = FirebaseDatabase.getInstance();
        myRef = firebase.getReference();

        ctxtNovoCPF.addTextChangedListener(MaskEditUtil.mask(ctxtNovoCPF, MaskEditUtil.FORMAT_CPF));

        btnCriarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = ctxtNovoNome.getText().toString();
                String email = ctxtNovoEmail.getText().toString();
                String cpf = ctxtNovoCPF.getText().toString();
                String endereco = ctxtNovoEndereco.getText().toString();
                String senha = ctxtNovoSenha.getText().toString();

                if(TextUtils.isEmpty(nome)){
                    ctxtNovoNome.setError("Insira o seu nome");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    ctxtNovoEmail.setError("Insira o seu email");
                    return;
                }

                if(TextUtils.isEmpty(cpf)){
                    ctxtNovoCPF.setError("Insira o seu cpf");
                    return;
                }

                if(TextUtils.isEmpty(endereco)){
                    ctxtNovoEndereco.setError("Insira o seu endereço");
                    return;
                }

                if(senha.length() < 6){
                    ctxtNovoSenha.setError("Senha > 5 digitos");
                    return;
                }

                registerNewUser(nome, email, cpf, endereco, senha);
            }
        });
    }

    private void registerNewUser(final String nome, final String email, final String cpf, final String endereco, String senha){

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Cadastro feito com sucesso!");
                            myRef.child("Usuario").child(mAuth.getUid()).child("Uid").setValue(mAuth.getUid());
                            myRef.child("Usuario").child(mAuth.getUid()).child("nome").setValue(nome);
                            myRef.child("Usuario").child(mAuth.getUid()).child("cpf").setValue(cpf);
                            myRef.child("Usuario").child(mAuth.getUid()).child("email").setValue(email);
                            myRef.child("Usuario").child(mAuth.getUid()).child("endereço").setValue(endereco);

                            FirebaseUser user = mAuth.getCurrentUser();
                            finishRegister(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Erro ao cadastrar o usuario!", task.getException());
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

        Toast.makeText(TelaCadastro.this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}