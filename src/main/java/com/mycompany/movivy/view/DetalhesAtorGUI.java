package com.mycompany.movivy.view;

import com.mycompany.movivy.controller.AtorDAO;
import com.mycompany.movivy.model.Ator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetalhesAtorGUI {
    public static void mostrarDetalhes(Ator ator) {
        Stage detalhesAtorStage = new Stage();
        detalhesAtorStage.setTitle("Detalhes do Ator");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label nomeLabel = new Label("Nome: " + ator.getNome());
        Label idadeLabel = new Label("Idade: " + ator.getIdade());
        Label alturaLabel = new Label("Altura: " + ator.getAltura());
        Label nacionalidadeLabel = new Label("Nacionalidade: " + ator.getNacionalidade());

        Button editarButton = new Button("Editar");
        editarButton.setOnAction(e -> abrirJanelaEditar(ator, detalhesAtorStage));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deletarAtor(ator, detalhesAtorStage));


        layout.getChildren().addAll(nomeLabel, idadeLabel, alturaLabel, nacionalidadeLabel, editarButton, deleteButton);

        Scene scene = new Scene(layout, 300, 200);
        detalhesAtorStage.setScene(scene);
        detalhesAtorStage.show();
    }

    private static void abrirJanelaEditar(Ator ator,Stage detalhesAtorStage) {
        EditarAtorGUI.mostrarJanelaEditar(ator);
        detalhesAtorStage.close(); 
    }

    private static void deletarAtor(Ator ator, Stage detalhesAtorStage) {
        AtorDAO atorDAO = new AtorDAO();
        atorDAO.remover(ator.getNome());
        detalhesAtorStage.close(); 
    }

}
