package com.mycompany.movivy.view;

import com.mycompany.movivy.controller.AtorDAO;
import com.mycompany.movivy.model.Ator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditarAtorGUI {
    public static void mostrarJanelaEditar(Ator ator) {
        Stage editarAtorStage = new Stage();
        editarAtorStage.setTitle("Editar Ator");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);

        // Labels
        Label nomeLabel = new Label("Nome:");
        Label idadeLabel = new Label("Idade:");
        Label alturaLabel = new Label("Altura:");
        Label nacionalidadeLabel = new Label("Nacionalidade:");

        // TextFields para editar as informações
        TextField nomeField = new TextField(ator.getNome());
        TextField idadeField = new TextField(String.valueOf(ator.getIdade()));
        TextField alturaField = new TextField(String.valueOf(ator.getAltura()));
        TextField nacionalidadeField = new TextField(ator.getNacionalidade());

        // Botão para salvar as alterações
        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(e -> {
            ator.setNome(nomeField.getText());
            ator.setIdade(Integer.parseInt(idadeField.getText()));
            ator.setAltura(Double.parseDouble(alturaField.getText()));
            ator.setNacionalidade(nacionalidadeField.getText());
            AtorDAO atorDAO = new AtorDAO();
            atorDAO.atualizar(ator);
            editarAtorStage.close();
        });

        layout.add(nomeLabel, 0, 0);
        layout.add(nomeField, 1, 0);
        layout.add(idadeLabel, 0, 1);
        layout.add(idadeField, 1, 1);
        layout.add(alturaLabel, 0, 2);
        layout.add(alturaField, 1, 2);
        layout.add(nacionalidadeLabel, 0, 3);
        layout.add(nacionalidadeField, 1, 3);
        layout.add(salvarButton, 0, 4, 2, 1);

        Scene scene = new Scene(layout, 300, 200);
        editarAtorStage.setScene(scene);
        editarAtorStage.show();
    }
}
