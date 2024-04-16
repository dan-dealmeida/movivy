package com.mycompany.movivy.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author zelen
 */

import com.mycompany.movivy.controller.FilmeController;
import com.mycompany.movivy.model.Filme;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class NewFilmeGUI extends Application {

    private FilmeController filmeController;

    @Override
    public void start(Stage primaryStage) {
        filmeController = new FilmeController(); 

        primaryStage.setTitle("Gerenciamento de Filmes");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Component setup
        Label titleLabel = new Label("Título:");
        GridPane.setConstraints(titleLabel, 0, 0);
        TextField titleInput = new TextField();
        GridPane.setConstraints(titleInput, 1, 0);

        Label genreLabel = new Label("Gênero:");
        GridPane.setConstraints(genreLabel, 0, 1);
        TextField genreInput = new TextField();
        GridPane.setConstraints(genreInput, 1, 1);

        Label yearLabel = new Label("Ano:");
        GridPane.setConstraints(yearLabel, 0, 2);
        TextField yearInput = new TextField();
        GridPane.setConstraints(yearInput, 1, 2);

        Label directorLabel = new Label("Diretor:");
        GridPane.setConstraints(directorLabel, 0, 3);
        TextField directorInput = new TextField();
        GridPane.setConstraints(directorInput, 1, 3);

        Button addButton = new Button("Adicionar Filme");
        GridPane.setConstraints(addButton, 0, 4);
        addButton.setOnAction(e -> {
            try {
                int year = Integer.parseInt(yearInput.getText());
                filmeController.adicionarFilme(new Filme(titleInput.getText(), genreInput.getText(), year, directorInput.getText()));
                clearFields(titleInput, genreInput, yearInput, directorInput);
            } catch (NumberFormatException ex) {
                System.out.println("Ano inválido"); // Ideally, show a dialog or a label message
            }
        });
        
        Button btnCancelar = new Button("Cancelar");
        GridPane.setConstraints(btnCancelar, 1, 4); // Definindo a posição do botão Cancelar
        btnCancelar.setOnAction(e -> {
            primaryStage.close(); 
        });

        grid.getChildren().addAll(titleLabel, titleInput, genreLabel, genreInput, yearLabel, yearInput, directorLabel, directorInput, addButton, btnCancelar);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
