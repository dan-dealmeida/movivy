package com.mycompany.movivy.view;

import com.mycompany.movivy.controller.StudioDAO;
import com.mycompany.movivy.model.Studio;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ExibirStudiosGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lista de Studios");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);

        // Obtendo a lista de todos os estúdios
        StudioDAO studioDAO = new StudioDAO();
        List<Studio> studios = studioDAO.lerTodos();

        // Exibindo a lista de estúdios em uma ListView
        ListView<String> listViewStudios = new ListView<>();
        for (Studio studio : studios) {
            listViewStudios.getItems().add(studio.getNome());
        }

        // Configurando evento de seleção para abrir detalhes do estúdio
        listViewStudios.setOnMouseClicked(event -> {
            String selectedStudioLocal = listViewStudios.getSelectionModel().getSelectedItem();
            if (selectedStudioLocal != null) {
                // Obter o estúdio completo com base no local selecionado
                Studio selectedStudio = studioDAO.buscarPorLocal(selectedStudioLocal);
                if (selectedStudio != null) {
                    // Abrir uma nova tela para exibir os detalhes do estúdio
                    DetalhesStudioGUI.mostrarDetalhes(selectedStudio);
                }
            }
        });

        layout.getChildren().add(listViewStudios);

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> voltar(primaryStage));
        voltarButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        layout.getChildren().add(voltarButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void voltar(Stage primaryStage) {
        StudioManagerGUI studioManagerGUI = new StudioManagerGUI();
        studioManagerGUI.start(primaryStage);
    }
}
