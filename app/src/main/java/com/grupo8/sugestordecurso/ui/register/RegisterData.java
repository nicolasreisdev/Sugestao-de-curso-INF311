package com.grupo8.sugestordecurso.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Interfaces.NotasCallback;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.loadScreen.LoadScreen;
import com.grupo8.sugestordecurso.ui.profile.Profile;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public class RegisterData extends AppCompatActivity {
    User user;
    private LoadScreen loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);

        user = (User) getIntent().getSerializableExtra("user");
        Log.i("API Teste", "ID Register Data: " + user.getId());
        //Spinner com as opções pré-determinadas de áreas de preferencia
        Spinner spinner = findViewById(R.id.Pref);

        //define as possíveis áreas de preferencia do aluno
        String[] prefs = {"Área de preferência","Exatas","Saúde","Humanas","Linguagens","Biológicas","Artes","Tecnologia","Comunicação"};

        //cria e define o spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,prefs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //para pegar a opção selecionada pelo spinner
        //String select = spinner.getSelectedItem().tostring();
    }

    public void onClickRegister2(View v){
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
        Spinner spinner = findViewById(R.id.Pref);
        String areaPreferencia = spinner.getSelectedItem().toString();
        Log.i("API Teste", "Convertendo dados para double");
        double notaMatematica = Double.parseDouble(editTextMatematica.getText().toString());
        double notaPortugues = Double.parseDouble(editTextPortugues.getText().toString());
        double notaLiteratura = Double.parseDouble(editTextLiteratura.getText().toString());
        double notaBiologia = Double.parseDouble(editTextBiologia.getText().toString());
        double notaFisica = Double.parseDouble(editTextFisica.getText().toString());
        double notaFilosofia = Double.parseDouble(editTextFilosofia.getText().toString());
        double notaGeografia = Double.parseDouble(editTextGeografia.getText().toString());
        double notaHistoria = Double.parseDouble(editTextHistoria.getText().toString());
        double notaQuimica = Double.parseDouble(editTextQuimica.getText().toString());
        double notaRedacao = Double.parseDouble(editTextRedacao.getText().toString());
        double notaSociologia = Double.parseDouble(editTextSociologia.getText().toString());
        double notaArtes = Double.parseDouble(editTextArtes.getText().toString());
        Log.i("API Teste", "Criando BodyNotas");

        BodyNotas notas = new BodyNotas();
        showLoadingScreen("Cadastrando...");

        final long DELAY_BEFORE_API_CALL = 3000;

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("API Teste", "Passando id");
                // id do usuário
                notas.setPessoa(user.getId());

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
                        it.putExtra("user", user);
                        dismissLoadingScreen();
                        startActivity(it);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.i("API Teste", "Error");
                    }
                });
            }
            }, DELAY_BEFORE_API_CALL);
    }

    private void showLoadingScreen(String message) {
        if (loadingDialog == null) {
            loadingDialog = LoadScreen.newInstance(message);
        }
        // Verifica se o dialog já está sendo exibido para evitar IllegalStateException
        if (!loadingDialog.isAdded()) {
            loadingDialog.show(getSupportFragmentManager(), "loading_dialog");
        }
    }

    private void dismissLoadingScreen() {
        if (loadingDialog != null && loadingDialog.isAdded()) {
            loadingDialog.dismiss();
            loadingDialog = null; // Opcional: reinicia a instância para o próximo uso
        }
    }
}