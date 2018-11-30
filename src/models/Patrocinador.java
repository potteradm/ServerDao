package models;

import java.io.Serializable;

public class Patrocinador implements Serializable {
    private String cpfCnpj;
    private String telefone;
    private String usuario;
    private String senha;
    private String nome;

    public Patrocinador(String cpfCnpj, String telefone, String usuario, String senha, String nome) {
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
    }

    public Patrocinador(String telefone, String usuario, String senha, String nome) {
        this.telefone = telefone;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
    }

    public Patrocinador(String cpfCnpj) {

        this.cpfCnpj = cpfCnpj;
    }

    public Patrocinador() {
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
