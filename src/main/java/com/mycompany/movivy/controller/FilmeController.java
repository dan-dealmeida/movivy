/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.controller;

/**
 *
 * @author zelen
 */
import com.mycompany.movivy.model.Filme;
import java.util.ArrayList;
import java.util.List;

public class FilmeController {

    private List<Filme> filmes;

    public FilmeController() {
        // Initially load films from persistence layer
        this.filmes = FilmeDAO.carregarFilmes();
    }

    public void adicionarFilme(Filme filme) {
        if (filme != null) {
            filmes.add(filme);
            FilmeDAO.salvarFilmes(filmes); // Persist the updated list
            System.out.println("Filme adicionado com sucesso: " + filme.getTitulo());
        } else {
            System.err.println("O filme é nulo e não pode ser adicionado.");
        }
    }

    public void exibirFilmes() {
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            filmes.forEach(filme -> System.out.println(filme));
            
        }
    }
     public List<Filme> getFilmes() {
        return filmes;
    }

    public void atualizarFilme(Filme filmeExistente, Filme filmeNovo) {
        if (filmeExistente != null && filmeNovo != null) {
            int index = filmes.indexOf(filmeExistente);
            if (index != -1) { // If the film exists in the list
                filmes.set(index, filmeNovo); // Update the film
                FilmeDAO.salvarFilmes(filmes); // Persist the updated list
                System.out.println("Filme atualizado com sucesso: " + filmeNovo.getTitulo());
            } else {
                System.err.println("O filme a ser atualizado não foi encontrado na lista.");
            }
        } else {
            System.err.println("O filme existente ou o novo filme é nulo e a atualização não pode ser realizada.");
        }
    }

    public void deletarFilme(Filme filme) {
        if (filme != null) {
            if (filmes.remove(filme)) { // Remove the film from the list
                FilmeDAO.salvarFilmes(filmes); // Persist the updated list
                System.out.println("Filme deletado com sucesso: " + filme.getTitulo());
            } else {
                System.err.println("O filme não foi encontrado na lista e não pode ser deletado.");
            }
        } else {
            System.err.println("O filme é nulo e não pode ser deletado.");
        }
    }

    public Filme getFilmeByTitulo(String titulo) {
        for (Filme filme : filmes) {
            if (filme.getTitulo().equals(titulo)) {
                return filme;
            }
        }
        return null; 
    }
}
