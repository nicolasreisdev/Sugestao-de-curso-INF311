package com.grupo8.sugestordecurso.data.models.Utils;

//cursos em alta no mercado de cada area (pesquisados no google)
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

    public String[] getTendExatas() {
        return this.tendExatas;
    }

    public String[] getTendHumanas() {
        return this.tendHumanas;
    }

    public String[] getTendBiologicas() {
        return this.tendBiologicas;
    }

    public String[] getTendArtes() {
        return this.tendArtes;
    }

    public String[] getTendTecnologia() {
        return this.tendTecnologia;
    }

    public String[] getTendLinguagens() {
        return this.tendLinguagens;
    }

    public String[] getTendComunicacao() {
        return this.tendComunicacao;
    }
}
