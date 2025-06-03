package com.grupo8.sugestordecurso.data.api;

import com.grupo8.sugestordecurso.data.models.Contato;
import com.grupo8.sugestordecurso.data.models.Notas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaUser;
import com.grupo8.sugestordecurso.data.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

// Interface para endpoints da API do Rubeus
public interface APIRubeus {

    @Headers("Content-Type: application/json")

    // Cadastro de novo contato (Register)
    @POST("/api/Contato/cadastro")
    Call<RespostaCadastro> cadastrarContato(@Body Contato contato);

    // Busca por um usuário já cadastrado (Login)
    @POST("api/Contato/dadosPessoas")
    Call<RespostaUser> buscarUser(@Body User user);

    // Adiciona notas ao usuário
    @POST("api/Evento/cadastro")
    Call<RespostaAddNotas> adicionarNotas(@Body Notas notas);

}
