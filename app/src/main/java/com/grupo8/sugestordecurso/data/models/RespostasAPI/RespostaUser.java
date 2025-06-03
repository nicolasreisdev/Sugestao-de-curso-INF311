package com.grupo8.sugestordecurso.data.models.RespostasAPI;


import java.util.ArrayList;

class DadosUser {
    private int id;
    private String nome;
    private String nomeSocial;

    public int getId() {
        return id;
    }

    public String getNomeSocial() {
        return nomeSocial;
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

    public String getDadosNome() {
        return dados.get(0).getNome();
    }

    public String getDadosNomeSocial() { return dados.get(0).getNomeSocial(); }

    public int getDadosID(){ return dados.get(0).getId(); }
}
