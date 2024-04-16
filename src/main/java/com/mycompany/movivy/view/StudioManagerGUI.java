package com.mycompany.movivy.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudioManagerGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Estúdios");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);

        Button cadastrarStudioButton = new Button("Cadastrar Estúdio");
        cadastrarStudioButton.setOnAction(e -> cadastrarStudio(primaryStage));
        cadastrarStudioButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button studiosCadastradosButton = new Button("Estúdios Cadastrados");
        studiosCadastradosButton.setOnAction(e -> mostrarStudiosCadastrados(primaryStage));
        studiosCadastradosButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> voltar(primaryStage));
        voltarButton.setStyle("-fx-font-size: 16px; -fx-min-width: 70px;");

        layout.getChildren().addAll(cadastrarStudioButton, studiosCadastradosButton, voltarButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarStudio(Stage primaryStage) {
        NewStudio newStudio = new NewStudio();
        newStudio.start(primaryStage);
    }

    private void mostrarStudiosCadastrados(Stage primaryStage) {
        ExibirStudiosGUI exibirStudiosGUI = new ExibirStudiosGUI();
        exibirStudiosGUI.start(primaryStage);
    }

    private void voltar(Stage primaryStage) {
        // Volte para a tela inicial
        HomeGUI homeGUI = new HomeGUI();
        homeGUI.start(primaryStage);
    }
}
