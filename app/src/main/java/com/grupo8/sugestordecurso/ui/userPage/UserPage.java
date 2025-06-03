package com.grupo8.sugestordecurso.ui.userPage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.ui.base.Base;

public class UserPage extends Base {
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        setupBottomNavigation(R.id.nav_home);
        user = (User)getIntent().getSerializableExtra("user");

        TextView nome = findViewById(R.id.Name);
        nome.setText("OI");
        Log.i("API Teste", "Nome: " + user.getNomeSocial());
        nome.setText(user.getNome());

    }


}