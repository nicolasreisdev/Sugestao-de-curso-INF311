package com.grupo8.sugestordecurso.ui.historico;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.database.BancoDados;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.ui.base.Base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Historico extends Base {

    private LinearLayout sugestoesContainer;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        setupBottomNavigation(R.id.nav_historico);

        sugestoesContainer = findViewById(R.id.sugestoesContainer);
        carregarSugestoesDoBanco();
    }

    private void carregarSugestoesDoBanco() {
        Cursor c = BancoDados.getInstance().buscarPredicoesPorUsuario(user.getInstance().getId());

        HistoricoAdapter adapter = new HistoricoAdapter(this, c);
        ListView listaHistorico = findViewById(R.id.listaHistorico);
        listaHistorico.setAdapter(adapter);
    }

}
