package com.grupo8.sugestordecurso.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyNotas;
import com.grupo8.sugestordecurso.data.models.Interfaces.NotasCallback;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.models.Utils.CheckConexion;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.loadScreen.LoadScreen;
import com.grupo8.sugestordecurso.ui.userPage.UserPage;

public class ProfileGradeData extends AppCompatActivity {
    //editTexts com os dados do usuário passíveis de serem alterados
    EditText edtMat, edtPort, edtRed, edtLit, edtQuim, edtFis, edtBio, edtGeo, edtHist, edtFilo, edtSocio, edtArtes;
    LoadScreen LoadScreen;
    User user;
    private boolean isConectado = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_grade_data);

        LoadScreen = new LoadScreen();
        user = User.getInstance();

        onClickVoltar();
        verificaConexao();

        setNotas();
    }

    public void onClickUpdateGrade(View v){
        if(isConectado) {
            LoadScreen.showLoading(getSupportFragmentManager(), "Salvando");
            BodyNotas notas = new BodyNotas();

            updateNotas(notas);

            RequestRepository notasRepository = new RequestRepository();
            notasRepository.adicionarNotas(notas, new NotasCallback() {
                @Override
                public void onSuccess(RespostaAddNotas response) { // notas cadastradas com sucesso
                    LoadScreen.dismissLoading();
                    Toast.makeText(ProfileGradeData.this, "Notas atualizadas com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(ProfileGradeData.this, UserPage.class);
                    it.putExtra("profile","profile");
                    startActivity(it);
                    Log.i("API Teste", "Notas atualizadas com sucesso");
                }

                @Override
                public void onError(String errorMessage) {
                    LoadScreen.dismissLoading();
                    Toast.makeText(ProfileGradeData.this, "Erro ao atualizar notas", Toast.LENGTH_SHORT).show();
                    Log.i("API Teste", "Error");
                }
            });
        } else{
            View view = findViewById(android.R.id.content);
            Snackbar.make(view, "Sem conexão com a internet", Snackbar.LENGTH_LONG).show();
        }
    }

    private void onClickVoltar(){
        ImageButton btnVoltar = findViewById(R.id.btn_voltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para voltar: Simula o botão "voltar" do sistema
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    private void verificaConexao(){
        //verificador de conexao
        CheckConexion verificadorConexao = new CheckConexion(getApplicationContext());
        // Observa as mudanças no estado da conexão
        verificadorConexao.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean conectado) {
                isConectado = conectado;
                if (!conectado) {
                    // Exibe uma mensagem de erro persistente se não houver conexão
                    View view = findViewById(android.R.id.content); // View raiz da sua activity
                    Snackbar.make(view, "Sem conexão com a internet", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setNotas(){
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

    private void updateNotas(BodyNotas notas){
        String notaMat = edtMat.getText().toString();
        String notaPort = edtPort.getText().toString();
        String notaLit = edtLit.getText().toString();
        String notaRed = edtRed.getText().toString();
        String notaQuim = edtQuim.getText().toString();
        String notaFis = edtFis.getText().toString();
        String notaBio = edtBio.getText().toString();
        String notaGeo = edtGeo.getText().toString();
        String notaHist = edtHist.getText().toString();
        String notaFilo = edtFilo.getText().toString();
        String notaSocio = edtSocio.getText().toString();
        String notaArt = edtArtes.getText().toString();
        //Atualização dos dados via api
        notas.setPessoa(user.getId());
        notas.setMatematica(Double.parseDouble(notaMat));
        notas.setPortugues(Double.parseDouble(notaPort));
        notas.setLiteratura(Double.parseDouble(notaLit));
        notas.setRedacao(Double.parseDouble(notaRed));
        notas.setQuimica(Double.parseDouble(notaQuim));
        notas.setFisica(Double.parseDouble(notaFis));
        notas.setBiologia(Double.parseDouble(notaBio));
        notas.setGeografia(Double.parseDouble(notaGeo));
        notas.setHistoria(Double.parseDouble(notaHist));
        notas.setFilosofia(Double.parseDouble(notaFilo));
        notas.setSociologia(Double.parseDouble(notaSocio));
        notas.setArtes(Double.parseDouble(notaArt));
        notas.setArea(user.getAreaPreferencia());
    }

}