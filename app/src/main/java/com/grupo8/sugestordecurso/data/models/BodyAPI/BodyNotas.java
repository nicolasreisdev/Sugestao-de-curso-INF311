package com.grupo8.sugestordecurso.data.models.BodyAPI;


import java.util.List;

class Pessoa{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class Nota{
    private double nota;

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}

class campoPersonalizados{
    private List<Nota> campopersonalizado_6_compl_proc;
    private List<Nota> campopersonalizado_7_compl_proc;
    private List<Nota> campopersonalizado_8_compl_proc;
    private List<Nota> campopersonalizado_9_compl_proc;
    private List<Nota> campopersonalizado_10_compl_proc;
    private List<Nota> campopersonalizado_11_compl_proc;
    private List<Nota> campopersonalizado_12_compl_proc;
    private List<Nota> campopersonalizado_13_compl_proc;
    private List<Nota> campopersonalizado_14_compl_proc;
    private List<Nota> campopersonalizado_15_compl_proc;
    private List<Nota> campopersonalizado_16_compl_proc;
    private List<Nota> campopersonalizado_17_compl_proc;

    public void setCampopersonalizado_6_compl_proc(double notaMatematica) {
        Nota nota1 = new Nota();
        nota1.setNota(notaMatematica);
        this.campopersonalizado_6_compl_proc.add(nota1);
    }

    public void setCampopersonalizado_7_compl_proc(double notaPortugues) {
        Nota nota2 = new Nota();
        nota2.setNota(notaPortugues);
        this.campopersonalizado_7_compl_proc.add(nota2);
    }

    public void setCampopersonalizado_8_compl_proc(double notaLiteratura) {
        Nota nota3 = new Nota();
        nota3.setNota(notaLiteratura);
        this.campopersonalizado_8_compl_proc.add(nota3);
    }

    public void setCampopersonalizado_9_compl_proc(double notaRedacao) {
        Nota nota4 = new Nota();
        nota4.setNota(notaRedacao);
        this.campopersonalizado_9_compl_proc.add(nota4);
    }

    public void setCampopersonalizado_10_compl_proc(double notaQuimica) {
        Nota nota5 = new Nota();
        nota5.setNota(notaQuimica);
        this.campopersonalizado_10_compl_proc.add(nota5);
    }

    public void setCampopersonalizado_11_compl_proc(double notaFisica) {
        Nota nota6 = new Nota();
        nota6.setNota(notaFisica);
        this.campopersonalizado_11_compl_proc.add(nota6);
    }

    public void setCampopersonalizado_12_compl_proc(double notaBiologia) {
        Nota nota7 = new Nota();
        nota7.setNota(notaBiologia);
        this.campopersonalizado_12_compl_proc.add(nota7);
    }

    public void setCampopersonalizado_13_compl_proc(double notaGeografia) {
        Nota nota8 = new Nota();
        nota8.setNota(notaGeografia);
        this.campopersonalizado_13_compl_proc.add(nota8);
    }

    public void setCampopersonalizado_14_compl_proc(double notaHistoria) {
        Nota nota9 = new Nota();
        nota9.setNota(notaHistoria);
        this.campopersonalizado_14_compl_proc.add(nota9);
    }

    public void setCampopersonalizado_15_compl_proc(double notaFilosofia) {
        Nota nota10 = new Nota();
        nota10.setNota(notaFilosofia);
        this.campopersonalizado_15_compl_proc.add(nota10);
    }

    public void setCampopersonalizado_16_compl_proc(double notaSociologia) {
        Nota nota11 = new Nota();
        nota11.setNota(notaSociologia);
        this.campopersonalizado_16_compl_proc.add(nota11);
    }

    public void setCampopersonalizado_17_compl_proc(double notaArtes) {
        Nota nota12 = new Nota();
        nota12.setNota(notaArtes);
        this.campopersonalizado_17_compl_proc.add(nota12);
    }
}


public class BodyNotas {
    private int tipo = 104; // canal API para o Fluxo de Automação
    private Pessoa pessoa;
    private campoPersonalizados camposNotas;
    private static final int origem = 7;
    private static final String token = "f2240ed12dca63c0a425f028cd88500e";

    public void setMatematica(double nota){
        this.camposNotas.setCampopersonalizado_6_compl_proc(nota);
    }
    public void setPortugues(double nota){
        this.camposNotas.setCampopersonalizado_7_compl_proc(nota);
    }
    public void setLiteratura(double nota){
        this.camposNotas.setCampopersonalizado_8_compl_proc(nota);
    }
    public void setRedacao(double nota){
        this.camposNotas.setCampopersonalizado_9_compl_proc(nota);
    }
    public void setQuimica(double nota){
        this.camposNotas.setCampopersonalizado_10_compl_proc(nota);
    }
    public void setFisica(double nota){
        this.camposNotas.setCampopersonalizado_11_compl_proc(nota);
    }
    public void setBiologia(double nota){
        this.camposNotas.setCampopersonalizado_12_compl_proc(nota);
    }
    public void setGeografia(double nota){
        this.camposNotas.setCampopersonalizado_13_compl_proc(nota);
    }
    public void setHistoria(double nota){
        this.camposNotas.setCampopersonalizado_14_compl_proc(nota);
    }
    public void setFilosofia(double nota){
        this.camposNotas.setCampopersonalizado_15_compl_proc(nota);
    }
    public void setSociologia(double nota){
        this.camposNotas.setCampopersonalizado_16_compl_proc(nota);
    }
    public void setArtes(double nota){
        this.camposNotas.setCampopersonalizado_17_compl_proc(nota);
    }

    public void setPessoa(int id) {
        this.pessoa.setId(id);
    }
}
