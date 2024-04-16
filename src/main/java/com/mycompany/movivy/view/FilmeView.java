/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.view;

/**
 *
 * @author zelen
 */
import com.mycompany.movivy.model.Filme;
import java.util.List;


public class FilmeView {
    public void exibirFilmes(List<Filme> filmes) {
        for (Filme filme : filmes) {
            System.out.println("Título: " + filme.getTitulo());
            System.out.println("Gênero: " + filme.getGenero());
            System.out.println("Ano: " + filme.getAno());
            System.out.println("Diretor: " + filme.getDiretor());
            System.out.println("-----------------------");
        }
    }
}

