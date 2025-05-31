package com.grupo8.sugestordecurso.data.models;

import java.util.List;

class Dados{
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
    private List<Dados> dados;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setDados(List<Dados> dados) {
        this.dados = dados;
    }

    public List<Dados> getDados() {
        return dados;
    }
}
