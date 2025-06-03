package com.grupo8.sugestordecurso.data.models.BodyAPI;


import android.util.Log;

import java.util.ArrayList;

class Pessoa{
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int ID) {
        Log.i("API Teste", "Cheguei 2");
        this.id = ID;
    }
}

class campoPersonalizados{
    private ArrayList<Double> campopersonalizado_6_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_7_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_8_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_9_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_10_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_11_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_12_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_13_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_14_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_15_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_16_compl_proc = new ArrayList<>();
    private ArrayList<Double> campopersonalizado_17_compl_proc = new ArrayList<>();


    public void setCampopersonalizado_6_compl_proc(double notaMatematica) {
        this.campopersonalizado_6_compl_proc.add(notaMatematica);
    }

    public void setCampopersonalizado_7_compl_proc(double notaPortugues) {
        this.campopersonalizado_7_compl_proc.add(notaPortugues);
    }

    public void setCampopersonalizado_8_compl_proc(double notaLiteratura) {
        this.campopersonalizado_8_compl_proc.add(notaLiteratura);
    }

    public void setCampopersonalizado_9_compl_proc(double notaRedacao) {
        this.campopersonalizado_9_compl_proc.add(notaRedacao);
    }

    public void setCampopersonalizado_10_compl_proc(double notaQuimica) {
        this.campopersonalizado_10_compl_proc.add(notaQuimica);
    }

    public void setCampopersonalizado_11_compl_proc(double notaFisica) {
        this.campopersonalizado_11_compl_proc.add(notaFisica);
    }

    public void setCampopersonalizado_12_compl_proc(double notaBiologia) {
        this.campopersonalizado_12_compl_proc.add(notaBiologia);
    }

    public void setCampopersonalizado_13_compl_proc(double notaGeografia) {
        this.campopersonalizado_13_compl_proc.add(notaGeografia);
    }

    public void setCampopersonalizado_14_compl_proc(double notaHistoria) {
        this.campopersonalizado_14_compl_proc.add(notaHistoria);
    }

    public void setCampopersonalizado_15_compl_proc(double notaFilosofia) {
        this.campopersonalizado_15_compl_proc.add(notaFilosofia);
    }

    public void setCampopersonalizado_16_compl_proc(double notaSociologia) {
        this.campopersonalizado_16_compl_proc.add(notaSociologia);
    }

    public void setCampopersonalizado_17_compl_proc(double notaArtes) {
        this.campopersonalizado_17_compl_proc.add(notaArtes);
    }
}


public class BodyNotas {
    private int tipo = 104; // canal API para o Fluxo de Automação
    private Pessoa pessoa;
    private campoPersonalizados camposPersonalizados;
    private int origem = 7;
    private String token = "f2240ed12dca63c0a425f028cd88500e";

    public BodyNotas(){
        pessoa = new Pessoa();
        camposPersonalizados = new campoPersonalizados();
    }

    public void setMatematica(double nota){
        this.camposPersonalizados.setCampopersonalizado_6_compl_proc(nota);
    }
    public void setPortugues(double nota){
        this.camposPersonalizados.setCampopersonalizado_7_compl_proc(nota);
    }
    public void setLiteratura(double nota){
        this.camposPersonalizados.setCampopersonalizado_8_compl_proc(nota);
    }
    public void setRedacao(double nota){
        this.camposPersonalizados.setCampopersonalizado_9_compl_proc(nota);
    }
    public void setQuimica(double nota){
        this.camposPersonalizados.setCampopersonalizado_10_compl_proc(nota);
    }
    public void setFisica(double nota){
        this.camposPersonalizados.setCampopersonalizado_11_compl_proc(nota);
    }
    public void setBiologia(double nota){
        this.camposPersonalizados.setCampopersonalizado_12_compl_proc(nota);
    }
    public void setGeografia(double nota){
        this.camposPersonalizados.setCampopersonalizado_13_compl_proc(nota);
    }
    public void setHistoria(double nota){
        this.camposPersonalizados.setCampopersonalizado_14_compl_proc(nota);
    }
    public void setFilosofia(double nota){
        this.camposPersonalizados.setCampopersonalizado_15_compl_proc(nota);
    }
    public void setSociologia(double nota){
        this.camposPersonalizados.setCampopersonalizado_16_compl_proc(nota);
    }
    public void setArtes(double nota){
        this.camposPersonalizados.setCampopersonalizado_17_compl_proc(nota);
    }

    public void setPessoa(int id) {
        Log.i("API Teste", "Cheguei 1");
        this.pessoa.setId(id);
    }
}
