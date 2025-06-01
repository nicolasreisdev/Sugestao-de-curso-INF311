package com.grupo8.sugestordecurso.data.models.Interfaces;

import com.grupo8.sugestordecurso.data.models.RespostaUser;

public interface UserCallback {
    void onSuccess(RespostaUser response);
    void onError(String errorMessage);
}
