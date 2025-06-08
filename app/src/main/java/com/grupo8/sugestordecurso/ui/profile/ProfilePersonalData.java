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
    EditText edtNome, edtEmail, edtTelefone, edtCPF, edtDataN;
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

        buttonSalvar = findViewById(R.id.BtSalvar);

        //colocar os dados do aluno retirados da api da rubeus
        String nome = "Pedro Silva";
        String email = "pedro@email.com";
        String telefone = "+55 38 94444-4444";
        String cpf = "211.227.986-60";
        String data = "21/07/2000";
        edtNome.setText(nome);
        edtEmail.setText(email);
        edtTelefone.setText(telefone);
        edtCPF.setText(cpf);
        edtDataN.setText(data);

    }

    public void onClickUpdateData(View v){
        String nome = edtNome.getText().toString();

        //Atualização dos dados via api

        Toast.makeText(ProfilePersonalData.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();

        //volta para tela anterior
        finish();
    }
}