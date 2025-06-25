package com.grupo8.sugestordecurso.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyBuscarNotas;
import com.grupo8.sugestordecurso.data.models.Interfaces.BuscarNotasCallback;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaBuscarNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaUser;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyLogin;
import com.grupo8.sugestordecurso.data.models.Interfaces.UserCallback;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.loadScreen.LoadScreen;
import com.grupo8.sugestordecurso.ui.register.Register;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public class MainActivity extends AppCompatActivity {

    private BodyLogin bodyUser = new BodyLogin();
    private TextInputEditText editTextCPF;
    private LoadScreen LoadScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadScreen = new LoadScreen(); //inicializa a tela de carregamento para ser usada posteriormente
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
        Log.i("Load","Iniciando Login");
        bodyUser.setCPF(editTextCPF.getText().toString());
        Log.i("API Teste", "Passei");
        LoadScreen.showLoading(getSupportFragmentManager(),"Logando..."); //chama uma tela de carregamento enquanto as requisições de api e processamentos do modelo são feitas
        //define um tempo de atraso
        final long DELAY_BEFORE_API_CALL = 1000;
        User user = User.getInstance();
        //agenda a chamada da API para depois do atraso
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Cria conexão com APIRubeus
                RequestRepository contatoRepository = new RequestRepository();
                // Envia chamada
                contatoRepository.buscarUser(bodyUser, new UserCallback() {
                    @Override
                    public void onSuccess(RespostaUser response) {
                        // Navega para a página do usuário
                        user.setId(response.getDadosID());
                        user.setNome(response.getDadosNome());
                        user.setNomeSocial(response.getDadosNomeSocial());
                        user.setCpf(response.getDadosCPF());
                        user.setDataNascimento(response.getDadosNascimento());
                        user.setEmail(response.getDadosEmail());
                        user.setTelefone(response.getDadosTelefone());
                        Log.i("API Teste", "Dados: " + response.getDadosID() + " " + response.getDadosNomeSocial());

                        // ---------------------------------------------------------------------------------
                        // Requisição de notas do usuário
                        Log.i("API Teste", "Iniciando requisição para recuperar as notas do usuário.");
                        BodyBuscarNotas buscarNotas = new BodyBuscarNotas();
                        buscarNotas.setId(user.getId());
                        contatoRepository.buscarNotas(buscarNotas, new BuscarNotasCallback() {
                            @Override
                            public void onSuccess(RespostaBuscarNotas response) {
                                Log.i("API Teste", "Notas recuperadas, navegação para tela do usuário");
                                user.setNotaMatematica(response.getNotaMatematica());
                                user.setNotaPortugues(response.getNotaPortugues());
                                user.setNotaLiteratura(response.getNotaLiteratura());
                                user.setNotaRedacao(response.getNotaRedacao());
                                user.setNotaQuimica(response.getNotaQuimica());
                                user.setNotaFisica(response.getNotaFisica());
                                user.setNotaBiologia(response.getNotaBiologia());
                                user.setNotaGeografia(response.getNotaGeografia());
                                user.setNotaHistoria(response.getNotaHistoria());
                                user.setNotaFilosofia(response.getNotaFilosofia());
                                user.setNotaSociologia(response.getNotaSociologia());
                                user.setNotaArtes(response.getNotaArtes());
                                user.setAreaPreferencia(response.getAreaPreferencia());

                                LoadScreen.dismissLoading(); //dispensa a tela de carregamento
                                Intent it = new Intent(MainActivity.this, UserPage.class);
                                it.putExtra("user", user);
                                startActivity(it);
                                finish();
                            }

                            @Override
                            public void onError(String errorMessage) {
                                Log.e("API Test", "Error: " + errorMessage);
                                LoadScreen.dismissLoading(); //dispensa a tela de carregamento em caso de erro
                                Toast.makeText(MainActivity.this, "Usuário inexistente, tente novamente", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.e("API Test", "Error: " + errorMessage); // Use Log.e para erros
                        LoadScreen.dismissLoading(); //dispensa a tela de carregamento em caso de erro
                        Toast.makeText(MainActivity.this, "Usuário inexistente, tente novamente", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }, DELAY_BEFORE_API_CALL);


    }

    public void goRegister(View v){
        Intent itRegister = new Intent(this, Register.class);
        startActivity(itRegister);
    }





}