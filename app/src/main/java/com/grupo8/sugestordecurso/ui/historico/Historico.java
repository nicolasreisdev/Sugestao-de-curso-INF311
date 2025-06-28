package com.grupo8.sugestordecurso.ui.historico;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.database.BancoDados;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.ui.base.Base;

public class Historico extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        setupBottomNavigation(R.id.nav_historico);

        carregarSugestoesDoBanco();
    }

    private void carregarSugestoesDoBanco() {
        Cursor c = BancoDados.getInstance().buscarPredicoesPorUsuario(User.getInstance().getId());

        HistoricoAdapter adapter = new HistoricoAdapter(this, c);
        ListView listaHistorico = findViewById(R.id.listaHistorico);
        listaHistorico.setAdapter(adapter);
    }

}
