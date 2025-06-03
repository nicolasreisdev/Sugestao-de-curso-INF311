package com.grupo8.sugestordecurso.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.ui.base.Base;

public class Profile extends Base {

    LinearLayout menuNotas, menuDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView imagemPerfil = findViewById(R.id.ImagemPerfil);
        TextView textNome = findViewById(R.id.TextNomeSobrenome);

        menuNotas = findViewById(R.id.MenuNotas);
        menuDados = findViewById(R.id.MenuDadosPessoais);

        //setar o nome e imagem de acordo com o usuário

        //tratamento de clique nos menus
        menuNotas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent(Profile.this, ProfileGradeData.class);
                startActivity(it);
            }
        });

        menuDados.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent(Profile.this, ProfilePersonalData.class);
                startActivity(it);
            }
        });


        setupBottomNavigation(R.id.nav_profile);
    }

}