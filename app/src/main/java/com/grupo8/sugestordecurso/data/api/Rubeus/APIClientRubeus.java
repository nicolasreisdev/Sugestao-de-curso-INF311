package com.grupo8.sugestordecurso.data.api.Rubeus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientRubeus {
    private static final String BASE_URL = "https://crmufvgrupo8.apprubeus.com.br/";
    private static Retrofit retrofit = null;

    // passar token e origem como parametro
    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
