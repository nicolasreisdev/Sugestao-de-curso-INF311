package com.grupo8.sugestordecurso.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.RespostaUser;
import com.grupo8.sugestordecurso.data.models.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.register.Register;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public class MainActivity extends AppCompatActivity {
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogin(View v){
        TextInputEditText editTextCPF = findViewById(R.id.userCPF);
        TextInputEditText editTextSenha = findViewById(R.id.userSenha);
        user.setCPF(editTextCPF.getText().toString());

        // Cria conexão com APIRubeus
        RequestRepository contatoRepository = new RequestRepository();
        // Envia chamada
        RespostaUser response = contatoRepository.buscarUser(user);

        if(response.isSuccess()){
            //navega para a página do usuário
            Intent it = new Intent(MainActivity.this, UserPage.class);
            startActivity(it);
        }
        else{
            //exibe mensagem de erro
        }

    }

    public void goRegister(View v){
        Intent itRegister = new Intent(this, Register.class);
        startActivity(itRegister);
    }



}