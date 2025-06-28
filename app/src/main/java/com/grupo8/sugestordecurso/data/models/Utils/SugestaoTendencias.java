package com.grupo8.sugestordecurso.data.models.Utils;

import java.util.ArrayList;

public class SugestaoTendencias {
    private String[] tendSaude = {"Medicina", "Enfermagem","Fisioterapia"};
    private String[] tendExatas = {"Engenharia Civil", "Engenharia Ambiental","Engenharia Sanitária"};
    private String[] tendHumanas = {"Administração", "Pedagogia","Direito"};
    private String[] tendBiologicas = {"Medicina Veterinária", "Nutrição","Biomedicina"};
    private String[] tendArtes = {"Design Gráfico", "Design de Moda", "Artes Visuais"};
    private String[] tendTecnologia = {"Ciência da Computação", "Sistemas de Informação", "ADS"};
    private String[] tendLinguagens = {"Tradução e Interpretação", "Letras Inglês", "Linguística"};
    private String[] tendComunicacao = {"Jornalismo", "Marketing", "Publicidade e Propaganda"};

    public String[] getTendSaude() {
        return this.tendSaude;
    }

    public void setTendSaude(String[] tendSaude) {
        this.tendSaude = tendSaude;
    }

    public String[] getTendExatas() {
        return this.tendExatas;
    }

    public void setTendExatas(String[] tendExatas) {
        this.tendExatas = tendExatas;
    }

    public String[] getTendHumanas() {
        return this.tendHumanas;
    }

    public void setTendHumanas(String[] tendHumanas) {
        this.tendHumanas = tendHumanas;
    }

    public String[] getTendBiologicas() {
        return this.tendBiologicas;
    }

    public void setTendBiologicas(String[] tendBiologicas) {
        this.tendBiologicas = tendBiologicas;
    }

    public String[] getTendArtes() {
        return this.tendArtes;
    }

    public void setTendArtes(String[] tendArtes) {
        this.tendArtes = tendArtes;
    }

    public String[] getTendTecnologia() {
        return this.tendTecnologia;
    }

    public void setTendTecnologia(String[] tendTecnologia) {
        this.tendTecnologia = tendTecnologia;
    }

    public String[] getTendLinguagens() {
        return this.tendLinguagens;
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
