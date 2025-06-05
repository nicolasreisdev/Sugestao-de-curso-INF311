package com.grupo8.sugestordecurso.data.api.Rubeus;

import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyCadastro;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaUser;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

// Interface para endpoints da API do Rubeus
public interface APIRubeus {

    @Headers("Content-Type: application/json")

    // Cadastro de novo contato (Register)
    @POST("/api/Contato/cadastro")
    Call<RespostaCadastro> cadastrarContato(@Body BodyCadastro contato);

    // Busca por um usuário já cadastrado (Login)
    @POST("api/Contato/dadosPessoas")
    Call<RespostaUser> buscarUser(@Body BodyLogin user);

    // Adiciona notas ao usuário
    @POST("api/Evento/cadastro")
    Call<RespostaAddNotas> adicionarNotas(@Body BodyNotas notas);

}
