package com.grupo8.sugestordecurso.ui.historico;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.ui.base.Base;

public class Historico extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        setupBottomNavigation(R.id.nav_historico);

    }
}