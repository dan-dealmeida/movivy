package com.mycompany.movivy.view;

import com.mycompany.movivy.controller.StudioDAO;
import com.mycompany.movivy.model.Studio;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EditarStudioGUI {
    public static void mostrarJanelaEditar(Studio studio) {
        Stage editarStudioStage = new Stage();
        editarStudioStage.setTitle("Editar Studio");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);

        // Labels
        Label localLabel = new Label("Local:");
        Label anoFundacaoLabel = new Label("Ano de Fundação:");
        Label presidenteLabel = new Label("Presidente:");

        // TextFields para editar as informações
        TextField localField = new TextField(studio.getNome());
        TextField anoFundacaoField = new TextField(String.valueOf(studio.getAnoFundacao()));
        TextField presidenteField = new TextField(studio.getPresidente());

        // Botão para salvar as alterações
        Button salvarButton = new Button("Salvar");
        salvarButton.setOnAction(e -> {
            studio.setNome(localField.getText());
            studio.setAnoFundacao(Integer.parseInt(anoFundacaoField.getText()));
            studio.setPresidente(presidenteField.getText());
            StudioDAO studioDAO = new StudioDAO();
            studioDAO.atualizar(studio);
            editarStudioStage.close();
        });

        layout.add(localLabel, 0, 0);
        layout.add(localField, 1, 0);
        layout.add(anoFundacaoLabel, 0, 1);
        layout.add(anoFundacaoField, 1, 1);
        layout.add(presidenteLabel, 0, 2);
        layout.add(presidenteField, 1, 2);
        layout.add(salvarButton, 0, 3, 2, 1);

        Scene scene = new Scene(layout, 300, 200);
        editarStudioStage.setScene(scene);
        editarStudioStage.show();
    }
}
