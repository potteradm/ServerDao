package models;

import java.io.Serializable;

public class TipoIngresso implements Serializable {
    private int codigo=-1;
    private String nome;
    private String descricao;
    private double preco;
    private int evento_codigo;

    public TipoIngresso(String nome, String descricao, double preco, int evento_codigo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.evento_codigo = evento_codigo;
    }


    public TipoIngresso(int codigo, String nome, String descricao, double preco, int evento_codigo) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.evento_codigo = evento_codigo;
    }

    public TipoIngresso(int codigo) {
        this.codigo = codigo;
    }

    public TipoIngresso() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEvento_codigo() {
        return evento_codigo;
    }

    public void setEvento_codigo(int evento_codigo) {
        this.evento_codigo = evento_codigo;
    }
}
