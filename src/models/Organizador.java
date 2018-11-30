package models;

import java.io.Serializable;

public class Organizador implements Serializable {
    private String cpf_cnpj;
    private String email;
    private String telefone;
    private String nome;
    private String usuario;
    private String senha;

    public Organizador(String cpf_cnpj, String email, String telefone, String nome, String usuario, String senha) {
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Organizador(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public Organizador(String email, String telefone, String nome, String usuario, String senha) {
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Organizador() {
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}