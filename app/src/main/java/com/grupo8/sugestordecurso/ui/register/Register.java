package com.grupo8.sugestordecurso.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.api.APIClient;
import com.grupo8.sugestordecurso.data.api.APIRubeus;
import com.grupo8.sugestordecurso.data.model.Contato;
import com.grupo8.sugestordecurso.data.model.RespostaCadastro;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

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

    public void onClickRegister(View v){

        // Recebe os dados
        TextInputEditText editTextNome = findViewById(R.id.Nome);
        TextInputEditText editTextCPF = findViewById(R.id.CPF);
        TextInputEditText editTextTelefone = findViewById(R.id.Telefone);
        TextInputEditText editTextEmail = findViewById(R.id.Email);
        TextInputEditText editTextNascimento = findViewById(R.id.Nascimento);
        TextInputEditText editTextSenha = findViewById(R.id.Senha);

        contato.setNome(editTextNome.toString());
        contato.setCpf(editTextCPF.toString());
        contato.setTelefone(editTextTelefone.toString());
        contato.setEmail(editTextEmail.toString());
        contato.setNascimento(editTextNascimento.toString());
        contato.setSenha(editTextSenha.toString());
        contato.setToken("");
        contato.setOrigem(1);

        // Cria conexão com a APIRubeus
        APIRubeus rubeus = APIClient.getClient().create(APIRubeus.class);

        // Envia chamada
        Call<RespostaCadastro> call = rubeus.cadastrarContato(contato);

        call.enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {
                RespostaCadastro resposta = response.body();
                if (resposta.isSuccess()) { // caso o contato seja cadastrado
                    // intent para página do usuário (é necessário passar Activity.this pois
                    // está dentro do onResponse que está apontando para o Callback e não para
                    // a Activity
                    Intent ituserPage = new Intent(Register.this, UserPage.class);
                    // passa os dados do usuário
                    ituserPage.putExtra("User", contato);
                    // inicializa a página do usuário
                    startActivity(ituserPage);
                }else{ // erro no cadastro
                    // exibir mensagem de erro
                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}