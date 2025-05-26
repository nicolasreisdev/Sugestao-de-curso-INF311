package com.grupo8.sugestordecurso.data.models;

import java.io.Serializable;

public class User implements Serializable {
    private String CPF;
    private String email;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
