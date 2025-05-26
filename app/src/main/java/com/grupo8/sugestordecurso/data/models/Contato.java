package com.grupo8.sugestordecurso.data.models;

import java.io.Serializable;

public class Contato implements Serializable {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String nascimento;
    private String senha;
    private static final Integer origem = 7; // Código do canal
    private static final String token = "f2240ed12dca63c0a425f028cd88500e"; // Token da API

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getOrigem() {
        return origem;
    }
    
    public String getToken() {
        return token;
    }

}

