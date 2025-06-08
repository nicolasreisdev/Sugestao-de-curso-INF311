package com.grupo8.sugestordecurso.data.models.Interfaces;

import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaSugestor;

import java.util.ArrayList;

public interface SugestoesCallback {
    void onSuccess(ArrayList<RespostaSugestor> response);
    void onError(String errorMessage);
}
