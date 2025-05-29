package com.grupo8.sugestordecurso.ui.profile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.grupo8.sugestordecurso.R;

public class ProfilePersonalData extends Activity {
    //editTexts com os dados do usuário passíveis de serem alterados
    EditText edtNome, edtEmail, edtTelefone, edtCPF, edtDataN, edtSenha;
    Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_personal_data);

        edtNome = findViewById(R.id.Nome);
        edtEmail = findViewById(R.id.Email);
        edtTelefone = findViewById(R.id.Telefone);
        edtCPF = findViewById(R.id.CPF);
        edtDataN = findViewById(R.id.Nascimento);
        edtSenha = findViewById(R.id.Senha);

        buttonSalvar = findViewById(R.id.BtSalvar);

        //colocar os dados do aluno retirados da api da rubeus
        String e = "ble";
        edtNome.setText(e);
        edtEmail.setText(e);
        edtTelefone.setText(e);
        edtCPF.setText(e);
        edtDataN.setText(e);
        edtSenha.setText(e);

    }

    public void onClickUpdateData(View v){
        String nome = edtNome.getText().toString();

        //Atualização dos dados via api

        Toast.makeText(ProfilePersonalData.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();

        //volta para tela anterior
        finish();
    }
}