package com.example.women;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AtualizarConta extends AppCompatActivity {

    private EditText novoNome, pegarEmail, novoCPF , novoEndereco, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_conta);

        novoNome = (EditText) findViewById(R.id.novoNome);
        novoCPF = (EditText) findViewById(R.id.novoCpf);
        novoEndereco = (EditText) findViewById(R.id.novoEndereco);

        novoCPF.addTextChangedListener(MaskEditUtil.mask(novoCPF, MaskEditUtil.FORMAT_CPF));

    }
}
