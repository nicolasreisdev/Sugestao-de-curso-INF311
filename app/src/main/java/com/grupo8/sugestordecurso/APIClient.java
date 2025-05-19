package com.grupo8.sugestordecurso;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static final String BASE_URL = "";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String token, String origin) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(token, origin))
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
