package com.grupo8.sugestordecurso.data.models.Interfaces;

import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;

public interface ContatoCallback {
    void onSuccess(RespostaCadastro response);
    void onError(String errorMessage);
}
