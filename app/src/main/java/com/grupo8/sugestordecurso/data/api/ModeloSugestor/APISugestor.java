package com.grupo8.sugestordecurso.data.api.ModeloSugestor;

import com.grupo8.sugestordecurso.data.models.BodyAPI.BodySugestor;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaSugestor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APISugestor {

    @Headers("Content-Type: application/json")

    @POST("/predict")
    Call<ArrayList<RespostaSugestor>> obterSugestões(@Body BodySugestor predicao);
}
