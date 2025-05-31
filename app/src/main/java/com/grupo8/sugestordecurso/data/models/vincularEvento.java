package com.grupo8.sugestordecurso.data.models;


class Pessoa{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class campoPersonalizado{
    private String field;
    private int nota;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}


public class vincularEvento {
    private int tipo;
    private Pessoa pessoa;
    private campoPersonalizado campoEvento;
    private static final int origem = 7;
    private static final String token = "f2240ed12dca63c0a425f028cd88500e";

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(int id) {
        this.pessoa.setId(id);
    }

    public campoPersonalizado getCampoEvento() {
        return campoEvento;
    }

    public void setCampoEvento(String campo, int nota) {
        this.campoEvento.setField(campo);
        this.campoEvento.setNota(nota);
    }
}
