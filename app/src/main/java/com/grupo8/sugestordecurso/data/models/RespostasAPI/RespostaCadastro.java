package com.grupo8.sugestordecurso.data.models.RespostasAPI;

public class RespostaCadastro {
    private boolean success;
    private int id;

    public boolean isSuccess(){
        return success;
    }
    public void setSuccess(boolean success){
        this.success = success;
    }

    public int getDados(){
        return id;
    }

    public void setDados(int dados){
        this.id = dados;
    }
}
