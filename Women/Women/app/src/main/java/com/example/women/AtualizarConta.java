package com.example.women;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private TextView teste;
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

        teste = (TextView) findViewById(R.id.teste);
        novoNome = (EditText) findViewById(R.id.novoNome);
        novoContato = (EditText) findViewById(R.id.novoContato);
        novoCpf = (EditText) findViewById(R.id.novoCpf);
        novoEndereco = (EditText) findViewById(R.id.novoEndereco);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);

        getData();

        novoCpf.addTextChangedListener(MaskEditUtil.mask(novoCpf, MaskEditUtil.FORMAT_CPF));

        /*btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null){
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
                        }
                    });
                }

            }
        });*/

    }

    private void getData() {
            myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue() != null){
                        userData = snapshot.getValue(User.class);

                        //teste.setText(userData.getNome());

                        novoNome.setText(userData.getNome(), TextView.BufferType.EDITABLE);
                        //novoContato.setText(userData.getContato(), TextView.BufferType.EDITABLE);
                        /*novoCpf.setText(userData.getCpf(), TextView.BufferType.EDITABLE);
                        novoEndereco.setText(userData.getEndereco(), TextView.BufferType.EDITABLE);*/
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
