package com.grupo8.sugestordecurso.data.models.RespostasAPI;


import java.util.ArrayList;

class DadosUser {
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

public class RespostaUser {
    private boolean success;
    private ArrayList<DadosUser> dados;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setDados(ArrayList<DadosUser> dados) {
        this.dados = dados;
    }

    public String getDadosName() {
        return dados.get(0).getNome();
    }
}
