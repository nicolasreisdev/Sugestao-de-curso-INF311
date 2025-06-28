package com.grupo8.sugestordecurso.ui.base;

import android.content.Intent;

import com.grupo8.sugestordecurso.R;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.ui.historico.Historico;
import com.grupo8.sugestordecurso.ui.profile.Profile;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public abstract class Base extends AppCompatActivity {

    protected User user = User.getInstance();

    protected void setupBottomNavigation(int selectedItemId) {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(selectedItemId); // marca o item atual como ativo
        Log.i("Nav","na Base");

        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == selectedItemId) {
                Log.i("Nav","na tela");
                return true; // já está na tela atual
            }

            Intent it = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                it = new Intent(this, UserPage.class);
                it.putExtra("nav","nav" );
                Log.i("Nav","Indo para userpage");
            } else if (id == R.id.nav_historico) {
                it = new Intent(this, Historico.class);
                Log.i("Nav","Indo para historico");
            } else if (id == R.id.nav_profile) {
                it = new Intent(this, Profile.class);
                Log.i("Nav","Indo para profile");
            }

            if (it != null) {
                Log.i("Nav","startar activity");
                startActivity(it);
                overridePendingTransition(0, 0);
                finish();
                return true;
            }

            return false;
        });
    }
}