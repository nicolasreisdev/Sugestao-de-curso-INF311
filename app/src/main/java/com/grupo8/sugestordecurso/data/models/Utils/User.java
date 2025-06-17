package com.grupo8.sugestordecurso.data.models.Utils;

import java.io.Serializable;
import org.json.JSONObject;

public class User implements Serializable {

    private static User instance;

    private User() {};

    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    private int id = -1;
    private String nome;
    private String nomeSocial;
    private String email;
    private String telefone;
    private String cpf;
    private String dataNascimento;
    private double notaMatematica;
    private double notaPortugues;
    private double notaLiteratura;
    private double notaRedacao;
    private double notaQuimica;
    private double notaFisica;
    private double notaBiologia;
    private double notaGeografia;
    private double notaHistoria;
    private double notaFilosofia;
    private double notaSociologia;
    private double notaArtes;
    private String areaPreferencia;

    public double getNotaMatematica() {
        return notaMatematica;
    }

    public void setNotaMatematica(double notaMatematica) {
        this.notaMatematica = notaMatematica;
    }

    public double getNotaPortugues() {
        return notaPortugues;
    }

    public void setNotaPortugues(double notaPortugues) {
        this.notaPortugues = notaPortugues;
    }

    public double getNotaLiteratura() {
        return notaLiteratura;
    }

    public void setNotaLiteratura(double notaLiteratura) {
        this.notaLiteratura = notaLiteratura;
    }

    public double getNotaRedacao() {
        return notaRedacao;
    }

    public void setNotaRedacao(double notaRedacao) {
        this.notaRedacao = notaRedacao;
    }

    public double getNotaQuimica() {
        return notaQuimica;
    }

    public void setNotaQuimica(double notaQuimica) {
        this.notaQuimica = notaQuimica;
    }

    public double getNotaFisica() {
        return notaFisica;
    }

    public void setNotaFisica(double notaFisica) {
        this.notaFisica = notaFisica;
    }

    public double getNotaBiologia() {
        return notaBiologia;
    }

    public void setNotaBiologia(double notaBiologia) {
        this.notaBiologia = notaBiologia;
    }

    public double getNotaGeografia() {
        return notaGeografia;
    }

    public void setNotaGeografia(double notaGeografia) {
        this.notaGeografia = notaGeografia;
    }

    public double getNotaHistoria() {
        return notaHistoria;
    }

    public void setNotaHistoria(double notaHistoria) {
        this.notaHistoria = notaHistoria;
    }

    public double getNotaFilosofia() {
        return notaFilosofia;
    }

    public void setNotaFilosofia(double notaFilosofia) {
        this.notaFilosofia = notaFilosofia;
    }

    public double getNotaSociologia() {
        return notaSociologia;
    }

    public void setNotaSociologia(double notaSociologia) {
        this.notaSociologia = notaSociologia;
    }

    public double getNotaArtes() {
        return notaArtes;
    }

    public void setNotaArtes(double notaArtes) {
        this.notaArtes = notaArtes;
    }

    public String getAreaPreferencia() {
        return areaPreferencia;
    }

    public void setAreaPreferencia(String areaPreferencia) {
        this.areaPreferencia = areaPreferencia;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

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
