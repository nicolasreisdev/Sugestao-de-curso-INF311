package com.grupo8.sugestordecurso.data.api;

import com.grupo8.sugestordecurso.data.model.Contato;
import com.grupo8.sugestordecurso.data.model.RespostaCadastro;
import com.grupo8.sugestordecurso.data.model.User;

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

    // Criar registro dentro da API da Rubeus
    @POST("api/Evento/cadastro")
    Call<RespostaCadastro> criarRegistro(@Body Contato contato);

    // Busca por um usuário já cadastrado (Login)
    @POST("api/Contato/dadosPessoas")
    Call<RespostaCadastro> buscaUser(@Body User user);



}
