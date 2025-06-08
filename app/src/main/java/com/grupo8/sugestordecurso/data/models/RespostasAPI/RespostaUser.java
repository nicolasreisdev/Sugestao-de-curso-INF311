package com.grupo8.sugestordecurso.data.models.RespostasAPI;


import java.util.ArrayList;

class TelefonePrincipal{
    private String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}


class Telefone{
    private TelefonePrincipal principal;

    public TelefonePrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(TelefonePrincipal principal) {
        this.principal = principal;
    }
}

class EmailPrincipal{
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


class Email{
    private EmailPrincipal principal;

    public EmailPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(EmailPrincipal principal) {
        this.principal = principal;
    }
}

class DadosUser {
    private int id;
    private String nome;
    private String nomeSocial;
    private String cpf;
    private String datanascimento;
    private Email emails;
    private Telefone telefones;

    public Telefone getTelefones() {
        return telefones;
    }

    public void setTelefones(Telefone telefones) {
        this.telefones = telefones;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Email getEmails() {
        return emails;
    }

    public void setEmails(Email emails) {
        this.emails = emails;
    }

    public int getId() {
        return id;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

public class RespostaUser {
    private boolean success;
    private ArrayList<DadosUser> dados;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDadosNome() {
        return dados.get(0).getNome();
    }

    public String getDadosNomeSocial() { return dados.get(0).getNomeSocial(); }

    public int getDadosID(){ return dados.get(0).getId(); }

    public String getDadosCPF(){ return dados.get(0).getCpf(); }

    public String getDadosNascimento(){ return dados.get(0).getDatanascimento(); }

    public String getDadosEmail(){ return dados.get(0).getEmails().getPrincipal().getEmail(); }

    public String getDadosTelefone(){ return dados.get(0).getTelefones().getPrincipal().getTelefone(); }
}
