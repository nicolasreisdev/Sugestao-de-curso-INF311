package com.grupo8.sugestordecurso.data.models.Interfaces;

import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaBuscarNotas;

public interface BuscarNotasCallback {
    void onSuccess(RespostaBuscarNotas response);
    void onError(String errorMessage);
}
