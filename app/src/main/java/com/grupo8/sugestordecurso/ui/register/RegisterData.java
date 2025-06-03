package com.grupo8.sugestordecurso.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Interfaces.NotasCallback;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.profile.Profile;

public class RegisterData extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);

        //Spinner com as opções pré-determinadas de áreas de preferencia
        Spinner spinner = findViewById(R.id.Pref);

        //define as possíveis áreas de preferencia do aluno
        String[] prefs = {"Área de preferência","Exatas","Saúde","Humanas","Linguagens","Biológicas","Artes","Tecnologia"};

        //cria e define o spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,prefs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //para pegar a opção selecionada pelo spinner
        //String select = spinner.getSelectedItem().tostring();
    }

    public void onClickRegister2(View v){
        //tratamento da api
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

        BodyNotas notas = new BodyNotas();

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
        RequestRepository notasRepository = new RequestRepository();

        notasRepository.adicionarNotas(notas, new NotasCallback() {
            @Override
            public void onSuccess(RespostaAddNotas response) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        });



        //navega para a área do usuário
        Intent it = new Intent(RegisterData.this, Profile.class);
        startActivity(it);
    }
}