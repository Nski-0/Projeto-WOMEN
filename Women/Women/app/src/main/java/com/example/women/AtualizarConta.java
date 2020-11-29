package com.example.women;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.women.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AtualizarConta extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference myRef;
    FirebaseUser user;
    private User userData;
    private EditText novoNome;
    private EditText novoContato;
    private EditText novoCpf;
    private EditText novoEndereco;
    private Button btnAtualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_conta);

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();

        novoNome = (EditText) findViewById(R.id.novoNome);
        novoContato = (EditText) findViewById(R.id.novoContato);
        novoCpf = (EditText) findViewById(R.id.novoCpf);
        novoEndereco = (EditText) findViewById(R.id.novoEndereco);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);

        getData();

        novoCpf.addTextChangedListener(MaskEditUtil.mask(novoCpf, MaskEditUtil.FORMAT_CPF));
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atuallzarConta();
            }
        });

        novoNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!novoNome.getText().toString().equals(userData.getNome())){
                    userData.setNome(novoNome.getText().toString());
                    btnAtualizar.setEnabled(true);

                }
            }
        });

        novoContato.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!novoContato.getText().toString().equals(userData.getContato())){
                    userData.setContato(novoContato.getText().toString());
                    btnAtualizar.setEnabled(true);
                }
            }
        });

        novoCpf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!novoCpf.getText().toString().equals(userData.getContato())){
                    userData.setContato(novoCpf.getText().toString());
                    btnAtualizar.setEnabled(true);
                }
            }
        });

        novoEndereco.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!novoEndereco.getText().toString().equals(userData.getContato())){
                    userData.setContato(novoEndereco.getText().toString());
                    btnAtualizar.setEnabled(true);
                }
            }
        });

    }


    private void atuallzarConta() {
        if (user != null){
            btnAtualizar.setEnabled(false);
            myRef.child(user.getUid()).setValue(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "Dados atualizados!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Falha ao atualizar os dados!", Toast.LENGTH_LONG).show();
                    btnAtualizar.setEnabled(true);
                }
            });
        }
    }

    private void getData() {
            myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue() != null){
                        userData = snapshot.getValue(User.class);

                        novoNome.setText(userData.getNome());
                        novoContato.setText(userData.getContato());
                        novoCpf.setText(userData.getCpf());
                        novoEndereco.setText(userData.getEndereco());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    public void voltar(View view) {
        finish();
    }
}
