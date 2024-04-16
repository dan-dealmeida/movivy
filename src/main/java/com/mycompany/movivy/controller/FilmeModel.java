/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.controller;

/**
 *
 * @author zelen
 */
import com.mycompany.movivy.controller.FilmeDAO;
import com.mycompany.movivy.model.Filme;
import java.util.List;

public class FilmeModel {
    private List<Filme> filmes;

    public FilmeModel() {
        this.filmes = FilmeDAO.carregarFilmes();
    }

    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
        FilmeDAO.salvarFilmes(filmes);
    }

    public List<Filme> obterTodosFilmes() {
        return filmes;
    }

    public void atualizarFilme(Filme filmeAntigo, Filme novoFilme) {
        int index = filmes.indexOf(filmeAntigo);
        if (index != -1) {
            filmes.set(index, novoFilme);
            FilmeDAO.salvarFilmes(filmes);
        }
    }

    public void excluirFilme(Filme filme) {
        filmes.remove(filme);
        FilmeDAO.salvarFilmes(filmes);
    }
}


