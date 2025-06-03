package com.grupo8.sugestordecurso.ui.base;

import android.content.Intent;

import com.grupo8.sugestordecurso.R;
import android.app.Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.grupo8.sugestordecurso.ui.historico.Historico;
import com.grupo8.sugestordecurso.ui.profile.Profile;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public abstract class Base extends Activity {

    protected void setupBottomNavigation(int selectedItemId) {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(selectedItemId); // marca o item atual como ativo

        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == selectedItemId) {
                return true; // já está na tela atual
            }

            Intent intent = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                intent = new Intent(this, UserPage.class);
            } else if (id == R.id.nav_historico) {
                intent = new Intent(this, Historico.class);
            } else if (id == R.id.nav_profile) {
                intent = new Intent(this, Profile.class);
            }

            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return true;
            }

            return false;
        });
    }
}