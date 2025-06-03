package com.grupo8.sugestordecurso.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaUser;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyLogin;
import com.grupo8.sugestordecurso.data.models.Interfaces.UserCallback;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.register.Register;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public class MainActivity extends AppCompatActivity {
    private BodyLogin user = new BodyLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickLogin(View v){
        TextInputEditText editTextCPF = findViewById(R.id.userCPF);
        TextInputEditText editTextSenha = findViewById(R.id.userSenha);
        user.setCPF(editTextCPF.getText().toString());
        Log.i("API Teste", "Passei");

        // Cria conexão com APIRubeus
        RequestRepository contatoRepository = new RequestRepository();
        // Envia chamada
        contatoRepository.buscarUser(user, new UserCallback() {
            @Override
            public void onSuccess(RespostaUser response) {
                Log.i("API Teste", "Navegando para a tela do usuário");
                //navega para a página do usuário

                Intent it = new Intent(MainActivity.this, UserPage.class);
                startActivity(it);
            }

            @Override
            public void onError(String errorMessage) {
                Log.i("API Test", "Error");
            }
        });

    }

    public void goRegister(View v){
        Intent itRegister = new Intent(this, Register.class);
        startActivity(itRegister);
    }



}