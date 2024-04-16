package com.mycompany.movivy.view;

import com.mycompany.movivy.controller.AtorDAO;
import com.mycompany.movivy.model.Ator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ExibirAtoresGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lista de Atores");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setAlignment(Pos.CENTER);

        // Obtendo a lista de todos os atores
        AtorDAO atorDAO = new AtorDAO();
        List<Ator> atores = atorDAO.lerTodos();

        // Exibindo a lista de atores em uma ListView
        ListView<String> listViewAtores = new ListView<>();
        for (Ator ator : atores) {
            listViewAtores.getItems().add(ator.getNome());
        }

        // Configurando evento de seleção para abrir detalhes do ator
        listViewAtores.setOnMouseClicked(event -> {
            String selectedAtorName = listViewAtores.getSelectionModel().getSelectedItem();
            if (selectedAtorName != null) {
                // Obter o ator completo com base no nome selecionado
                Ator selectedAtor = atorDAO.buscarPorNome(selectedAtorName);
                if (selectedAtor != null) {
                    // Abrir uma nova tela para exibir os detalhes do ator
                    DetalhesAtorGUI.mostrarDetalhes(selectedAtor);
                }
            }
        });

        layout.getChildren().add(listViewAtores);

        Button voltarButton = new Button("Voltar");
        voltarButton.setOnAction(e -> voltarParaHome(primaryStage));
        voltarButton.setStyle("-fx-font-size: 16px; -fx-min-width: 150px;");

        layout.getChildren().add(voltarButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void voltarParaHome(Stage primaryStage) {
        HomeGUI homeGUI = new HomeGUI();
        homeGUI.start(primaryStage);
    }
}
