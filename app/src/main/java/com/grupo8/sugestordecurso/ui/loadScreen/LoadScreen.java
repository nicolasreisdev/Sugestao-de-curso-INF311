package com.grupo8.sugestordecurso.ui.loadScreen;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.grupo8.sugestordecurso.R;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class LoadScreen extends DialogFragment {

    private static final String ARG_MESSAGE = "message";
    private ImageView gifImageView; // Alterado de WebView para ImageView


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

        View view = inflater.inflate(R.layout.fragment_loading_screen, container, false);

        // Se você usa o TextView:
        // loadingTextView = view.findViewById(R.id.loading_text);
        // if (getArguments() != null && loadingTextView != null) {
        //     String message = getArguments().getString(ARG_MESSAGE);
        //     if (message != null && !message.isEmpty()) {
        //         loadingTextView.setText(message);
        //     }
        // }

        gifImageView = view.findViewById(R.id.LoadGifImageView); // Referencie o ImageView

        // Carrega o GIF usando Glide
        Glide.with(this) // 'this' é o Fragment, use getActivity() se for Activity
                .asGif()   // Especifica que você está carregando um GIF
                .load(R.drawable.loadscreen2) // Carrega o GIF da pasta res/drawable
                // Se o GIF ainda estiver em assets:
                // .load("file:///android_asset/loadscreen.gif")
                .centerInside() // Centraliza o GIF dentro do ImageView, mantendo proporção
                .into(gifImageView); // Define o ImageView onde o GIF será exibido

        return view;
    }

    // Se você usa o updateMessage:
    // public void updateMessage(String newMessage) {
    //     if (loadingTextView != null) {
    //         loadingTextView.setText(newMessage);
    //     }
    // }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Fundo da janela do diálogo transparente
        }
    }

}