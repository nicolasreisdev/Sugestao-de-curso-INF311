package com.grupo8.sugestordecurso.ui.userPage;

import android.app.Activity;
import android.os.Bundle;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.ui.base.Base;

public class UserPage extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        setupBottomNavigation(R.id.nav_home);
    }
}