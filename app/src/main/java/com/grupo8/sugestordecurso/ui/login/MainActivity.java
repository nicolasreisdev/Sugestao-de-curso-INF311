package com.grupo8.sugestordecurso.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.api.APIClient;
import com.grupo8.sugestordecurso.data.api.APIRubeus;
import com.grupo8.sugestordecurso.data.models.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.User;
import com.grupo8.sugestordecurso.data.repository.ContatoRepository;
import com.grupo8.sugestordecurso.ui.register.Register;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogin(View v){
        /*TextInputEditText editTextCPF = findViewById(R.id.userCPF);
        TextInputEditText editTextSenha = findViewById(R.id.userSenha);
        user.setCPF(editTextCPF.getText().toString());
        user.setEmail(editTextSenha.getText().toString());

        // Cria conexão com APIRubeus
        ContatoRepository contatoRepository = new ContatoRepository();
        // Envia chamada
        contatoRepository.buscarUser(user).observe(this, resposta -> {
            if(resposta != null && resposta.isSuccess()){
                // intent para página do usuário (é necessário passar Activity.this pois
                // está dentro do onResponse que está apontando para o Callback e não para
                // a Activity
                Intent ituserPage = new Intent(MainActivity.this, UserPage.class);
                // passa os dados do usuário
                ituserPage.putExtra("User", user);
                // inicializa a página do usuário
                startActivity(ituserPage);
            }else{
                // exibir mensagem de erro
            }
        });*/

        //navega para a página do usuário
        Intent it = new Intent(MainActivity.this, UserPage.class);
        startActivity(it);

    }

    public void goRegister(View v){
        Intent itRegister = new Intent(this, Register.class);
        startActivity(itRegister);
    }



}