package com.grupo8.sugestordecurso.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaUser;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyLogin;
import com.grupo8.sugestordecurso.data.models.Interfaces.UserCallback;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.register.Register;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public class MainActivity extends AppCompatActivity {
    private BodyLogin bodyUser = new BodyLogin();
    private TextInputEditText editTextCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCPF = findViewById(R.id.userCPF);

        //cria uma mascara dinamicamente para que o input do usuario tenha formato xxx.xxx.xxx-xx
        editTextCPF.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;
            String oldText = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString().replaceAll("[^\\d]", "");

                if (isUpdating) {
                    oldText = str;
                    isUpdating = false;
                    return;
                }

                StringBuilder formatted = new StringBuilder();

                int len = str.length();

                if (len > 0) {
                    for (int i = 0; i < len && i < 11; i++) {
                        char c = str.charAt(i);
                        if (i == 3 || i == 6) {
                            formatted.append('.');
                        } else if (i == 9) {
                            formatted.append('-');
                        }
                        formatted.append(c);
                    }
                }

                isUpdating = true;
                editTextCPF.setText(formatted.toString());
                editTextCPF.setSelection(formatted.length());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

    }

    public void onClickLogin(View v){
        bodyUser.setCPF(editTextCPF.getText().toString());
        Log.i("API Teste", "Passei");

        // Cria conexão com APIRubeus
        RequestRepository contatoRepository = new RequestRepository();
        // Envia chamada
        contatoRepository.buscarUser(bodyUser, new UserCallback() {
            @Override
            public void onSuccess(RespostaUser response) {
                Log.i("API Teste", "Navegando para a tela do usuário");
                //navega para a página do usuário
                User user = User.getInstance();
                user.setId(response.getDadosID());
                user.setNome(response.getDadosNome());
                user.setNomeSocial(response.getDadosNomeSocial());
                user.setCpf(response.getDadosCPF());
                user.setDataNascimento(response.getDadosNascimento());
                user.setEmail(response.getDadosEmail());
                user.setTelefone(response.getDadosTelefone());
                Log.i("API Teste", "Dados: " + response.getDadosID() + " " + response.getDadosNomeSocial());
                Intent it = new Intent(MainActivity.this, UserPage.class);
                it.putExtra("user", user);
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