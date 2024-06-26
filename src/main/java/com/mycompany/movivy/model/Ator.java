package com.mycompany.movivy.model;

import java.io.Serializable;

public class Ator implements Serializable {
    // Atributos da classe
    private String nome;
    private int idade;
    private double altura; // Altura em metros
    private String nacionalidade;

    // Construtor
    public Ator(String nome, int idade, double altura, String nacionalidade) {
        this.nome = nome;
        this.idade = idade;
        this.altura = altura;
        this.nacionalidade = nacionalidade;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
