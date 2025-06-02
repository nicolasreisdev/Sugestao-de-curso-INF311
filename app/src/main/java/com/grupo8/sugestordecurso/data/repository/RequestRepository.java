package com.grupo8.sugestordecurso.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.grupo8.sugestordecurso.data.api.APIClient;
import com.grupo8.sugestordecurso.data.api.APIRubeus;
import com.grupo8.sugestordecurso.data.models.Contato;
import com.grupo8.sugestordecurso.data.models.Evento;
import com.grupo8.sugestordecurso.data.models.Interfaces.ContatoCallback;
import com.grupo8.sugestordecurso.data.models.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.RespostaUser;
import com.grupo8.sugestordecurso.data.models.User;
import com.grupo8.sugestordecurso.data.models.Interfaces.UserCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRepository {
    private APIRubeus rubeus;

    public RequestRepository() {
        this.rubeus = APIClient.getClient().create(APIRubeus.class);
    }

    //LiveData<RespostaCadastro>
    public void cadastrarContato(Contato contato, final ContatoCallback callback) {
        Log.i("API Teste", "Chamada da requisição");
        Call<RespostaCadastro> callCadastro = rubeus.cadastrarContato(contato);
        callCadastro.enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {

                if(response.isSuccessful() && response.body() != null && response.body().isSuccess()){ // accepted 200
                    Log.i("API Teste", "200 OK Cadastro");
                    callback.onSuccess(response.body());
                    Log.i("API Teste", "Requisição feita com sucesso e retornado o id: " + response.body().getDados() );
                }
                else{ // error 400
                    Log.i("API Teste", "Error:  " + response);
                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                Log.i("API Teste", t.toString());
            }
        });

    }

    // LiveData<RespostaCadastro>
    public void buscarUser(User user, final UserCallback callback){
        Call<RespostaUser> callLogin = rubeus.buscarUser(user);
        callLogin.enqueue(new Callback<RespostaUser>() {
            @Override
            public void onResponse(Call<RespostaUser> call, Response<RespostaUser> response) {

                if(response.isSuccessful() && response.body() != null && response.body().isSuccess()){ // accepted 200
                    Log.i("API Teste", "200 OK Login");
                    callback.onSuccess(response.body());
                    Log.i("API Teste", "Requisição feita com sucesso e retornado os dados: " + response.body().getDadosName());
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

    public LiveData<RespostaCadastro> eventoNotaMatematica(Evento evento){
        MutableLiveData<RespostaCadastro> data = new MutableLiveData<>();
        rubeus.eventoNotaMatematica(evento).enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {
                RespostaCadastro resposta = response.body();
                if(resposta.isSuccess()){
                    data.setValue(resposta);
                }else{
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


    public LiveData<RespostaCadastro> eventoNotaHistoria(Evento evento){
        MutableLiveData<RespostaCadastro> data = new MutableLiveData<>();
        rubeus.eventoNotaHistoria(evento).enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {
                RespostaCadastro resposta = response.body();
                if(resposta.isSuccess()){
                    data.setValue(resposta);
                }else{
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


}
