package com.grupo8.sugestordecurso.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.ui.profile.Profile;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

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

        //navega para a área do usuário
        Intent it = new Intent(RegisterData.this, Profile.class);
        startActivity(it);
    }
}