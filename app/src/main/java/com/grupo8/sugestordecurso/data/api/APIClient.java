package com.grupo8.sugestordecurso.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL = "https://crmufvgrupo8.apprubeus.com.br/";
    private static Retrofit retrofit = null;

    // passar token e origem como parametro
    public static Retrofit getClient() {
        //OkHttpClient client = new OkHttpClient.Builder()
        //       .addInterceptor(new AuthInterceptor(token, origin))
        //      .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
