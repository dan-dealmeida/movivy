/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movivy.view;

/**
 *
 * @author zelen
 */

import com.mycompany.movivy.controller.FilmeDAO;
import com.mycompany.movivy.model.Filme;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FilmeEdicaoGUI {
    public static void mostrarGUIEdicao(Filme filme) {
        Stage edicaoStage = new Stage();
        edicaoStage.setTitle("Editar Filme");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        TextField tituloField = new TextField(filme.getTitulo());
        TextField generoField = new TextField(filme.getGenero());
        TextField anoField = new TextField(Integer.toString(filme.getAno()));
        TextField diretorField = new TextField(filme.getDiretor());

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            // Salvar as alterações no filme
            filme.setTitulo(tituloField.getText());
            filme.setGenero(generoField.getText());
            filme.setAno(Integer.parseInt(anoField.getText()));
            filme.setDiretor(diretorField.getText());

            // Atualizar o filme editado no arquivo de texto
            FilmeDAO.atualizarFilme(filme, FilmeDAO.carregarFilmes());

            // Fechar a janela de edição
            edicaoStage.close();
        });

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> edicaoStage.close());

        VBox.setMargin(btnSalvar, new Insets(10, 0, 0, 0)); // Ajustar a margem para o botão "Salvar"
        VBox.setMargin(btnCancelar, new Insets(10, 0, 0, 0)); // Ajustar a margem para o botão "Cancelar"

        vbox.getChildren().addAll(
            new Label(filme.getTitulo()),
            new Label("Gênero:"), generoField,
            new Label("Ano:"), anoField,
            new Label("Diretor:"), diretorField,
            btnSalvar, btnCancelar
        );

        Scene scene = new Scene(vbox, 300, 350); // Aumentar a altura para acomodar os botões
        edicaoStage.setScene(scene);
        edicaoStage.show();
    }
}

