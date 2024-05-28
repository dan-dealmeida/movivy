package com.mycompany.movivy.controller;

import com.mycompany.movivy.model.Studio;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudioDAO {
    private static final String FILE_NAME = "studios.dat";
    private static final String DATA_DIRECTORY = "data";
    private static final String FULL_PATH;

    static {
        Path currentDirectory = Paths.get("").toAbsolutePath();
        Path dataPath = Paths.get(currentDirectory.toString(), DATA_DIRECTORY);
        FULL_PATH = Paths.get(dataPath.toString(), FILE_NAME).toString();

        try {
            Files.createDirectories(dataPath);
        } catch (IOException e) {
            System.err.println("Error creating 'data' directory: " + e.getMessage());
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

    public void salvar(Studio studio) {
        List<Studio> studios = lerTodos();
        studios.add(studio);
        salvarTodos(studios);
    }

    public List<Studio> lerTodos() {
        List<Studio> studios = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FULL_PATH))) {
            studios = (List<Studio>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading studios: " + e.getMessage());
        }
        return studios;
    }

    public void atualizar(Studio studio) {
        List<Studio> studios = lerTodos();
        boolean updated = false;

        for (int i = 0; i < studios.size(); i++) {
            if (studios.get(i).getNome().equals(studio.getNome())) {
                studios.set(i, studio);
                updated = true;
                break;
            }
        }

        if (updated) {
            salvarTodos(studios);
        }
    }

    public void remover(String local) {
        List<Studio> studios = lerTodos();
        if (studios.removeIf(studio -> studio.getNome().equals(local))) {
            salvarTodos(studios);
        }
    }

    private void salvarTodos(List<Studio> studios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FULL_PATH))) {
            oos.writeObject(studios);
        } catch (IOException e) {
            System.err.println("Error writing studios: " + e.getMessage());
        }
    }
}
