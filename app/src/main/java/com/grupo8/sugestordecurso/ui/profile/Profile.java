package com.grupo8.sugestordecurso.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grupo8.sugestordecurso.R;

public class Profile extends Activity {

    LinearLayout menuNotas, menuHistorico, menuDados, menuConfigs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView imagemPerfil = findViewById(R.id.ImagemPerfil);
        TextView textNome = findViewById(R.id.TextNomeSobrenome);

        menuNotas = findViewById(R.id.MenuNotas);
        menuHistorico = findViewById(R.id.MenuHistorico);
        menuDados = findViewById(R.id.MenuDadosPessoais);
        menuConfigs = findViewById(R.id.MenuConfigs);

        //setar o nome e imagem de acordo com o usuário

        //tratamento de clique nos menus
        menuNotas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent(Profile.this, ProfileGradeData.class);
                startActivity(it);
            }
        });

        menuHistorico.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent it = new Intent(Profile.this, .class);
                //startActivity(it);
            }
        });

        menuDados.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent it = new Intent(Profile.this, ProfilePersonalData.class);
                startActivity(it);
            }
        });

        menuConfigs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent it = new Intent(Profile.this, .class);
                //startActivity(it);
            }
        });
    }
}