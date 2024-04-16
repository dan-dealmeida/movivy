package com.mycompany.movivy.view;

import java.util.List;
import java.util.Random;
import com.mycompany.movivy.controller.FilmeDAO;
import com.mycompany.movivy.model.Filme;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FilmeManagerGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Movivy");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);

        Button cadastrarFilmeButton = new Button("Cadastrar Filme");
        cadastrarFilmeButton.setOnAction(e -> openNewFilmeGUI());
        cadastrarFilmeButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button filmesCadastradosButton = new Button("Filmes Cadastrados");
        filmesCadastradosButton.setOnAction(e -> showCadastrados());
        filmesCadastradosButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> returnToHome(primaryStage));
        voltarButton.setStyle("-fx-font-size: 16px; -fx-min-width: 70px;");

        layout.getChildren().addAll(cadastrarFilmeButton, filmesCadastradosButton, voltarButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openNewFilmeGUI() {
        NewFilmeGUI filmeGUI = new NewFilmeGUI();
        filmeGUI.start(new Stage());
    }

    private void showCadastrados() {
        ExibirFilmesGUI exibirFilmesGUI = new ExibirFilmesGUI();
        exibirFilmesGUI.start(new Stage());
    }

    private void returnToHome(Stage primaryStage) {
        HomeGUI homeGUI = new HomeGUI();
        homeGUI.start(primaryStage);
    }

    // Método público para abrir um filme aleatório (acessível de HomeGUI)
    public void openRandomFilmeGUI() {
        List<Filme> filmes = FilmeDAO.carregarFilmes();

        if (!filmes.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(filmes.size());
            DetalhesFilmeGUI.mostrarDetalhesFilme(filmes.get(randomIndex));
        } else {
            System.out.println("Não há filmes cadastrados.");
        }
    }
}
