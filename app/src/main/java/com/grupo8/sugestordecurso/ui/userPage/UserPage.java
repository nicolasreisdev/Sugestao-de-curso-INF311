package com.grupo8.sugestordecurso.ui.userPage;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.database.BancoDados;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodySugestor;
import com.grupo8.sugestordecurso.data.models.Interfaces.SugestoesCallback;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaSugestor;
import com.grupo8.sugestordecurso.data.models.Utils.HalfPieChartView;
import com.grupo8.sugestordecurso.data.models.Utils.SugestaoTendencias;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.base.Base;
import com.grupo8.sugestordecurso.ui.loadScreen.LoadScreen;

import java.util.ArrayList;

public class UserPage extends Base {
    User user;
    private HalfPieChartView halfPieChart;
    private SugestaoTendencias sugestao;
    private TextView textViewCurso1Nome, textViewCurso1Prob, textViewCurso1NomeT, textViewCurso1ProbT;
    private TextView textViewCurso2Nome, textViewCurso2Prob, textViewCurso2NomeT, textViewCurso2ProbT;
    private TextView textViewCurso3Nome, textViewCurso3Prob, textViewCurso3NomeT, textViewCurso3ProbT;
    private View colorCurso1, colorCurso2, colorCurso3;
    private View colorCurso1T, colorCurso2T, colorCurso3T;
    private LoadScreen LoadScreen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Log.i("Nav","Pagina user");

        LoadScreen = new LoadScreen(); //inicializa a tela de carregamento para ser usada posteriormente

        setupBottomNavigation(R.id.nav_home);
        user = User.getInstance();

        halfPieChart = findViewById(R.id.halfPieChart);
        requisitaModelo(user.getAreaPreferencia());

        // settar as prbabilidades de cada curso retornado pelo modelo e as cores usadas
        textViewCurso1Nome = findViewById(R.id.textViewCurso1Nome);
        textViewCurso1Prob = findViewById(R.id.textViewCurso1Prob);
        colorCurso1 = findViewById(R.id.colorCurso1);

        textViewCurso2Nome = findViewById(R.id.textViewCurso2Nome);
        textViewCurso2Prob = findViewById(R.id.textViewCurso2Prob);
        colorCurso2 = findViewById(R.id.colorCurso2);

        textViewCurso3Nome = findViewById(R.id.textViewCurso3Nome);
        textViewCurso3Prob = findViewById(R.id.textViewCurso3Prob);
        colorCurso3 = findViewById(R.id.colorCurso3);

        //settar probabilidades de cada curso dada a tendencia do mercado
        textViewCurso1NomeT = findViewById(R.id.textViewCurso1NomeT);
        textViewCurso1ProbT = findViewById(R.id.textViewCurso1ProbT);
        colorCurso1T = findViewById(R.id.colorCurso1T);

        textViewCurso2NomeT = findViewById(R.id.textViewCurso2NomeT);
        textViewCurso2ProbT = findViewById(R.id.textViewCurso2ProbT);
        colorCurso2T = findViewById(R.id.colorCurso2T);

        textViewCurso3NomeT = findViewById(R.id.textViewCurso3NomeT);
        textViewCurso3ProbT = findViewById(R.id.textViewCurso3ProbT);
        colorCurso3T = findViewById(R.id.colorCurso3T);

        //faz requisição para o modelo e atualiza grafico com as predições


    }

    private void atualizaInformacoesTendencias(String nome1, float p1, String nome2, float p2, String nome3, float p3){
        // Definir nomes dos cursos
        textViewCurso1NomeT.setText(nome1);
        textViewCurso2NomeT.setText(nome2);
        textViewCurso3NomeT.setText(nome3);

        // Definir probabilidades em porcentagem
        textViewCurso1ProbT.setText(String.format("%.0f%%", p1 * 100));
        textViewCurso2ProbT.setText(String.format("%.0f%%", p2 * 100));
        textViewCurso3ProbT.setText(String.format("%.0f%%", p3 * 100));

        // Cores para a legenda
        int[] cores = {Color.parseColor("#eb3455"), Color.parseColor("#eb34b1"), Color.parseColor("#e30733")};
        colorCurso1T.setBackgroundColor(cores[0]);
        colorCurso2T.setBackgroundColor(cores[1]);
        colorCurso3T.setBackgroundColor(cores[2]);
    }

    private void atualizarInformacoesCursos(String nome1, float p1, String nome2, float p2, String nome3, float p3) {
        // Definir nomes dos cursos
        textViewCurso1Nome.setText(nome1);
        textViewCurso2Nome.setText(nome2);
        textViewCurso3Nome.setText(nome3);

        // Definir probabilidades em porcentagem
        textViewCurso1Prob.setText(String.format("%.0f%%", p1 * 100));
        textViewCurso2Prob.setText(String.format("%.0f%%", p2 * 100));
        textViewCurso3Prob.setText(String.format("%.0f%%", p3 * 100));

        // Cores para a legenda
        int[] cores = {Color.parseColor("#eb3455"), Color.parseColor("#eb34b1"), Color.parseColor("#e30733")};
        colorCurso1.setBackgroundColor(cores[0]);
        colorCurso2.setBackgroundColor(cores[1]);
        colorCurso3.setBackgroundColor(cores[2]);

        // Atualizar o gráfico (View Customizada)
        if (halfPieChart != null) {
            halfPieChart.setProbabilities(p1, p2, p3);
        }


    }

    public void onClickAlterarPref(View v){
        //atualiza a área de preferencia do usuario
        String[] areas = {"Exatas","Saúde","Humanas","Linguagens","Biológicas","Artes","Tecnologia","Comunicação"};
        final String[] areaEscolhida = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Esolha Nova Área") //define uma aba com escolhas pro usuario
                .setItems(areas, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        areaEscolhida[0] = areas[which]; //define a nova area por ele escolhida
                        user.setAreaPreferencia(areaEscolhida[0]);

                        //chama tela de carregamento enquanto processa requisição do modelo
                        LoadScreen.showLoading(getSupportFragmentManager(),"Atualizando...");
                        final long DELAY_BEFORE_API_CALL = 1500;

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                requisitaModelo(areaEscolhida[0]);
                                LoadScreen.dismissLoading();
                            }
                        }, DELAY_BEFORE_API_CALL);
                    }
                });
        builder.create().show();


    }

    public void requisitaModelo(String area){

        TextView pref = (TextView) findViewById(R.id.textViewPref);
        pref.setText(area);
        BodySugestor notas = getBodySugestor();
        // atualiza a UI com os dados
        RequestRepository sugestaoRepository = new RequestRepository();

        sugestaoRepository.obterSugestoes(notas, new SugestoesCallback() {
            @Override
            public void onSuccess(ArrayList<RespostaSugestor> response) {
                Log.i("API Teste", "Predição feita com sucesso.");
                String nomeCurso1 = response.get(0).getCurso();
                float probCurso1 = response.get(0).getProbabilidadeAptidao();

                String nomeCurso2 = response.get(1).getCurso();
                float probCurso2 = response.get(1).getProbabilidadeAptidao();

                String nomeCurso3 = response.get(2).getCurso();
                float probCurso3 = response.get(2).getProbabilidadeAptidao();

                float total = probCurso1 + probCurso2 + probCurso3;
                probCurso1 /= total;
                probCurso2 /= total;
                probCurso3 /= total;

//                String nomeCurso1T;
//                float probCurso1T;
//
//                String nomeCurso2T;
//                float probCurso2T;
//
//                String nomeCurso3T;
//                float probCurso3T;
//
//                String tend[] = new String[3];
//
//                if(area.equals("Saude")){
//                    tend = sugestao.getTendSaude();
//
//                } else if(area.equals("Exatas")){
//                    tend = sugestao.getTendExatas();
//                } else if(area.equals("Linguagens")){
//                    tend = sugestao.getTendLinguagens();
//                } else if(area.equals("Biologicas")){
//                    tend = sugestao.getTendBiologicas();
//                } else if(area.equals("Humanas")){
//                    tend = sugestao.getTendHumanas();
//                } else if(area.equals("Artes")){
//                    tend = sugestao.getTendArtes();
//                } else if(area.equals("Tecnologia")){
//                    tend = sugestao.getTendTecnologia();
//                }
//                else if(area.equals("Comunicacao")){
//                    tend = sugestao.getTendComunicacao();
//                }
//
//                nomeCurso1T = tend[0];
//                probCurso1T = 0.5F;
//                nomeCurso2T = tend[1];
//                probCurso2T = 0.3F;
//                nomeCurso3T = tend[2];
//                probCurso3T = 0.2F;

                atualizarInformacoesCursos(nomeCurso1, probCurso1, nomeCurso2, probCurso2, nomeCurso3, probCurso3);
                //atualizaInformacoesTendencias(nomeCurso1T,probCurso1T,nomeCurso2T,probCurso2T,nomeCurso3T,probCurso3T);
                BancoDados.getInstance().salvarPredicao(
                        nomeCurso1, probCurso1,
                        nomeCurso2, probCurso2,
                        nomeCurso3, probCurso3
                );

            }

            @Override
            public void onError(String errorMessage) {
                Log.i("API Teste", "Erro na predição");
            }
        });

    }

    @NonNull
    private BodySugestor getBodySugestor() {
        BodySugestor notas = new BodySugestor();

        notas.setNotaMatematica(user.getNotaMatematica());
        notas.setNotaPortugues(user.getNotaPortugues());
        notas.setNotaLiteratura(user.getNotaLiteratura());
        notas.setNotaRedacao(user.getNotaRedacao());
        notas.setNotaQuimica(user.getNotaQuimica());
        notas.setNotaFisica(user.getNotaFisica());
        notas.setNotaBiologia(user.getNotaBiologia());
        notas.setNotaFilosofia(user.getNotaFilosofia());
        notas.setNotaSociologia(user.getNotaSociologia());
        notas.setNotaGeografia(user.getNotaGeografia());
        notas.setNotaHistoria(user.getNotaHistoria());
        notas.setNotaArtes(user.getNotaArtes());
        notas.setAreaPreferencia(user.getAreaPreferencia());
        return notas;
    }

}