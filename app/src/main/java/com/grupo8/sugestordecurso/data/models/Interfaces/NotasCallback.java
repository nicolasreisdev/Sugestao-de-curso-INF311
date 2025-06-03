package com.grupo8.sugestordecurso.data.models.Interfaces;

import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;

public interface NotasCallback {
    void onSuccess(RespostaAddNotas response);
    void onError(String errorMessage);
}
