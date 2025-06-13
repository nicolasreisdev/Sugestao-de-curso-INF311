package com.grupo8.sugestordecurso.data.models.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class HalfPieChartView extends View {

    private Paint paint;
    private RectF rectF;
    private float[] probabilities = {0f, 0f, 0f}; // Probabilidades para os 3 cursos
    private int[] colors = {Color.parseColor("#eb3455"), Color.parseColor("#eb34b1"), Color.parseColor("#e30733")};

    public HalfPieChartView(Context context) {
        super(context);
        init();
    }

    public HalfPieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HalfPieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true); // Suaviza as bordas
        paint.setStyle(Paint.Style.FILL);
        rectF = new RectF();
    }

    public void setProbabilities(float prob1, float prob2, float prob3) {
        // Garanta que as probabilidades somem 1.0f (ou 100%)
        // Aqui, esperamos valores de 0.0 a 1.0
        probabilities[0] = prob1;
        probabilities[1] = prob2;
        probabilities[2] = prob3;
        invalidate(); // Redesenha a view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (probabilities == null || probabilities.length == 0) {
            return;
        }

        float width = getWidth();
        float height = getHeight() * 2; // Para desenhar a meia pizza na metade inferior do espaço alocado para um círculo completo
        float radius = Math.min(width / 2, height / 2); // O raio será baseado na menor dimensão

        // Define o retângulo que conterá a "pizza" (na verdade, um oval)
        // Ajuste os valores de left, top, right, bottom conforme necessário para centralizar
        float centerX = width / 2;
        float centerY = getHeight(); // Para que a base da meia pizza fique na parte inferior da View

        // O RectF define os limites do oval onde os arcos serão desenhados
        // Para uma meia pizza na parte de baixo, o centro Y do oval imaginário estaria na borda inferior da view
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        float totalAngle = 180.0f; // Meia pizza
        float startAngle = 180.0f; // Começa da esquerda (sentido horário)

        for (int i = 0; i < probabilities.length; i++) {
            if (probabilities[i] > 0) { // Só desenha se a probabilidade for maior que zero
                paint.setColor(colors[i % colors.length]); // Define a cor para o segmento
                float sweepAngle = probabilities[i] * totalAngle;
                canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
                startAngle += sweepAngle;
            }
        }
    }
}