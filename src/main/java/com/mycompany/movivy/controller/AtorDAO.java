package com.mycompany.movivy.controller;

import com.mycompany.movivy.model.Ator;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AtorDAO {
    private static final String NOME_ARQUIVO = "atores.dat";
    private static final String DIRETORIO_DATA = "data";
    private static final String CAMINHO_COMPLETO;

    static {
        
        Path diretorioAtual = Paths.get("").toAbsolutePath();
        Path caminhoData = Paths.get(diretorioAtual.toString(), DIRETORIO_DATA);
        CAMINHO_COMPLETO = Paths.get(caminhoData.toString(), NOME_ARQUIVO).toString();

        
        try {
            Files.createDirectories(caminhoData);
        } catch (IOException e) {
            System.err.println("Error creating the 'data' directory: " + e.getMessage());
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

    public void salvar(Ator ator) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_COMPLETO, true))) {
            oos.writeObject(ator);
        } catch (IOException e) {
            System.err.println("Error saving actor: " + e.getMessage());
        }
    }

    public List<Ator> lerTodos() {
        List<Ator> atores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_COMPLETO))) {
            while (true) {
                try {
                    Ator ator = (Ator) ois.readObject();
                    atores.add(ator);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading actors: " + e.getMessage());
        }
        return atores;
    }

    public void atualizar(Ator atorAtualizado) {
        List<Ator> atores = lerTodos();
        for (int i = 0; i < atores.size(); i++) {
            Ator ator = atores.get(i);
            if (ator.getNome().equals(atorAtualizado.getNome())) {
                atores.set(i, atorAtualizado);
                break;
            }
        }
        reescreverArquivo(atores);
    }

    public void remover(String nomeAtor) {
        List<Ator> atores = lerTodos();
        atores.removeIf(ator -> ator.getNome().equals(nomeAtor));
        reescreverArquivo(atores);
    }

    private void reescreverArquivo(List<Ator> atores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_COMPLETO, false))) {
            for (Ator ator : atores) {
                oos.writeObject(ator);
            }
        } catch (IOException e) {
            System.err.println("Error rewriting file: " + e.getMessage());
        }
    }
}
