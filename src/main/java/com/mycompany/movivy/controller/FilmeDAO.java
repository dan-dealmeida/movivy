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
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private static final String PASTA_DADOS = "data";
    private static final String ARQUIVO_FILMES = PASTA_DADOS + File.separator + "filmes.txt";

    public static void salvarFilmes(List<Filme> filmes) {
        File pastaDados = new File(PASTA_DADOS);
        if (!pastaDados.exists()) {
            pastaDados.mkdir();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_FILMES))) {
            for (Filme filme : filmes) {
                writer.println(filme.getTitulo() + "," + filme.getGenero() + "," + filme.getAno() + "," + filme.getDiretor());
            }
            System.out.println("Filmes salvos com sucesso em " + ARQUIVO_FILMES);
        } catch (IOException e) {
            System.err.println("Erro ao salvar filmes: " + e.getMessage());
        }
    }

    public static List<Filme> carregarFilmes() {
        List<Filme> filmes = new ArrayList<>();
        File arquivo = new File(ARQUIVO_FILMES);
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_FILMES))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(",");
                    if (partes.length == 4) {
                        String titulo = partes[0];
                        String genero = partes[1];
                        int ano = Integer.parseInt(partes[2]);
                        String diretor = partes[3];
                        filmes.add(new Filme(titulo, genero, ano, diretor));
                    }
                }
                System.out.println("Filmes carregados com sucesso de " + ARQUIVO_FILMES);
            } catch (IOException | NumberFormatException e) {
                System.err.println("Erro ao carregar filmes: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo de filmes encontrado. Criando novo arquivo...");
        }
        return filmes;
    }

    public static void atualizarFilme(Filme filmeAtualizado, List<Filme> listaFilmes) {
        // Iterar sobre a lista de filmes para encontrar o filme a ser atualizado
        for (Filme filme : listaFilmes) {
            if (filme.getTitulo().equals(filmeAtualizado.getTitulo())) {
                // Atualizar os detalhes do filme
                filme.setGenero(filmeAtualizado.getGenero());
                filme.setAno(filmeAtualizado.getAno());
                filme.setDiretor(filmeAtualizado.getDiretor());
                break;
            }
        }
        // Salvar a lista atualizada no arquivo
        salvarFilmes(listaFilmes);
        System.out.println("Filme atualizado com sucesso: " + filmeAtualizado.getTitulo());
    }
    
    public static void excluirFilme(Filme filmeExcluido, List<Filme> listaFilmes) {
    // Iterar sobre a lista de filmes para encontrar o filme a ser excluído
    for (Filme filme : listaFilmes) {
        if (filme.getTitulo().equals(filmeExcluido.getTitulo())) {
            listaFilmes.remove(filme); // Remover o filme da lista
            System.out.println("Filme excluído com sucesso: " + filmeExcluido.getTitulo());
            break;
        }
    }
    // Salvar a lista atualizada no arquivo
    salvarFilmes(listaFilmes);
}

}
