package com.grupo8.sugestordecurso.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.grupo8.sugestordecurso.data.api.APIClient;
import com.grupo8.sugestordecurso.data.api.APIRubeus;
import com.grupo8.sugestordecurso.data.models.Contato;
import com.grupo8.sugestordecurso.data.models.Evento;
import com.grupo8.sugestordecurso.data.models.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.RespostaUser;
import com.grupo8.sugestordecurso.data.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestRepository {
    private APIRubeus rubeus;

    public RequestRepository() {
        this.rubeus = APIClient.getClient().create(APIRubeus.class);
    }

    //LiveData<RespostaCadastro>
    public RespostaCadastro cadastrarContato(Contato contato) {

        RespostaCadastro data = new RespostaCadastro();
        data.setSuccess(false);
        Call<RespostaCadastro> callCadastro = rubeus.cadastrarContato(contato);
        callCadastro.enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {

                if(response.isSuccessful()){ // accepted 200
                    RespostaCadastro aux = response.body(); // recebe o retorno da requisição
                    data.setSuccess(aux.isSuccess());
                    data.setDados(aux.getDados());
                    Log.i("API", "Requisição feita com sucesso e retornado os dados" );
                }
                else{ // error 400
                    Log.i("API", "Error 400");
                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                Log.i("API", t.toString());
            }
        });

        return data;

        /*
        MutableLiveData<RespostaCadastro> data = new MutableLiveData<>();
        // Envia chamada
        rubeus.cadastrarContato(contato).enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {
                RespostaCadastro resposta = response.body();
                if (resposta.isSuccess()) { // caso o contato seja cadastrado
                    data.setValue(resposta);
                } else { // erro no cadastro
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data; // retorna o LiveData*/
    }

    // LiveData<RespostaCadastro>
    public RespostaUser buscarUser(User user){
        RespostaUser data = new RespostaUser();
        data.setSuccess(false);
        Call<RespostaUser> callLogin = rubeus.buscarUser(user);
        callLogin.enqueue(new Callback<RespostaUser>() {
            @Override
            public void onResponse(Call<RespostaUser> call, Response<RespostaUser> response) {

                if(response.isSuccessful()){ // accepted 200
                    RespostaUser aux = response.body(); // recebe o retorno da requisição
                    data.setSuccess(aux.isSuccess());
                    data.setDados(aux.getDados());
                    Log.i("API", "Requisição feita com sucesso e retornado os dados" );
                }
                else{ // error 400
                    Log.i("API", "Error 400");
                }
            }

            @Override
            public void onFailure(Call<RespostaUser> call, Throwable t) {
                Log.i("API", t.toString());
            }
        });

        return data;

        /*
        MutableLiveData<RespostaCadastro> data = new MutableLiveData<>();
        // Envia chamada
        rubeus.buscarUser(user).enqueue(new Callback<RespostaCadastro>() {
            @Override
            public void onResponse(Call<RespostaCadastro> call, Response<RespostaCadastro> response) {
                RespostaCadastro resposta = response.body();
                if (resposta.isSuccess()) { // caso o contato seja cadastrado
                    data.setValue(resposta);
                } else { // erro no cadastro
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<RespostaCadastro> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data; // retorna o LiveData */
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
