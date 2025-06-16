package com.grupo8.sugestordecurso.data.models.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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
    private ValueAnimator animator;
    private float[] animatedProbabilities = {0f, 0f, 0f};
    private boolean isAnimating = false;

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

    //desenha as probabilidades no grafico criando uma animação de construção
    public void setProbabilities(float prob1, float prob2, float prob3) {
        final float[] targetProbabilities = {prob1, prob2, prob3};

        if (isAnimating && animator != null) {
            animator.cancel(); //cancela a animação anterior se estiver rodando
        }

        animator = ValueAnimator.ofFloat(0f, 1f); //anima de 0 a 1 (progresso da animação)
        animator.setDuration(1000); //duração da animação em milissegundos

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                for (int i = 0; i < targetProbabilities.length; i++) {
                    animatedProbabilities [i] = targetProbabilities [i] * fraction;
                }
                invalidate(); //força a redesenhar a view a cada atualização da animação
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
                //resetar as probabilidades animadas para 0 no início da animação
                for (int i = 0; i < animatedProbabilities.length; i++) {
                    animatedProbabilities [i] = 0f;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
                //garantir que as probabilidades finais sejam as desejadas após a animação
                System.arraycopy(targetProbabilities, 0, animatedProbabilities, 0, targetProbabilities.length);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimating = false;
                //garantir que as probabilidades animadas sejam resetadas em caso de cancelamento
                for (int i = 0; i < animatedProbabilities.length; i++) {
                    animatedProbabilities [i] = 0f;
                }
            }
        });

        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (animatedProbabilities == null || animatedProbabilities.length == 0) {
            return;
        }

        float width = getWidth();
        float height = getHeight() * 2; //para desenhar a meia pizza na metade inferior do espaço alocado para um círculo completo
        float radius = Math.min(width / 2, height / 2); //o raio será baseado na menor dimensão

        //define o retângulo que conterá a "pizza"
        //ajuste os valores de left, top, right, bottom conforme necessário para centralizar
        float centerX = width / 2;
        float centerY = getHeight(); //para que a base da meia pizza fique na parte inferior da View

        //rectF define os limites do oval onde os arcos serão desenhados
        //para uma meia pizza na parte de baixo, o centro Y do oval imaginário estaria na borda inferior da view
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        float totalAngle = 180.0f; //meia pizza
        float startAngle = 180.0f; //começa da esquerda (sentido horário)

        for (int i = 0; i < animatedProbabilities.length; i++) {
            if (animatedProbabilities [i] > 0) {
                paint.setColor(colors [i % colors.length]);
                float sweepAngle = animatedProbabilities [i] * totalAngle;
                canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
                startAngle += sweepAngle;
            }
        }
    }
}