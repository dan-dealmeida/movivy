/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.view;

/**
 *
 * @author zelen
 */
import com.mycompany.movivy.controller.StudioDAO;
import com.mycompany.movivy.model.Studio;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetalhesStudioGUI {
    public static void mostrarDetalhes(Studio studio) {
        Stage detalhesStudioStage = new Stage();
        detalhesStudioStage.setTitle("Detalhes do Studio");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label localLabel = new Label("Local: " + studio.getNome());
        Label anoFundacaoLabel = new Label("Ano de Fundação: " + studio.getAnoFundacao());
        Label presidenteLabel = new Label("Presidente: " + studio.getPresidente());

        Button editarButton = new Button("Editar");
        editarButton.setOnAction(e -> abrirJanelaEditar(studio, detalhesStudioStage));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deletarStudio(studio, detalhesStudioStage));


        layout.getChildren().addAll(localLabel, anoFundacaoLabel, presidenteLabel, editarButton, deleteButton);

        Scene scene = new Scene(layout, 300, 200);
        detalhesStudioStage.setScene(scene);
        detalhesStudioStage.show();
    }

    private static void abrirJanelaEditar(Studio studio, Stage detalhesStudioStage) {
        EditarStudioGUI.mostrarJanelaEditar(studio);
        detalhesStudioStage.close(); 
    }

    private static void deletarStudio(Studio studio, Stage detalhesStudioStage) {
        StudioDAO studioDAO = new StudioDAO();
        studioDAO.remover(studio.getNome());
        detalhesStudioStage.close(); 
    }
}

