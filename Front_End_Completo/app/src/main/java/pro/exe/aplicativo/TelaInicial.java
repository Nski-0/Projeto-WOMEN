package pro.exe.aplicativo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.ViewDebug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaInicial extends AppCompatActivity {

    /*private FirebaseAuth mAuth;*/
    private Intent IrTela,IrMenu;
    private EditText txtEmail, txtSenha;
    private TextView teste;
    private Button btnLogin, btnCriar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCriar = (Button) findViewById(R.id.btnCriar);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        teste = (TextView) findViewById(R.id.teste);
        /*mAuth = FirebaseAuth.getInstance();*/

        IrTela = new Intent(TelaInicial.this, TelaCadastro.class);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrTela);
            }
        });

        IrMenu = new Intent(TelaInicial.this, TelaMenu.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrMenu);
            }
        });

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ctxtEmail.getText().toString();
                String password = ctxtSenha.getText().toString();

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
            }
        });
    }*/

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

    /*
    private void updateUI(FirebaseUser currentUser) {
        String msg = "Iniciando Tela Principal...";
        Toast.makeText(TelaInicial.this, msg, Toast.LENGTH_LONG).show();
    }*/
    }
}
