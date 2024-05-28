package com.mycompany.movivy.model;

import java.io.Serializable;

public class Filme implements Serializable {
    private String titulo;
    private String genero;
    private int ano;
    private String diretor;

    public Filme(String titulo, String genero, int ano, String diretor) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.diretor = diretor;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Filme filme = (Filme) obj;
        return titulo.equals(filme.titulo) && genero.equals(filme.genero) && ano == filme.ano && diretor.equals(filme.diretor);
    }

    @Override
    public int hashCode() {
        int result = titulo.hashCode();
        result = 31 * result + genero.hashCode();
        result = 31 * result + ano;
        result = 31 * result + diretor.hashCode();
        return result;
    }
}
