package com.grupo8.sugestordecurso.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Interfaces.NotasCallback;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.models.Utils.CheckConexion;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.loadScreen.LoadScreen;
import com.grupo8.sugestordecurso.ui.profile.Profile;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

import java.util.Objects;

public class RegisterData extends AppCompatActivity {
    User user;
    private LoadScreen LoadScreen;
    private boolean isConectado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);

        verificaConexao();

        LoadScreen = new LoadScreen(); //inicializa a tela de carregamento para ser usada posteriormente
        user = User.getInstance();

        Log.i("API Teste", "ID Register Data: " + user.getId());
        //Spinner com as opções pré-determinadas de áreas de preferencia
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autocomplete);

        //define as possíveis áreas de preferencia do aluno
        String[] prefs = {"Exatas","Saúde","Humanas","Linguagens","Biológicas","Artes","Tecnologia","Comunicação"};

        //cria e define o spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.list_item, // Layout personalizado
                prefs
        );

        autoCompleteTextView.setAdapter(adapter);

    }

    public void onClickRegister2(View v){
        if(isConectado) {
            LoadScreen.showLoading(getSupportFragmentManager(), "Cadastrando...");
            //tratamento da api
            Log.i("API Teste", "Iniciando cadastro dos dados");
            TextInputEditText editTextMatematica = findViewById(R.id.NotaMat);
            TextInputEditText editTextPortugues = findViewById(R.id.NotaPort);
            TextInputEditText editTextLiteratura = findViewById(R.id.NotaLit);
            TextInputEditText editTextBiologia = findViewById(R.id.NotaBio);
            TextInputEditText editTextFisica = findViewById(R.id.NotaFis);
            TextInputEditText editTextFilosofia = findViewById(R.id.NotaFilo);
            TextInputEditText editTextGeografia = findViewById(R.id.NotaGeo);
            TextInputEditText editTextHistoria = findViewById(R.id.NotaHist);
            TextInputEditText editTextQuimica = findViewById(R.id.NotaQuim);
            TextInputEditText editTextRedacao = findViewById(R.id.NotaRed);
            TextInputEditText editTextSociologia = findViewById(R.id.NotaSocio);
            TextInputEditText editTextArtes = findViewById(R.id.NotaArte);
            AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autocomplete);

            String NotaMat = Objects.requireNonNull(editTextMatematica.getText()).toString();
            String NotaPort = Objects.requireNonNull(editTextPortugues.getText()).toString();
            String NotaLit = Objects.requireNonNull(editTextLiteratura.getText()).toString();
            String NotaBio = Objects.requireNonNull(editTextBiologia.getText()).toString();
            String NotaFis = Objects.requireNonNull(editTextFisica.getText()).toString();
            String NotaFilo = Objects.requireNonNull(editTextFilosofia.getText()).toString();
            String NotaGeo = Objects.requireNonNull(editTextGeografia.getText()).toString();
            String NotaHist = Objects.requireNonNull(editTextHistoria.getText()).toString();
            String NotaQuim = Objects.requireNonNull(editTextQuimica.getText()).toString();
            String NotaRed = Objects.requireNonNull(editTextRedacao.getText()).toString();
            String NotaSocio = Objects.requireNonNull(editTextSociologia.getText()).toString();
            String NotaArt = Objects.requireNonNull(editTextArtes.getText()).toString();
            String areaPreferencia = autoCompleteTextView.getText().toString();

            //garante que todos os campos foram preenchidos, incluindo o spinner
            if (NotaMat.trim().isEmpty() || NotaPort.trim().isEmpty() || NotaLit.trim().isEmpty()
                    || NotaFis.trim().isEmpty() || NotaBio.trim().isEmpty() || NotaFilo.trim().isEmpty()
                    || NotaGeo.trim().isEmpty() || NotaHist.trim().isEmpty() || NotaQuim.trim().isEmpty()
                    || NotaRed.trim().isEmpty() || NotaSocio.trim().isEmpty() || NotaArt.trim().isEmpty()
                    || areaPreferencia.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_LONG).show();
                return;
            }

            Log.i("API Teste", "Convertendo dados para double");
            double notaMatematica = Double.parseDouble(NotaMat);
            double notaPortugues = Double.parseDouble(NotaPort);
            double notaLiteratura = Double.parseDouble(NotaLit);
            double notaBiologia = Double.parseDouble(NotaBio);
            double notaFisica = Double.parseDouble(NotaFis);
            double notaFilosofia = Double.parseDouble(NotaFilo);
            double notaGeografia = Double.parseDouble(NotaGeo);
            double notaHistoria = Double.parseDouble(NotaHist);
            double notaQuimica = Double.parseDouble(NotaQuim);
            double notaRedacao = Double.parseDouble(NotaRed);
            double notaSociologia = Double.parseDouble(NotaSocio);
            double notaArtes = Double.parseDouble(NotaArt);
            Log.i("API Teste", "Criando BodyNotas");

            BodyNotas notas = new BodyNotas();

            final long DELAY_BEFORE_API_CALL = 1000;

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i("API Teste", "Passando id");
                    // id do usuário
                    notas.setPessoa(user.getId());
                    user.setAreaPreferencia(areaPreferencia);

                    Log.i("API Teste", "Passando notas");
                    // notas do usuário
                    notas.setMatematica(notaMatematica);
                    notas.setPortugues(notaPortugues);
                    notas.setLiteratura(notaLiteratura);
                    notas.setBiologia(notaBiologia);
                    notas.setFisica(notaFisica);
                    notas.setFilosofia(notaFilosofia);
                    notas.setGeografia(notaGeografia);
                    notas.setHistoria(notaHistoria);
                    notas.setQuimica(notaQuimica);
                    notas.setRedacao(notaRedacao);
                    notas.setSociologia(notaSociologia);
                    notas.setArtes(notaArtes);
                    notas.setArea(areaPreferencia);
                    Log.i("API Teste", "Iniciando requisição");
                    RequestRepository notasRepository = new RequestRepository();

                    notasRepository.adicionarNotas(notas, new NotasCallback() {
                        @Override
                        public void onSuccess(RespostaAddNotas response) { // notas cadastradas com sucesso
                            Intent it = new Intent(RegisterData.this, UserPage.class);
                            Log.i("Nav", "Indo de cadastro para userpage");
                            it.putExtra("register", "register");
                            LoadScreen.dismissLoading();
                            startActivity(it);
                        }

                        @Override
                        public void onError(String errorMessage) {
                            LoadScreen.dismissLoading();
                            Log.i("API Teste", "Error");
                        }
                    });
                }
            }, DELAY_BEFORE_API_CALL);
        } else{
            View view = findViewById(android.R.id.content); // View raiz da sua activity
            Snackbar.make(view, "Sem conexão com a internet", Snackbar.LENGTH_LONG).show();
        }
    }

    private void verificaConexao(){
        //verificador de conexao
        CheckConexion verificadorConexao = new CheckConexion(getApplicationContext());

        // Observa as mudanças no estado da conexão
        verificadorConexao.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean conectado) {
                isConectado = conectado;
                if (!conectado) {
                    // Exibe uma mensagem de erro persistente se não houver conexão
                    View view = findViewById(android.R.id.content); // View raiz da sua activity
                    Snackbar.make(view, "Sem conexão com a internet", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }


}