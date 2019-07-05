package com.example.appaeroporto;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewHolder mViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("meuLog", "onCreate");


        this.mViewHolder.imagem = findViewById(R.id.seta);
        // Deixa imagem invisivel
        this.mViewHolder.imagem.setVisibility(View.INVISIBLE);
        this.mViewHolder.texto = findViewById(R.id.ladoText);
        // Faz a imagem sair do transparente e e ter a cor total
        this.mViewHolder.aparece = new AlphaAnimation(0,1);
        // Faz a imagem ir para transparência
        this.mViewHolder.some = new AlphaAnimation(1,0);
        // Tempo de duração de da animação em milissegundos
        this.mViewHolder.aparece.setDuration(500);
        // Tempo de duração de da animação em milissegundos
        this.mViewHolder.some.setDuration(500);
        // Captura a tela inteira...
        this.mViewHolder.telaInteira = findViewById(R.id.Toque);

        this.mViewHolder.texto.setText("Toque para Continuar!");

        // Evento ao clickar em qualquer lugar da tela
        this.mViewHolder.telaInteira.setOnClickListener(this);

        // Controla o que acontece duurante a animação
        this.mViewHolder.aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            // Quando ela começa
            public void onAnimationStart(Animation animation) {
                mViewHolder.imagem.setVisibility(View.VISIBLE);

            }

            @Override
            // Quando ela termina
            public void onAnimationEnd(Animation animation) {
                mViewHolder.imagem.setVisibility(View.VISIBLE);
            }

            @Override
            // Quando ela repete
            public void onAnimationRepeat(Animation animation) {

            }
        });

        this.mViewHolder.some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mViewHolder.imagem.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewHolder.imagem.setVisibility(View.INVISIBLE);

                //Quando a animação de some termina
                mViewHolder.texto.setText("Toque para Continuar!");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }



    @Override
    public void onClick(View v) {
        // Se clickar na tela
        if(v.getId()== R.id.Toque) {

            double sorteio = Math.random();

            if (sorteio>=0.5) {
                this.mViewHolder.texto.setText("Siga para esquerda");


                this.mViewHolder.imagem.setScaleX(1f);
            }else{
                this.mViewHolder.texto.setText("Siga para a direita");
                this.mViewHolder.imagem.setScaleX(-1f);
            }
                this.mViewHolder.imagem.startAnimation(this.mViewHolder.aparece);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mViewHolder.imagem.startAnimation(mViewHolder.some);
                }
            },2000);

        }
    }

    public static class ViewHolder{
        ImageView imagem;
        TextView texto;
        Animation aparece, some;
        ConstraintLayout telaInteira;



    }
}