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
import com.grupo8.sugestordecurso.ui.base.Base;

public class UserPage extends Base {
    User user;
    private HalfPieChartView halfPieChart;
    private TextView textViewCurso1Nome, textViewCurso1Prob;
    private TextView textViewCurso2Nome, textViewCurso2Prob;
    private TextView textViewCurso3Nome, textViewCurso3Prob;
    private View colorCurso1, colorCurso2, colorCurso3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Log.i("Nav","Pagina user");

        setupBottomNavigation(R.id.nav_home);
        user = (User)getIntent().getSerializableExtra("user");

        halfPieChart = findViewById(R.id.halfPieChart);

        // Referências para MPAndroidChart (descomente se usar)
        // pieChart = findViewById(R.id.pieChart);

        // Referências para os TextViews e cores da legenda
        textViewCurso1Nome = findViewById(R.id.textViewCurso1Nome);
        textViewCurso1Prob = findViewById(R.id.textViewCurso1Prob);
        colorCurso1 = findViewById(R.id.colorCurso1);

        textViewCurso2Nome = findViewById(R.id.textViewCurso2Nome);
        textViewCurso2Prob = findViewById(R.id.textViewCurso2Prob);
        colorCurso2 = findViewById(R.id.colorCurso2);

        textViewCurso3Nome = findViewById(R.id.textViewCurso3Nome);
        textViewCurso3Prob = findViewById(R.id.textViewCurso3Prob);
        colorCurso3 = findViewById(R.id.colorCurso3);

        // --- DADOS DE EXEMPLO ---
        // Estes dados viriam da lógica do seu app (escolhas do usuário, cálculos, etc.)
        String nomeCurso1 = "Engenharia de Software";
        float probCurso1 = 0.50f; // 50%

        String nomeCurso2 = "Ciência de Dados";
        float probCurso2 = 0.30f; // 30%

        String nomeCurso3 = "Design UX/UI";
        float probCurso3 = 0.20f; // 20%
        // Certifique-se que a soma das probabilidades seja 1.0f (ou 100%)
        // -------------------------

        // Atualizar a UI com os dados
        atualizarInformacoesCursos(nomeCurso1, probCurso1, nomeCurso2, probCurso2, nomeCurso3, probCurso3);

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

}