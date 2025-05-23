package com.grupo8.sugestordecurso;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.data.api.APIClient;
import com.grupo8.sugestordecurso.data.api.APIRubeus;
import com.grupo8.sugestordecurso.data.model.Contato;
import com.grupo8.sugestordecurso.data.model.RespostaCadastro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Activity {

    private Contato contato = new Contato();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClick(View v){

        // Recebe os dados
        TextInputEditText editTextNome = findViewById(R.id.Nome);
        TextInputEditText editTextCPF = findViewById(R.id.CPF);
        TextInputEditText editTextTelefone = findViewById(R.id.Telefone);
        TextInputEditText editTextEmail = findViewById(R.id.Email);
        TextInputEditText editTextNascimento = findViewById(R.id.Nascimento);
        TextInputEditText editTextSenha = findViewById(R.id.Senha);
        contato.nome = editTextNome.toString();
        contato.cpf = editTextCPF.toString();
        contato.telefone = editTextTelefone.toString();
        contato.email = editTextEmail.toString();
        contato.nascimento = editTextNascimento.toString();
        contato.senha = editTextSenha.toString();
        contato.token = "";
        contato.origem = 1;

        // Cria conexão com a APIRubeus
        APIRubeus rubeus = APIClient.getClient().create(APIRubeus.class);

        // Envia chamada
        Call<RespostaCadastro> call = rubeus.cadastrarContato(contato);

        call.enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {
                RespostaCadastro resposta = response.body();
                if (resposta.isSuccess()) { // caso o contato seja cadastrado

                }else{ // erro no cadastro

                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}