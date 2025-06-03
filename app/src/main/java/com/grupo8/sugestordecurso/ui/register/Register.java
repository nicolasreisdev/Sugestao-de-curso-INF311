package com.grupo8.sugestordecurso.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Contato;
import com.grupo8.sugestordecurso.data.models.Interfaces.ContatoCallback;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;


public class Register extends AppCompatActivity {

    private Contato contato;
    private TextInputEditText editTextCPF;
    private TextInputEditText editTextTelefone;
    private TextInputEditText editTextData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextCPF = findViewById(R.id.CPF);
        editTextTelefone = findViewById(R.id.Telefone);
        editTextData = findViewById(R.id.Nascimento);

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

        //cria uma mascara para o input de telefone ser no formato +55 (xx) xxxxx-xxxx

        //cria mascara para o input do usuario de data ser no formato YYYY-MM-DD
        editTextData.addTextChangedListener(new TextWatcher() {
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
                    for (int i = 0; i < len && i < 8; i++) {
                        if (i == 4 || i == 6) {
                            formatted.append("-");
                        }
                        formatted.append(str.charAt(i));
                    }
                }

                isUpdating = true;
                editTextData.setText(formatted.toString());
                editTextData.setSelection(formatted.length());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });



    }

    public void onClickRegister(View v){
        contato = new Contato();
        // Recebe os dados
        /* FORMATO DOS DADOS
        {
            "nome": "TesteAPI",
            "nomeSocial": "Teste",
            "dataNascimento": "2024-12-31",
            "emailPrincipal": "grupo8@gmail.com",
            "telefonePrincipal": "38999999999",
            "cpf": "333.333.333-33",
            "origem": 7,
            "token": "f2240ed12dca63c0a425f028cd88500e"
        }
        */
        TextInputEditText editTextNome = findViewById(R.id.Nome);
        TextInputEditText editTextNomeSocial = findViewById(R.id.NomeSocial);
        TextInputEditText editTextNascimento = findViewById(R.id.Nascimento);
        TextInputEditText editTextEmail = findViewById(R.id.Email);
        TextInputEditText editTextCPF = findViewById(R.id.CPF);

        contato.setNome(editTextNome.getText().toString());
        contato.setNomeSocial(editTextNomeSocial.getText().toString());
        contato.setCpf(editTextCPF.getText().toString());
        contato.setTelefonePrincipal(editTextTelefone.getText().toString());
        contato.setEmailPrincipal(editTextEmail.getText().toString());
        contato.setDataNascimento(editTextNascimento.getText().toString());
        Log.i("API Teste", "CPF: " + contato.getCpf());
        // Cria conexão com APIRubeus
        RequestRepository contatoRepository = new RequestRepository();
        // Envia chamada
        contatoRepository.cadastrarContato(contato, new ContatoCallback() {
            @Override
            public void onSuccess(RespostaCadastro response) {
                Log.i("API Teste", "Navegando para a tela do usuário");
                //navega para a página do usuário
                Intent it = new Intent(Register.this, RegisterData.class);
                startActivity(it);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });

    }
}