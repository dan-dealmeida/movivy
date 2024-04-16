/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.view;

/**
 *
 * @author zelen
 */
import com.mycompany.movivy.model.Filme;
import com.mycompany.movivy.controller.FilmeDAO;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetalhesFilmeGUI {

    public static void mostrarDetalhesFilme(Filme filme) {
        Stage detalhesStage = new Stage();
        detalhesStage.setTitle("Detalhes do Filme");

        
        HBox hbox = new HBox(); 
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);

        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Label tituloLabel = new Label("Título: " + filme.getTitulo());
        Label generoLabel = new Label("Gênero: " + filme.getGenero());
        Label anoLabel = new Label("Ano de Lançamento: " + filme.getAno());
        Label diretorLabel = new Label("Diretor: " + filme.getDiretor());

        Button btnEditar = new Button("Editar");
        btnEditar.setOnAction(e -> openFilmeEdicaoGUI(filme));

        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            FilmeDAO.excluirFilme(filme,FilmeDAO.carregarFilmes());
            detalhesStage.close(); 
        });

       
        hbox.getChildren().addAll(btnEditar, btnExcluir);

       
        vbox.getChildren().addAll(tituloLabel, generoLabel, anoLabel, diretorLabel);

        
        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(vbox, hbox); 

        Scene scene = new Scene(mainVBox, 300, 250); 
        detalhesStage.setScene(scene);
        detalhesStage.show();
    }

    public static void openFilmeEdicaoGUI(Filme filme) {
        FilmeEdicaoGUI.mostrarGUIEdicao(filme);
    }
}

