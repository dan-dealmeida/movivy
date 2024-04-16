/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.view;

/**
 *
 * @author zelen
 */
import com.mycompany.movivy.controller.AtorDAO;
import com.mycompany.movivy.model.Ator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewAtor extends Application {
    private AtorDAO atorDAO;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Ator");

        atorDAO = new AtorDAO(); 

       
        TextField nomeField = new TextField();
        TextField idadeField = new TextField();
        TextField alturaField = new TextField();
        TextField nacionalidadeField = new TextField();

      
        Label nomeLabel = new Label("Nome:");
        Label idadeLabel = new Label("Idade:");
        Label alturaLabel = new Label("Altura:");
        Label nacionalidadeLabel = new Label("Nacionalidade:");

       
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, nomeLabel, nomeField);
        gridPane.addRow(1, idadeLabel, idadeField);
        gridPane.addRow(2, alturaLabel, alturaField);
        gridPane.addRow(3, nacionalidadeLabel, nacionalidadeField);

        
        Button cadastrarButton = new Button("Cadastrar");
        cadastrarButton.setOnAction(e -> cadastrarAtor(nomeField.getText(), idadeField.getText(), alturaField.getText(), nacionalidadeField.getText(), primaryStage));

        
        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> voltar(primaryStage));

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(cadastrarButton, voltarButton);

        
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(gridPane, buttonBox);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarAtor(String nome, String idade, String altura, String nacionalidade, Stage primaryStage) {
        try {
            
            int idadeInt = Integer.parseInt(idade);
            double alturaDouble = Double.parseDouble(altura);
            
            
            Ator novoAtor = new Ator(nome, idadeInt, alturaDouble, nacionalidade);

            atorDAO.salvar(novoAtor);
            System.out.println("Ator cadastrado com sucesso!");
        } catch (NumberFormatException ex) {
            System.err.println("Erro ao converter dados: " + ex.getMessage());
        }
        primaryStage.close(); 
        AtorManagerGUI atorManagerGUI = new AtorManagerGUI();
        atorManagerGUI.start(primaryStage);
        
    }

    private void voltar(Stage primaryStage) {
        primaryStage.close(); 
        AtorManagerGUI atorManagerGUI = new AtorManagerGUI();
        atorManagerGUI.start(primaryStage);
        
    }
}

