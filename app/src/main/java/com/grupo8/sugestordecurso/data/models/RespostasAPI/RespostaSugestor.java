package com.grupo8.sugestordecurso.data.models.RespostasAPI;


import com.google.gson.annotations.SerializedName;

public class RespostaSugestor {
    private String curso;

    @SerializedName("probabilidade_aptidao")
    private float probabilidadeAptidao;

    private String area;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public float getProbabilidadeAptidao() {
        return probabilidadeAptidao;
    }

    public void setProbabilidadeAptidao(float probabilidadeAptidao) {
        this.probabilidadeAptidao = probabilidadeAptidao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
