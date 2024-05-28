package com.mycompany.movivy.view;

import com.mycompany.movivy.controller.FilmeDAO;
import com.mycompany.movivy.model.Filme;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ExibirFilmesGUI extends Application {

    private ObservableList<String> filmesObservableList;

    public ExibirFilmesGUI() {
        this.filmesObservableList = FXCollections.observableArrayList();
        atualizarListaFilmes();
    }

    public void atualizarListaFilmes() {
        filmesObservableList.clear();
        List<Filme> filmes = FilmeDAO.carregarFilmes(); // Load films using FilmeDAO
        for (Filme filme : filmes) {
            filmesObservableList.add(filme.getTitulo());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exibir Filmes");

        ListView<String> listView = new ListView<>(filmesObservableList);

        Button btnAtualizar = new Button("Atualizar");
        btnAtualizar.setOnAction(e -> {
            primaryStage.close();
            new ExibirFilmesGUI().start(new Stage());
        });

        // Adicionando um evento de clique ao ListView
        listView.setOnMouseClicked(event -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Obtendo o filme selecionado com base no título
                Filme selectedFilme = FilmeDAO.buscarPorTitulo(selectedItem); // Use FilmeDAO method to get filme by title
                // Abrindo uma nova GUI com mais informações sobre o filme selecionado
                DetalhesFilmeGUI.mostrarDetalhesFilme(selectedFilme);
                primaryStage.close();
            }
        });

        VBox vbox = new VBox(listView, btnAtualizar);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
