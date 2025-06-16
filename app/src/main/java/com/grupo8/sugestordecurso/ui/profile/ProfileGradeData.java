package com.grupo8.sugestordecurso.ui.profile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Utils.User;

public class ProfileGradeData extends Activity {
    //editTexts com os dados do usuário passíveis de serem alterados
    EditText edtMat, edtPort, edtRed, edtLit, edtQuim, edtFis, edtBio, edtGeo, edtHist, edtFilo, edtSocio, edtArtes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_grade_data);

        edtMat = findViewById(R.id.NotaMat);
        edtPort = findViewById(R.id.NotaPort);
        edtRed = findViewById(R.id.NotaRed);
        edtLit = findViewById(R.id.NotaLit);
        edtQuim = findViewById(R.id.NotaQuim);
        edtFis = findViewById(R.id.NotaFis);
        edtBio = findViewById(R.id.NotaBio);
        edtGeo = findViewById(R.id.NotaGeo);
        edtHist = findViewById(R.id.NotaHist);
        edtFilo = findViewById(R.id.NotaFilo);
        edtSocio = findViewById(R.id.NotaSocio);
        edtArtes = findViewById(R.id.NotaArte);

        //colocar os dados do aluno retirados da api da rubeus
        User user = User.getInstance();
        edtMat.setText(user.getNotaMatematica() + "");
        edtPort.setText(user.getNotaPortugues() + "");
        edtLit.setText(user.getNotaLiteratura() + "");
        edtRed.setText(user.getNotaRedacao() + "");
        edtQuim.setText(user.getNotaQuimica() + "");
        edtFis.setText(user.getNotaFisica() + "");
        edtBio.setText(user.getNotaBiologia() + "");
        edtGeo.setText(user.getNotaGeografia() + "");
        edtHist.setText(user.getNotaHistoria() + "");
        edtFilo.setText(user.getNotaFilosofia() + "");
        edtSocio.setText(user.getNotaSociologia() + "");
        edtArtes.setText(user.getNotaArtes() + "");
    }

    public void onClickUpdateGrade(View v){
        String notaMat = edtMat.getText().toString();

        //Atualização dos dados via api

        Toast.makeText(ProfileGradeData.this, "Notas atualizadas com sucesso!", Toast.LENGTH_SHORT).show();

        //volta para tela anterior
        finish();
    }
}