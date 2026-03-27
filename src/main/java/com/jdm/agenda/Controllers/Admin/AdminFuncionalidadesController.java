package com.jdm.agenda.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.IOException;

public class AdminFuncionalidadesController {

    @FXML
    private Button botao_grafico;
    @FXML
    private Button botao_agenda;
    @FXML
    private Button botao_funcionarios;
    @FXML
    private Button botao_config;
    @FXML
    private Text nome_user;
    @FXML
    private AnchorPane conteudoPane;

    // 🔹 Método auxiliar para marcar o botão ativo
    private void setActiveButton(Button activeButton) {
        // Remove a classe "active" de todos
        botao_grafico.getStyleClass().remove("active");
        botao_agenda.getStyleClass().remove("active");
        botao_funcionarios.getStyleClass().remove("active");
        botao_config.getStyleClass().remove("active");

        // Adiciona a classe "active" ao botão clicado
        if (!activeButton.getStyleClass().contains("active")) {
            activeButton.getStyleClass().add("active");
        }
    }

    public void mudarPagina_grafico(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Grafico_geral.fxml"));
            conteudoPane.getChildren().setAll(novaPagina);
            setActiveButton(botao_grafico);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mudarPagina_agenda(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Agenda_pagina.fxml"));
            conteudoPane.getChildren().setAll(novaPagina);
            setActiveButton(botao_agenda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mudarPagina_funcionario(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Visualizar_Funcionarios.fxml"));
            conteudoPane.getChildren().setAll(novaPagina);
            setActiveButton(botao_funcionarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mudarPagina_configuracoes(ActionEvent actionEvent) {
        try {
            AnchorPane novaPagina = FXMLLoader.load(getClass().getResource("/Fxml/Admin/Suporte.fxml"));
            conteudoPane.getChildren().setAll(novaPagina);
            setActiveButton(botao_config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
