/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.controller;

/**
 *
 * @author zelen
 */

import com.mycompany.movivy.model.Studio;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudioDAO {
    private static final String NOME_ARQUIVO = "studios.txt";
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
    public Studio buscarPorLocal(String local) {
        List<Studio> studios = lerTodos();
        for (Studio studio : studios) {
            if (studio.getNome().equals(local)) {
                return studio;
            }
        }
        return null;
    }

    // Salvar um estúdio
    public void salvar(Studio studio) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_COMPLETO, true))) {
            String linha = criarLinhaParaStudio(studio);
            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar estúdio: " + e.getMessage());
        }
    }

    // Ler todos os estúdios
    public List<Studio> lerTodos() {
        List<Studio> studios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_COMPLETO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Studio studio = parseLinhaParaStudio(linha);
                if (studio != null) {
                    studios.add(studio);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler estúdios: " + e.getMessage());
        }
        return studios;
    }

    // Atualizar um estúdio
    public void atualizar(Studio studio) {
        List<Studio> studios = lerTodos();
        boolean atualizado = false;

        for (int i = 0; i < studios.size(); i++) {
            if (studios.get(i).getNome().equals(studio.getNome())) {
                studios.set(i, studio);
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            reescreverArquivoComStudios(studios);
        }
    }

    // Remover um estúdio pelo local
    public void remover(String local) {
        List<Studio> studios = lerTodos();
        if (studios.removeIf(studio -> studio.getNome().equals(local))) {
            reescreverArquivoComStudios(studios);
        }
    }

    private void reescreverArquivoComStudios(List<Studio> studios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_COMPLETO, false))) {
            for (Studio studio : studios) {
                bw.write(criarLinhaParaStudio(studio));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao reescrever arquivo de estúdios: " + e.getMessage());
        }
    }

    private String criarLinhaParaStudio(Studio studio) {
        return studio.getNome() + ";" + studio.getAnoFundacao() + ";" + studio.getPresidente();
    }

    private Studio parseLinhaParaStudio(String linha) {
        String[] partes = linha.split(";");
        if (partes.length == 3) {
            try {
                return new Studio(partes[0], Integer.parseInt(partes[1]), partes[2]);
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter dados do estúdio: " + e.getMessage());
                return null;
            }
        }
        return null;
    }
}

