/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.view;

/**
 *
 * @author zelen
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AtorManagerGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciador de Atores");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);

        Button cadastrarAtorButton = new Button("Cadastrar Ator");
        cadastrarAtorButton.setOnAction(e -> cadastrarAtor(primaryStage));
        cadastrarAtorButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button atoresCadastradosButton = new Button("Atores Cadastrados");
        atoresCadastradosButton.setOnAction(e -> mostrarAtoresCadastrados(primaryStage));
        atoresCadastradosButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> voltar(primaryStage));
        voltarButton.setStyle("-fx-font-size: 16px; -fx-min-width: 70px;");

        layout.getChildren().addAll(cadastrarAtorButton, atoresCadastradosButton, voltarButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarAtor(Stage primaryStage) {
        NewAtor newAtor = new NewAtor();
        newAtor.start(primaryStage);
    }

    private void mostrarAtoresCadastrados(Stage primaryStage) {
        ExibirAtoresGUI exibirAtoresGUI = new ExibirAtoresGUI();
        exibirAtoresGUI.start(primaryStage);
    }

    private void voltar(Stage primaryStage) {
        HomeGUI homeGUI = new HomeGUI();
        homeGUI.start(primaryStage);
    }
}
