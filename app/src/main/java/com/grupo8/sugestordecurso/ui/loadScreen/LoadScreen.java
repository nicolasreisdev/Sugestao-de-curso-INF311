package com.grupo8.sugestordecurso.ui.loadScreen;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.grupo8.sugestordecurso.R;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

//tela de carregamento
public class LoadScreen extends DialogFragment {

    private static final String ARG_MESSAGE = "message";

    //cria a instancia do fragmento
    public static LoadScreen newInstance(String message) {
        LoadScreen fragment = new LoadScreen();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //garante que o diálogo não pode ser cancelado tocando fora
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        //infla a view de tela
        View view = inflater.inflate(R.layout.fragment_loading_screen, container, false);

        //image view para guardar gif
        ImageView gifImageView = view.findViewById(R.id.LoadGifImageView);

        //carregando o gif
        Glide.with(this)
                .asGif()   //especifica que é um gif
                .load(R.drawable.logo_gif) //carrega o GIF da pasta drawable
                .centerInside() //centraliza o GIF dentro do ImageView, mantendo proporção
                .into(gifImageView); //define o ImageView onde o GIF será exibido

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //fundo da janela do diálogo transparente
        }
    }
    //metodo para chamar a tela de carregamento
    public void showLoading(FragmentManager fragmentManager, String message) {
        Log.i("Load", "Starting to load");
        //garante que o argumento da mensagem esteja atualizado antes de mostrar
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }
        args.putString(ARG_MESSAGE, message);
        setArguments(args);

        //verifica se o dialog já está sendo exibido para evitar IllegalStateException
        if (!isAdded()) {
            //exibe a tela de carregamento
            show(fragmentManager, "loading_dialog");
        }
    }

    //metodo para finalizar a tela de carregamento
    public void dismissLoading() {
        Log.i("Load","Dismissing");
        if (isAdded()) { //verifica se o fragment está anexado ao gerenciador
            dismiss();
        }
    }

}