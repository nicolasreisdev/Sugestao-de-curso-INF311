package com.grupo8.sugestordecurso.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Contato;
import com.grupo8.sugestordecurso.data.models.Interfaces.ContatoCallback;
import com.grupo8.sugestordecurso.data.models.RespostaCadastro;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public class Register extends AppCompatActivity {

    private Contato contato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void onClickRegister(View v){
        contato = new Contato();
        // Recebe os dados
        /* FORMATO DOS DADOS
        "nome": "TesteAPI",
        "nomeSocial": "Teste",
        "dataNascimento": "2006-10-14",
        "telefonePrincipal": "+55 (38) 99979-7178",
        "emailPrincipal": "grupo8@gmail.com",
        "cpf": "910.108.870-09"
        */
        TextInputEditText editTextNome = findViewById(R.id.Nome);
        TextInputEditText editTextNomeSocial = findViewById(R.id.NomeSocial);
        TextInputEditText editTextNascimento = findViewById(R.id.Nascimento);
        TextInputEditText editTextTelefone = findViewById(R.id.Telefone);
        TextInputEditText editTextEmail = findViewById(R.id.Email);
        TextInputEditText editTextCPF = findViewById(R.id.CPF);
        contato.setNome(editTextNome.getText().toString());
        contato.setNomeSocial(editTextNomeSocial.getText().toString());
        contato.setCpf(editTextCPF.getText().toString());
        contato.setTelefonePrincipal(editTextTelefone.getText().toString());
        contato.setEmailPrincipal(editTextEmail.getText().toString());
        contato.setDataNascimento(editTextNascimento.getText().toString());

        // Cria conexão com APIRubeus
        RequestRepository contatoRepository = new RequestRepository();
        // Envia chamada
        contatoRepository.cadastrarContato(contato, new ContatoCallback() {
            @Override
            public void onSuccess(RespostaCadastro response) {
                Log.i("API Teste", "Navegando para a tela do usuário");
                //navega para a página do usuário
                Intent it = new Intent(Register.this, UserPage.class);
                startActivity(it);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }
}