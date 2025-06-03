package com.grupo8.sugestordecurso.data.models.Utils;

import java.io.Serializable;

public class User implements Serializable {
    private String nome = "Teste";
    private String nomeSocial;
    private int id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
