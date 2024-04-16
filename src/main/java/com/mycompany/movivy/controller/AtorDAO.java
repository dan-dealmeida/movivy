/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.controller;

/**
 *
 * @author zelen
 */
import com.mycompany.movivy.model.Ator;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AtorDAO {
    private static final String NOME_ARQUIVO = "atores.txt";
    private static final String DIRETORIO_DATA = "data";
    private static final String CAMINHO_COMPLETO;

    static {
        // Construindo o caminho relativo ao diretório atual do projeto
        Path diretorioAtual = Paths.get("").toAbsolutePath();
        Path caminhoData = Paths.get(diretorioAtual.toString(), DIRETORIO_DATA);
        CAMINHO_COMPLETO = Paths.get(caminhoData.toString(), NOME_ARQUIVO).toString();

        // Verifica se a pasta data existe, caso contrário, tenta criar
        try {
            Files.createDirectories(caminhoData);
        } catch (IOException e) {
            System.err.println("Erro ao criar o diretório 'data': " + e.getMessage());
        }
    }
    
    public Ator buscarPorNome(String nome) {
        List<Ator> atores = lerTodos();
        for (Ator ator : atores) {
            if (ator.getNome().equals(nome)) {
                return ator;
            }
        }
        return null; 
    }

    // Create - Salvar um ator
    public void salvar(Ator ator) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_COMPLETO, true))) {
            String linha = criarLinhaParaAtor(ator);
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar ator: " + e.getMessage());
        }
    }

    // Read - Ler todos os atores
    public List<Ator> lerTodos() {
        List<Ator> atores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_COMPLETO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Ator ator = parseLinhaParaAtor(linha);
                if (ator != null) {
                    atores.add(ator);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler atores: " + e.getMessage());
        }
        return atores;
    }

    // Update - Atualizar um ator
    public void atualizar(Ator ator) {
        List<Ator> atores = lerTodos();
        boolean atualizado = false;

        for (int i = 0; i < atores.size(); i++) {
            if (atores.get(i).getNome().equals(ator.getNome())) {
                atores.set(i, ator);
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            reescreverArquivoComAtors(atores);
        }
    }

    // Delete - Remover um ator pelo nome
    public void remover(String nomeAtor) {
        List<Ator> atores = lerTodos();
        if (atores.removeIf(ator -> ator.getNome().equals(nomeAtor))) {
            reescreverArquivoComAtors(atores);
        }
    }

    private void reescreverArquivoComAtors(List<Ator> atores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_COMPLETO, false))) {
            for (Ator ator : atores) {
                bw.write(criarLinhaParaAtor(ator));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever arquivo de atores: " + e.getMessage());
        }
    }

    private String criarLinhaParaAtor(Ator ator) {
        return ator.getNome() + ";" + ator.getIdade() + ";" + ator.getAltura() + ";" + ator.getNacionalidade();
    }

    private Ator parseLinhaParaAtor(String linha) {
        String[] partes = linha.split(";");
        if (partes.length == 4) {
            try {
                return new Ator(partes[0], Integer.parseInt(partes[1]), Double.parseDouble(partes[2]), partes[3]);
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter dados do ator: " + e.getMessage());
                return null;
            }
        }
        return null;
    }
}


