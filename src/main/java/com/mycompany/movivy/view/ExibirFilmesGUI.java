/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.view;

/**
 *
 * @author zelen
 */


import com.mycompany.movivy.controller.FilmeController;
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

public class ExibirFilmesGUI extends Application {

    private FilmeController filmeController;

    private ObservableList<String> filmesObservableList;

    public ExibirFilmesGUI() {
        this.filmeController = new FilmeController();
        this.filmesObservableList = FXCollections.observableArrayList();
        atualizarListaFilmes();
    }

    public void atualizarListaFilmes() {
        filmesObservableList.clear();
        for (Filme filme : filmeController.getFilmes()) {
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
        Filme selectedFilme = filmeController.getFilmeByTitulo(selectedItem);
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

