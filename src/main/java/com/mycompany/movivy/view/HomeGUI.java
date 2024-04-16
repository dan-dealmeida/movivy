package com.mycompany.movivy.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Movivy");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);

        Button filmesButton = new Button("Filmes");
        filmesButton.setOnAction(e -> openFilmeGUI(primaryStage));
        filmesButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button atoresButton = new Button("Atores");
        atoresButton.setOnAction(e -> openAtorGUI(primaryStage));
        atoresButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button studioButton = new Button("Studio");
        studioButton.setOnAction(e -> openStudioGUI(primaryStage));
        studioButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button aleatorioButton = new Button("Aleatório");
        aleatorioButton.setOnAction(e -> {
            FilmeManagerGUI filmeManagerGUI = new FilmeManagerGUI();
            filmeManagerGUI.openRandomFilmeGUI();
        });
        aleatorioButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(filmesButton, atoresButton, studioButton); // Adicionando o botão de Studio ao layout

        layout.getChildren().addAll(buttonBox, aleatorioButton);

        Scene scene = new Scene(layout, 500, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFilmeGUI(Stage primaryStage) {
        FilmeManagerGUI filmeManagerGUI = new FilmeManagerGUI();
        filmeManagerGUI.start(primaryStage);
    }

    private void openAtorGUI(Stage primaryStage) {
        AtorManagerGUI atorManagerGUI = new AtorManagerGUI();
        atorManagerGUI.start(primaryStage);
    }

    private void openStudioGUI(Stage primaryStage) {
        StudioManagerGUI studioManagerGUI = new StudioManagerGUI();
        studioManagerGUI.start(primaryStage);
    }
}
