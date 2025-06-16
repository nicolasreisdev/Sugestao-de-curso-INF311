package com.grupo8.sugestordecurso.data.models.RespostasAPI;

import java.util.ArrayList;

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
    private String campopersonalizado_18_compl_proc;

    public ArrayList<Double> getCampopersonalizado_6_compl_proc() {
        return campopersonalizado_6_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_7_compl_proc() {
        return campopersonalizado_7_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_8_compl_proc() {
        return campopersonalizado_8_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_9_compl_proc() {
        return campopersonalizado_9_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_10_compl_proc() {
        return campopersonalizado_10_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_11_compl_proc() {
        return campopersonalizado_11_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_12_compl_proc() {
        return campopersonalizado_12_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_13_compl_proc() {
        return campopersonalizado_13_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_14_compl_proc() {
        return campopersonalizado_14_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_15_compl_proc() {
        return campopersonalizado_15_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_16_compl_proc() {
        return campopersonalizado_16_compl_proc;
    }

    public ArrayList<Double> getCampopersonalizado_17_compl_proc() {
        return campopersonalizado_17_compl_proc;
    }

    public String getCampopersonalizado_18_compl_proc() {
        return campopersonalizado_18_compl_proc;
    }
}

class Dados {
    private campoPersonalizados camposPersonalizados;

    public campoPersonalizados getCamposPersonalizados() {
        return camposPersonalizados;
    }
}

public class RespostaBuscarNotas {
    private boolean success;
    private ArrayList<Dados> dados = new ArrayList<>();

    public double getNotaMatematica(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_6_compl_proc().get(0);
    }

    public double getNotaPortugues(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_7_compl_proc().get(0);
    }

    public double getNotaLiteratura(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_8_compl_proc().get(0);
    }

    public double getNotaRedacao(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_9_compl_proc().get(0);
    }

    public double getNotaQuimica(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_10_compl_proc().get(0);
    }

    public double getNotaFisica(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_11_compl_proc().get(0);
    }

    public double getNotaBiologia(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_12_compl_proc().get(0);
    }

    public double getNotaGeografia(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_13_compl_proc().get(0);
    }

    public double getNotaHistoria(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_14_compl_proc().get(0);
    }

    public double getNotaFilosofia(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_15_compl_proc().get(0);
    }

    public double getNotaSociologia(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_16_compl_proc().get(0);
    }

    public double getNotaArtes(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_17_compl_proc().get(0);
    }

    public String getAreaPreferencia(){
        return dados.get(0).getCamposPersonalizados().getCampopersonalizado_18_compl_proc();
    }
}
