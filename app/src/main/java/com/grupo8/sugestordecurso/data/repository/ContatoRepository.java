package com.grupo8.sugestordecurso.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.grupo8.sugestordecurso.data.api.APIClient;
import com.grupo8.sugestordecurso.data.api.APIRubeus;
import com.grupo8.sugestordecurso.data.models.Contato;
import com.grupo8.sugestordecurso.data.models.Evento;
import com.grupo8.sugestordecurso.data.models.RespostaCadastro;
import com.grupo8.sugestordecurso.data.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContatoRepository {
    private APIRubeus rubeus;

    public ContatoRepository() {
        this.rubeus = APIClient.getClient().create(APIRubeus.class);
    }

    public LiveData<RespostaCadastro> cadastrarContato(Contato contato) {
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

        return data; // retorna o LiveData
    }

    public LiveData<RespostaCadastro> buscarUser(User user){
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

        return data; // retorna o LiveData
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
