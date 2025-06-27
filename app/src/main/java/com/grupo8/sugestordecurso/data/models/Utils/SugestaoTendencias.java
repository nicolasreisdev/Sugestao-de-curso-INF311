package com.grupo8.sugestordecurso.data.models.Utils;

import java.util.ArrayList;

public class SugestaoTendencias {
    private String[] tendSaude = {"Medicina", "Enfermagem","Fisioterapia"};
    private String[] tendExatas = {"Matemática", "Física","Engenharia Civil"};
    private String[] tendHumanas = {"Administração", "Pedagogia","Direito"};
    private String[] tendBiologicas = {"Medicina Veterinária", "Nutrição","Biomedicina"};
    private String[] tendArtes = {"Design Gráfico", "Design de Moda", "Artes Visuais"};
    private String[] tendTecnologia = {"Ciência da Computação", "Sistemas de Informação", "ADS"};
    private String[] tendLinguagens = {"Letras Francês", "Letras Inglês", "Letras Mandarim"};
    private String[] tendComunicacao = {"Jornalismo", "Marketing", "Publicidade e Propaganda"};

    public String[] getTendSaude() {
        return tendSaude;
    }

    public void setTendSaude(String[] tendSaude) {
        this.tendSaude = tendSaude;
    }

    public String[] getTendExatas() {
        return tendExatas;
    }

    public void setTendExatas(String[] tendExatas) {
        this.tendExatas = tendExatas;
    }

    public String[] getTendHumanas() {
        return tendHumanas;
    }

    public void setTendHumanas(String[] tendHumanas) {
        this.tendHumanas = tendHumanas;
    }

    public String[] getTendBiologicas() {
        return tendBiologicas;
    }

    public void setTendBiologicas(String[] tendBiologicas) {
        this.tendBiologicas = tendBiologicas;
    }

    public String[] getTendArtes() {
        return tendArtes;
    }

    public void setTendArtes(String[] tendArtes) {
        this.tendArtes = tendArtes;
    }

    public String[] getTendTecnologia() {
        return tendTecnologia;
    }

    public void setTendTecnologia(String[] tendTecnologia) {
        this.tendTecnologia = tendTecnologia;
    }

    public String[] getTendLinguagens() {
        return tendLinguagens;
    }

    public void setTendLinguagens(String[] tendLinguagens) {
        this.tendLinguagens = tendLinguagens;
    }

    public void setTendComunicacao(String[] tendComunicacao) {
        this.tendComunicacao = tendComunicacao;
    }

    public String[] getTendComunicacao() {
        return this.tendComunicacao;
    }
}
