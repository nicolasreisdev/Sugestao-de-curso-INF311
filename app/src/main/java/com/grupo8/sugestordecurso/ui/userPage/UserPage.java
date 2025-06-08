package com.grupo8.sugestordecurso.ui.userPage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.grupo8.sugestordecurso.R;
import com.grupo8.sugestordecurso.data.models.Utils.HalfPieChartView;
import com.grupo8.sugestordecurso.data.models.Utils.User;
import com.grupo8.sugestordecurso.data.repository.RequestRepository;
import com.grupo8.sugestordecurso.ui.base.Base;

public class UserPage extends Base {
    User user;
    private HalfPieChartView halfPieChart;
    private HalfPieChartView halfPieChartTend;
    private TextView textViewCurso1Nome, textViewCurso1Prob, textViewCurso1NomeT, textViewCurso1ProbT;
    private TextView textViewCurso2Nome, textViewCurso2Prob, textViewCurso2NomeT, textViewCurso2ProbT;
    private TextView textViewCurso3Nome, textViewCurso3Prob, textViewCurso3NomeT, textViewCurso3ProbT;
    private View colorCurso1, colorCurso2, colorCurso3;
    private View colorCurso1T, colorCurso2T, colorCurso3T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Log.i("Nav","Pagina user");

        setupBottomNavigation(R.id.nav_home);
        user = (User)getIntent().getSerializableExtra("user");

        halfPieChart = findViewById(R.id.halfPieChart);
        halfPieChartTend = findViewById(R.id.halfPieChartT);

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

        //settar as probabilidades de cada curso sugerido pelas tendencias do mercado e cores usadas
        textViewCurso1NomeT = findViewById(R.id.textViewCurso1NomeT);
        textViewCurso1ProbT = findViewById(R.id.textViewCurso1ProbT);
        colorCurso1T = findViewById(R.id.colorCurso1T);

        textViewCurso2NomeT = findViewById(R.id.textViewCurso2NomeT);
        textViewCurso2ProbT = findViewById(R.id.textViewCurso2ProbT);
        colorCurso2T = findViewById(R.id.colorCurso2T);

        textViewCurso3NomeT = findViewById(R.id.textViewCurso3NomeT);
        textViewCurso3ProbT = findViewById(R.id.textViewCurso3ProbT);
        colorCurso3T = findViewById(R.id.colorCurso3T);

        //dados de cursos recomendados retirados do modelo
        String nomeCurso1 = "Engenharia de Software";
        float probCurso1 = 0.50f; // 50%

        String nomeCurso2 = "Ciência de Dados";
        float probCurso2 = 0.30f; // 30%

        String nomeCurso3 = "Design UX/UI";
        float probCurso3 = 0.20f; // 20%

        // atualiza a UI com os dados
        /*RequestRepository sugestaoRepository = new RequestRepository();

        sugestaoRepository.obterSugestoes();*/

        atualizarInformacoesCursos(nomeCurso1, probCurso1, nomeCurso2, probCurso2, nomeCurso3, probCurso3);

        //pega dados de tendência do mercado na área escolhida pelo usuário

        //atualiza o grafico com esses dados
        //atualizarInformacoesCursosT(nomeCurso1T, probCurso1T, nomeCurso2T, probCurso2T, nomeCurso3T, probCurso3T);

    }

    private void atualizarInformacoesCursos(String nome1, float p1, String nome2, float p2, String nome3, float p3) {
        // Definir nomes dos cursos
        textViewCurso1Nome.setText(nome1 + ":");
        textViewCurso2Nome.setText(nome2 + ":");
        textViewCurso3Nome.setText(nome3 + ":");

        // Definir probabilidades em porcentagem
        textViewCurso1Prob.setText(String.format("%.0f%%", p1 * 100));
        textViewCurso2Prob.setText(String.format("%.0f%%", p2 * 100));
        textViewCurso3Prob.setText(String.format("%.0f%%", p3 * 100));

        // Cores para a legenda (devem corresponder às cores no HalfPieChartView ou MPAndroidChart)
        int[] cores = {Color.parseColor("#FF6384"), Color.parseColor("#36A2EB"), Color.parseColor("#FFCE56")};
        colorCurso1.setBackgroundColor(cores[0]);
        colorCurso2.setBackgroundColor(cores[1]);
        colorCurso3.setBackgroundColor(cores[2]);

        // Atualizar o gráfico (View Customizada)
        if (halfPieChart != null) {
            halfPieChart.setProbabilities(p1, p2, p3);
            // Se você definiu cores na sua view customizada, elas serão usadas.
            // Se quiser passar as cores da activity para a view:
            // halfPieChart.setColors(new int[]{cores[0], cores[1], cores[2]}); // Você precisaria criar este método em HalfPieChartView
        }


    }

    private void atualizarInformacoesCursosT(String nome1, float p1, String nome2, float p2, String nome3, float p3) {
        // Definir nomes dos cursos
        textViewCurso1NomeT.setText(nome1 + ":");
        textViewCurso2NomeT.setText(nome2 + ":");
        textViewCurso3NomeT.setText(nome3 + ":");

        // Definir probabilidades em porcentagem
        textViewCurso1ProbT.setText(String.format("%.0f%%", p1 * 100));
        textViewCurso2ProbT.setText(String.format("%.0f%%", p2 * 100));
        textViewCurso3ProbT.setText(String.format("%.0f%%", p3 * 100));

        // Cores para a legenda
        int[] cores = {Color.parseColor("#FF6384"), Color.parseColor("#36A2EB"), Color.parseColor("#FFCE56")};
        colorCurso1T.setBackgroundColor(cores[0]);
        colorCurso2T.setBackgroundColor(cores[1]);
        colorCurso3T.setBackgroundColor(cores[2]);

        // Atualizar o gráfico (View Customizada)
        if (halfPieChartTend != null) {
            halfPieChartTend.setProbabilities(p1, p2, p3);
            // Se quiser passar as cores da activity para a view:
            // halfPieChart.setColors(new int[]{cores[0], cores[1], cores[2]}); // Você precisaria criar este método em HalfPieChartView
        }


    }

}