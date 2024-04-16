package com.mycompany.movivy.view;

import com.mycompany.movivy.controller.StudioDAO;
import com.mycompany.movivy.model.Studio;
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

public class NewStudio extends Application {
    private StudioDAO studioDAO;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cadastro de Estúdio");

        studioDAO = new StudioDAO();

        TextField localField = new TextField();
        TextField anoFundacaoField = new TextField();
        TextField presidenteField = new TextField();

        Label localLabel = new Label("Local:");
        Label anoFundacaoLabel = new Label("Ano de Fundação:");
        Label presidenteLabel = new Label("Presidente:");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, localLabel, localField);
        gridPane.addRow(1, anoFundacaoLabel, anoFundacaoField);
        gridPane.addRow(2, presidenteLabel, presidenteField);

        Button cadastrarButton = new Button("Cadastrar");
        cadastrarButton.setOnAction(e -> cadastrarStudio(localField.getText(), anoFundacaoField.getText(), presidenteField.getText(), primaryStage));

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

    private void cadastrarStudio(String local, String anoFundacao, String presidente, Stage primaryStage) {
        try {
            int anoFundacaoInt = Integer.parseInt(anoFundacao);
            Studio novoStudio = new Studio(local, anoFundacaoInt, presidente);
            studioDAO.salvar(novoStudio);
            System.out.println("Estúdio cadastrado com sucesso!");
        } catch (NumberFormatException ex) {
            System.err.println("Erro ao converter dados: " + ex.getMessage());
        }
        primaryStage.close();
        StudioManagerGUI studioManagerGUI = new StudioManagerGUI();
        studioManagerGUI.start(primaryStage);
    }

    private void voltar(Stage primaryStage) {
        primaryStage.close();
        StudioManagerGUI studioManagerGUI = new StudioManagerGUI();
        studioManagerGUI.start(primaryStage);
    }
}
