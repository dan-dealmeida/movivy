package com.mycompany.movivy.controller;

import com.mycompany.movivy.model.Filme;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private static final String PASTA_DADOS = "data";
    private static final String ARQUIVO_FILMES = PASTA_DADOS + File.separator + "filmes.dat";

    public static void salvarFilmes(List<Filme> filmes) {
        File pastaDados = new File(PASTA_DADOS);
        if (!pastaDados.exists()) {
            pastaDados.mkdir();
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_FILMES))) {
            outputStream.writeObject(filmes);
            System.out.println("Filmes salvos com sucesso em " + ARQUIVO_FILMES);
        } catch (IOException e) {
            System.err.println("Erro ao salvar filmes: " + e.getMessage());
        }
    }

    public static List<Filme> carregarFilmes() {
        List<Filme> filmes = new ArrayList<>();
        File arquivo = new File(ARQUIVO_FILMES);
        if (arquivo.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_FILMES))) {
                filmes = (List<Filme>) inputStream.readObject();
                System.out.println("Filmes carregados com sucesso de " + ARQUIVO_FILMES);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar filmes: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo de filmes encontrado. Criando novo arquivo...");
        }
        return filmes;
    }

    public static void atualizarFilme(Filme filmeAtualizado, List<Filme> listaFilmes) {
        
        for (Filme filme : listaFilmes) {
            if (filme.getTitulo().equals(filmeAtualizado.getTitulo())) {
               
                filme.setGenero(filmeAtualizado.getGenero());
                filme.setAno(filmeAtualizado.getAno());
                filme.setDiretor(filmeAtualizado.getDiretor());
                break;
            }
        }
        
        salvarFilmes(listaFilmes);
        System.out.println("Filme atualizado com sucesso: " + filmeAtualizado.getTitulo());
    }
    
    public static void excluirFilme(Filme filmeExcluido, List<Filme> listaFilmes) {
        
        for (Filme filme : listaFilmes) {
            if (filme.getTitulo().equals(filmeExcluido.getTitulo())) {
                listaFilmes.remove(filme); 
                System.out.println("Filme excluído com sucesso: " + filmeExcluido.getTitulo());
                break;
            }
        }
        
        salvarFilmes(listaFilmes);
    }

   
    public static Filme buscarPorTitulo(String titulo) {
        List<Filme> filmes = carregarFilmes();
        for (Filme filme : filmes) {
            if (filme.getTitulo().equals(titulo)) {
                return filme;
            }
        }
        return null; 
    }
     public static void adicionarFilme(Filme filme) {
        List<Filme> filmes = carregarFilmes();
        filmes.add(filme);
        salvarFilmes(filmes);
    }
}
