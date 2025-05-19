package com.grupo8.sugestordecurso;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

// Interface para endpoints da API do Rubeus
public interface APIRubeus {
    @GET("contatos")
    Call<List<User>> getUsuarios();

}
