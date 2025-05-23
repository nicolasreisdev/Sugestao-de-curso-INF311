package com.grupo8.sugestordecurso.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.api.APIClient;
import com.grupo8.sugestordecurso.data.api.APIRubeus;
import com.grupo8.sugestordecurso.data.model.RespostaCadastro;
import com.grupo8.sugestordecurso.data.model.User;
import com.grupo8.sugestordecurso.ui.register.Register;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogin(View v){
        TextInputEditText editTextCPF = findViewById(R.id.userCPF);
        TextInputEditText editTextSenha = findViewById(R.id.userSenha);
        user.setCPF(editTextCPF.toString());
        user.setEmail(editTextSenha.toString());

        APIRubeus rubeus = APIClient.getClient().create(APIRubeus.class);

        // Envia chamada
        Call<RespostaCadastro> call = rubeus.buscaUser(user);

        call.enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {
                RespostaCadastro resposta = response.body();
                if (resposta.isSuccess()) { // caso o contato seja cadastrado
                    // intent para página do usuário (é necessário passar Activity.this pois
                    // está dentro do onResponse que está apontando para o Callback e não para
                    // a Activity
                    Intent ituserPage = new Intent(MainActivity.this, UserPage.class);
                    // passa os dados do usuário
                    ituserPage.putExtra("User", user);
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

    public void goRegister(View v){
        Intent itRegister = new Intent(this, Register.class);
        startActivity(itRegister);
    }



}