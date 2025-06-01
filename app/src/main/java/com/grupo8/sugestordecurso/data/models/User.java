package com.grupo8.sugestordecurso.data.models;

import java.io.Serializable;

public class User implements Serializable {
    private String cpf;
    private int origem = 7;
    private String token = "f2240ed12dca63c0a425f028cd88500e";

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String CPF) {
        this.cpf = CPF;
    }
}
