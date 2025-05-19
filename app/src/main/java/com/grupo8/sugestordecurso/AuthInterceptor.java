package com.grupo8.sugestordecurso;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private String token;
    private String origin;

    public AuthInterceptor(String token, String origin) {
        this.token = token;
        this.origin = origin;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Origin", origin)
                .build();
        return chain.proceed(request);
    }
}
