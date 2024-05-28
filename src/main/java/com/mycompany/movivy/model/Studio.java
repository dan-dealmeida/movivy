package com.mycompany.movivy.model;

import java.io.Serializable;

public class Studio implements Serializable {
    private String nome;
    private int anoFundacao;
    private String presidente;

    public Studio(String local, int anoFundacao, String presidente) {
        this.nome = local;
        this.anoFundacao = anoFundacao;
        this.presidente = presidente;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }
}
