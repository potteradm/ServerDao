package models;

import java.io.Serializable;

public class Local implements Serializable {
    private int codigo=-1;
    private String nome;
    private String endereco;
    private String observacoes;

    public Local() {
    }

    public Local(String nome, String endereco, String observacoes) {
        this.nome = nome;
        this.endereco = endereco;
        this.observacoes = observacoes;
    }

    public Local(int codigo) {
        this.codigo = codigo;
    }

    public Local(int codigo, String nome, String endereco, String observacoes) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.observacoes = observacoes;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }


    @Override
    public String toString() {
        return this.nome;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Local)
            return codigo==((Local)obj).codigo;

        return super.equals(obj);
    }
}
