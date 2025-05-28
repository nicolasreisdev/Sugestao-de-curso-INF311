package com.grupo8.sugestordecurso.data.api;

import com.grupo8.sugestordecurso.data.models.Contato;
import com.grupo8.sugestordecurso.data.models.Evento;
import com.grupo8.sugestordecurso.data.models.RespostaCadastro;
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
    Call<RespostaCadastro> buscarUser(@Body User user);

    // Evento de nota de matématica
    @POST("api/Evento/cadastro")
    Call<RespostaCadastro> eventoNotaMatematica(@Body Evento evento);

    // Evento de nota de história
    @POST("api/Evento/cadastro")
    Call<RespostaCadastro> eventoNotaHistoria(@Body Evento evento);

    // Evento de nota de geografia
    @POST("api/Evento/cadastro")
    Call<RespostaCadastro> eventoNotaGeografia(@Body Evento evento);

    // Evento de nota de biologia
    @POST("api/Evento/cadastro")
    Call<RespostaCadastro> eventoNotaBiologia(@Body Evento evento);


}
