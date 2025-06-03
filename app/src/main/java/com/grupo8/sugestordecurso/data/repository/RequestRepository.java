package com.grupo8.sugestordecurso.data.repository;

import android.util.Log;


import com.google.gson.Gson;
import com.grupo8.sugestordecurso.data.api.APIClient;
import com.grupo8.sugestordecurso.data.api.APIRubeus;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyCadastro;
import com.grupo8.sugestordecurso.data.models.Interfaces.ContatoCallback;
import com.grupo8.sugestordecurso.data.models.Interfaces.NotasCallback;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaAddNotas;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.RespostasAPI.RespostaUser;
import com.grupo8.sugestordecurso.data.models.BodyAPI.BodyLogin;
import com.grupo8.sugestordecurso.data.models.Interfaces.UserCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRepository {
    private APIRubeus rubeus;

    public RequestRepository() {
        this.rubeus = APIClient.getClient().create(APIRubeus.class);
    }

    // Requisição para cadastro de novo contato
    public void cadastrarContato(BodyCadastro contato, final ContatoCallback callback) {
        Log.i("API Teste", "Chamada da requisição");
        Call<RespostaCadastro> callCadastro = rubeus.cadastrarContato(contato);
        callCadastro.enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {

                if(response.isSuccessful() && response.body() != null && response.body().isSuccess()){ // accepted 200
                    Log.i("API Teste", "200 OK Cadastro");
                    callback.onSuccess(response.body());
                }
                else{ // error 400
                    Log.i("API Teste", "Error:  " + response.body());
                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                Log.i("API Teste", t.toString());
            }
        });

    }

    // Requisição para buscar usuário já cadastrado
    public void buscarUser(BodyLogin user, final UserCallback callback){
        Call<RespostaUser> callLogin = rubeus.buscarUser(user);
        callLogin.enqueue(new Callback<RespostaUser>() {
            @Override
            public void onResponse(Call<RespostaUser> call, Response<RespostaUser> response) {

                if(response.isSuccessful() && response.body() != null && response.body().isSuccess()){ // accepted 200
                    Log.i("API Teste", "200 OK Login");
                    callback.onSuccess(response.body());
                    //Log.i("API Teste", "Requisição feita com sucesso e retornado os dados: " + response.body().getDadosNome());
                }
                else{ // error 400
                    Log.i("API Teste", "Error 400");
                    callback.onError("Error");
                }
            }

            @Override
            public void onFailure(Call<RespostaUser> call, Throwable t) {
                Log.i("API Teste", t.toString());
            }
        });

    }

    // Requisição para adicionar notas ao usuário
    public void adicionarNotas(BodyNotas notas, final NotasCallback callback){
        Log.i("API Teste", "Requisição de notas");
        Call<RespostaAddNotas> callNotas = rubeus.adicionarNotas(notas);
        callNotas.enqueue(new Callback<RespostaAddNotas>() {
            @Override
            public void onResponse(Call<RespostaAddNotas> call, Response<RespostaAddNotas> response) {
                if(response.isSuccessful()) { // accepted 200
                    callback.onSuccess(response.body());
                    Log.i("API Teste", "200 OK Notas");
                }
                else{ // Error
                    Log.i("API Teste", "Error " + response.body());
                }
            }

            @Override
            public void onFailure(Call<RespostaAddNotas> call, Throwable t) {
                Log.i("API Teste", t.toString());
            }
        });

    }



}
