package com.grupo8.sugestordecurso.data.models;

public class RespostaCadastro {
    private boolean success;
    private int dados;

    public boolean isSuccess(){
        return success;
    }
    public void setSuccess(boolean success){
        this.success = success;
    }

    public int getDados(){
        return dados;
    }

    public void setDados(int dados){
        this.dados = dados;
    }


}
